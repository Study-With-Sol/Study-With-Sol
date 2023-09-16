import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_3_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart'; // SharedPreferences 패키지 추가

class Join2 extends StatefulWidget {
  const Join2({Key? key}) : super(key: key);

  @override
  _Join2State createState() => _Join2State();
}

class _Join2State extends State<Join2> {
  TextEditingController emailController = TextEditingController();
  TextEditingController nameController = TextEditingController();
  TextEditingController verificationCodeController = TextEditingController();
  String emailVerificationResponse = "";
  String verificationCode = "";

  final Dio _dio = Dio(); // Dio 인스턴스 생성

  @override
  void dispose() {
    _dio.close(); // Dio 인스턴스를 사용한 후 꼭 닫아줘야 합니다.
    super.dispose();
  }

  Future<void> sendEmailVerification() async {
    String email = emailController.text;
    String name = nameController.text;

    // 서버에 이메일 전송
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/users/mail-check'; // 실제 API 엔드포인트로 수정
    final requestData = {
      "email": email,
      "name": name,
    };

    try {
      final response = await _dio.post(apiUrl, data: requestData);

      if (response.statusCode == 200) {
        setState(() {
          emailVerificationResponse = "인증 이메일을 전송했습니다.";
          verificationCode = response.data.toString();
        });

        // SharedPreferences에 이름과 이메일 저장
        _saveNameAndEmail(name, email);
      } else {
        setState(() {
          emailVerificationResponse = "인증 이메일 전송에 실패했습니다.";
          verificationCode = "";
        });
      }
    } catch (e) {
      setState(() {
        emailVerificationResponse = "오류가 발생했습니다: $e";
        verificationCode = "";
      });
    }
  }

  Future<void> verifyEmail() async {
    String enteredCode = verificationCodeController.text;

    if (enteredCode == verificationCode) {
      // 인증 번호 일치
      // 다음 페이지로 이동
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => const Join3(),
          fullscreenDialog: false,
        ),
      );
    } else {
      // 인증 번호 불일치
      setState(() {
        emailVerificationResponse = "인증 번호가 일치하지 않습니다.";
      });
    }
  }

  // SharedPreferences에 이름과 이메일을 저장하는 함수
  Future<void> _saveNameAndEmail(String name, String email) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString("name", name);
    await prefs.setString("email", email);
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
              name: "이름을 입력하세요",
              controller: nameController,
            ),
            InputBoxWidget(name: "이메일을 입력하세요", controller: emailController),
            InkWell(
              onTap: () async {
                await sendEmailVerification();
              },
              child: const Button(
                text: "인증하기",
                bgColor: Colors.white,
                textColor: Colors.black,
              ),
            ),
            InputBoxWidget(
              name: "이메일 인증 번호를 입력하세요",
              controller: verificationCodeController,
            ),
            Text(emailVerificationResponse),
            const SizedBox(
              height: 20,
            ),
            InkWell(
              onTap: () {
                verifyEmail();
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
