import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_end_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';

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

  final Dio _dio = Dio(); // Dio 인스턴스 생성

  @override
  void dispose() {
    _dio.close(); // Dio 인스턴스를 사용한 후 꼭 닫아줍니다.
    super.dispose();
  }

  Future<void> checkDuplicateId(String id) async {
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/users/duplication-check'; // 중복 체크 API 엔드포인트로 수정
    final requestData = {"id": id};

    try {
      final response = await _dio.post(apiUrl, data: requestData);

      if (response.statusCode == 200) {
        final jsonResponse = response.data;
        String message = jsonResponse['message'];

        if (message == 'duplicated') {
          setState(() {
            isIdAvailable = false;
          });
        } else {
          setState(() {
            isIdAvailable = true;
            savedId = id; // 아이디 저장
          });
        }
      } else {
        // 중복 확인 요청 실패
        setState(() {
          isIdAvailable = false;
        });
      }
    } catch (e) {
      // 오류 처리
      setState(() {
        isIdAvailable = false;
      });
    }
  }

  Future<void> registerUser() async {
    // SharedPreferences에서 사용자 정보 불러오기
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? name = prefs.getString('name');
    String? email = prefs.getString('email');
    bool? isParent = prefs.getBool('isParent');

    // 저장된 아이디와 비밀번호 가져오기
    String id = savedId!;
    String password = savedPassword!;

    // 회원가입 정보 생성
    final requestData = {
      "id": id,
      "password": password,
      "name": name,
      "email": email,
      "isParent": isParent,
    };

    // 회원가입 API 엔드포인트로 수정
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/users/sign-up';

    try {
      final response = await _dio.post(apiUrl, data: requestData);

      if (response.statusCode == 200) {
        final jsonResponse = response.data;
        String message = jsonResponse['message'];

        if (message == 'success') {
          // 회원가입 성공
          // 회원가입 완료 페이지로 이동
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const JoinEnd(),
              fullscreenDialog: false,
            ),
          );
        } else {
          // 회원가입 실패
          // 실패 처리 로직 추가
        }
      } else {
        // 요청 실패
        // 실패 처리 로직 추가
      }
    } catch (e) {
      // 오류 처리
      // 오류 처리 로직 추가
    }
  }

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
              onTap: () async {
                String enteredId = idController.text;
                await checkDuplicateId(enteredId);
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
              isPassword: true,
            ),
            InputBoxWidget(
              name: "비밀번호 확인",
              controller: confirmPasswordController,
              isPassword: true,
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
                String password = passwordController.text;
                String confirmPassword = confirmPasswordController.text;

                if (password == confirmPassword) {
                  setState(() {
                    doPasswordsMatch = true;
                    savedPassword = password;
                  });

                  print("저장된 아이디: $savedId");
                  print("저장된 비밀번호: $savedPassword");

                  // 회원가입 정보를 백엔드에 저장
                  registerUser();
                } else {
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
