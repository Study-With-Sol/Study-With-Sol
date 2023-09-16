import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_end_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Join3 extends StatefulWidget {
  const Join3({Key? key}) : super(key: key);

  @override
  _Join3State createState() => _Join3State();
}

class _Join3State extends State<Join3> {
  TextEditingController idController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController confirmPasswordController = TextEditingController();
  bool isIdAvailable = true;
  bool doPasswordsMatch = true;
  String? savedId;
  String? savedPassword;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('회원가입'),
      ),
      body: Center(
        child: Column(
          children: [
            InputBoxWidget(
              name: "아이디",
              controller: idController,
            ),
            InkWell(
              onTap: () {
                // 백엔드와 중복 확인 로직 추가
                String enteredId = idController.text;
                // 중복 확인 API 호출 또는 백엔드와 통신하여 중복 확인
                // 예시: 중복되지 않은 아이디인 경우
                bool isAvailable = true; // 중복되지 않음
                if (isAvailable) {
                  setState(() {
                    isIdAvailable = true;
                    savedId = enteredId; // 아이디 저장
                  });
                } else {
                  setState(() {
                    isIdAvailable = false;
                  });
                }
              },
              child: const Button(
                text: "중복확인",
                bgColor: Colors.white,
                textColor: Colors.black,
              ),
            ),
            isIdAvailable
                ? const Text("사용 가능한 아이디입니다")
                : const Text("사용 중인 아이디입니다",
                    style: TextStyle(color: Colors.red)),
            InputBoxWidget(
              name: "비밀번호",
              controller: passwordController,
              isPassword: true, // 비밀번호 입력 필드로 설정
            ),
            InputBoxWidget(
              name: "비밀번호 확인",
              controller: confirmPasswordController,
              isPassword: true, // 비밀번호 입력 필드로 설정
            ),
            doPasswordsMatch
                ? const Text("비밀번호가 일치합니다")
                : const Text("비밀번호가 일치하지 않습니다",
                    style: TextStyle(color: Colors.red)),
            const SizedBox(
              height: 100,
            ),
            InkWell(
              onTap: () {
                // 비밀번호 확인
                String password = passwordController.text;
                String confirmPassword = confirmPasswordController.text;

                if (password == confirmPassword) {
                  // 비밀번호 일치
                  setState(() {
                    doPasswordsMatch = true;
                    savedPassword = password; // 비밀번호 저장
                  });

                  // 저장된 아이디와 비밀번호를 출력
                  print("저장된 아이디: $savedId");
                  print("저장된 비밀번호: $savedPassword");

                  // 회원가입 완료 페이지로 이동
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (context) => const JoinEnd(),
                      fullscreenDialog: false,
                    ),
                  );
                } else {
                  // 비밀번호 불일치
                  setState(() {
                    doPasswordsMatch = false;
                  });
                }
              },
              child: const Button(
                text: "다음",
                bgColor: Colors.white,
                textColor: Colors.black,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
