package com.shbhack.studywithsol.user.service;

import com.shbhack.studywithsol.jwt.JwtTokenProvider;
import com.shbhack.studywithsol.transaction.dto.request.TransactionCreateRequest;
import com.shbhack.studywithsol.transaction.service.TransactionService;
import com.shbhack.studywithsol.user.domain.Connection;
import com.shbhack.studywithsol.user.domain.User;
import com.shbhack.studywithsol.user.dto.request.*;
import com.shbhack.studywithsol.user.dto.response.*;
import com.shbhack.studywithsol.user.repository.ConnectionRepository;
import com.shbhack.studywithsol.user.repository.MessageRepository;
import com.shbhack.studywithsol.user.repository.UserRepository;
import com.shbhack.studywithsol.utils.error.enums.ErrorMessage;
import com.shbhack.studywithsol.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final TransactionService transactionService;

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ConnectionRepository connectionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(String email) throws MessagingException {
        StringBuilder key = new StringBuilder();
        Random random = new Random();

        for(int i=0;i<6;i++){
            key.append(random.nextInt(10));
        }

        String code = key.toString(), subject = "Study With SOL 회원 가입 인증 코드", msg="";

        msg += "<h1 style=\"color: #0046FF; font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"color: #0046FF; font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 인증 코드를 Study with SOL 회원 가입 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";

        MimeMessage mail = javaMailSender.createMimeMessage();
        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mail.setSubject(subject,"utf-8");
        mail.setText(msg,"utf-8","html");

        javaMailSender.send(mail);
        return code;
    }

    // ID 중복 검사
    public Boolean duplicationCheck(String id) {
        return userRepository.findById(id).isPresent();
    }


    public Boolean signUp(UserSignUpRequest userSignUpRequest) {
        //Id 중복 확인
        userRepository.findById(userSignUpRequest.id()).ifPresent(
                user -> { throw new BusinessException(ErrorMessage.USER_ID_DUPLICATED);}
        );

        //저장
        User user = User.builder()
                .id(userSignUpRequest.id())
                .password(passwordEncoder.encode(userSignUpRequest.password()))
                .name(userSignUpRequest.name())
                .email(userSignUpRequest.email())
                .isParent(userSignUpRequest.isParent())
                .build();

        userRepository.save(user);

        return userRepository.findById(userSignUpRequest.id()).get().getUserId() == user.getUserId();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findById(userLoginRequest.id())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPassword())) {
            throw new BusinessException(ErrorMessage.USER_NOT_FOUND); // 비밀번호가 맞지 않을때 예외 처리
        }

        // 토큰 발행
        String token = JwtTokenProvider.createToken(user.getUserId());
        return UserLoginResponse.of(user, token);
    }


    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        return UserInfoResponse.of(user);
    }
    public Boolean checkPassword(Long userId, UserUpdatePasswordRequest userUpdatePasswordRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        if(!passwordEncoder.matches(userUpdatePasswordRequest.password(), user.getPassword())) {
            return false;
        }
        return  true;
    }

    public Boolean updatePassword(Long userId, UserUpdatePasswordRequest userUpdatePasswordRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        user.updatePassword(passwordEncoder.encode(userUpdatePasswordRequest.password()));

        if((user.getPassword()).equals(passwordEncoder.encode(userUpdatePasswordRequest.password()))){
            return true;
        }

        return false;
    }

    public Boolean updateEmail(Long userId, UserUpdateEmailRequest userUpdateEmailRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        user.updateEmail(userUpdateEmailRequest.email());

        if((user.getEmail()).equals(userUpdateEmailRequest.email())){
            return true;
        }
        return false;
    }




    public Boolean existMainAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        if(user.getMainAccount() == null){
            return false;
        }
        return true;
    }


    public UserIdCheckResponse idCheck(UserIdCheckRequest userIdCheckRequest) {
        User user = userRepository.findById(userIdCheckRequest.id())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        return UserIdCheckResponse.of(user.getId(), user.getName());
    }

    public Boolean oneTransfer(String childId, Long parentUserId) {
        User parent = userRepository.findById(parentUserId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        User child = userRepository.findById(childId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리

        //자녀에게 주 계좌가 등록되어 있는지 확인
        if(child.getMainAccount() == null) {
            throw new BusinessException(ErrorMessage.ACCOUNT_NOT_FOUND);
        }

        //랜덤 문구 만들기
        String randomMessage = messageRepository.getRandomMessage().getAdjective() + messageRepository.getRandomMessage().getNoun();

        //부모에 메시지 저장
        parent.setMessage(randomMessage);

        //자녀로 1원 이체
        // 스윗솔 -> 자녀
        transactionService.save(new TransactionCreateRequest(child.getMainAccount().getId(), randomMessage, 1L, true ,child.getName(), "스윗솔" ));


        return true;
    }

    public Boolean registerChild(UserRegisterChildRequest userRegisterChildRequest, Long parentId) {

        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        // 입력받은 메시지와 부모한테 저장된 메시지 확인
        if(parent.getMessage() == null ) {
            throw  new BusinessException(ErrorMessage.NO_MESSAGE);
        }
        if(!parent.getMessage().equals(userRegisterChildRequest.message())){
            return  false;
        }

        // 같다면 null 로 수정
        parent.setMessage("");

        // 같다면 connection 추가
        User child = userRepository.findById(userRegisterChildRequest.id())
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        connectionRepository.save(new Connection(parent, child, userRegisterChildRequest.alias()));

        return true;

    }

    public List<UserChildInfoResponse> getChildInfo(Long userId) {

        User parent = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리

        //부모와 연결된 자녀들 리스트 찾기
        List<Connection> connectionList = connectionRepository.findAllByParentAndIsConnect(parent, true) ;

        // 그 자녀의 pk와 아이디를 담는 responseDto 리스트 생성
        List<UserChildInfoResponse> childInfoResponseList = new ArrayList<>();
        for(Connection connection : connectionList){
            User child = userRepository.findById(connection.getChildren().getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리
            childInfoResponseList.add(UserChildInfoResponse.of(child));
        }


        return childInfoResponseList;
    }

    public List<UserParentResponse> getParent(Long childId) {
        User child = userRepository.findById(childId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        List<Connection> connectionList = connectionRepository.findAllByChildrenAndIsConnect(child, true);

        List<UserParentResponse> userParentResponseList = new ArrayList<>();

        for(Connection connection : connectionList){
            User parent = userRepository.findById(connection.getParent().getUserId())
                    .orElseThrow(() -> new BusinessException(ErrorMessage.PARENT_NOT_FOUND));
            userParentResponseList.add(UserParentResponse.of(parent, connection));
        }

        return userParentResponseList;



    }


    public Boolean disconnectChild(Long childId, Long parentId) {
        User parent = userRepository.findById(parentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND)); //해당 아이디가 없을때 예외 처리
        User child = userRepository.findById(childId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CHILD_NOT_FOUND)); //해당 자녀 아이디가 없을때 예외 처리

        Connection connection = connectionRepository.findByParentAndChildren(parent, child);
        if(connection == null) {
                throw new BusinessException(ErrorMessage.CONNECTION_NOT_FOUND);
        }

        connection.disconnect();

        return true;
    }


}
