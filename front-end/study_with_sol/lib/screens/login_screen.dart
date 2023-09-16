import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/baby/baby_main_screen.dart';
import 'package:study_with_sol/screens/join/join_1_screen.dart';
import 'package:study_with_sol/screens/parent/parent_main_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';
import 'package:dio/dio.dart';

class Login extends StatefulWidget {
  const Login({
    super.key,
  });

  @override
  _LoginState createState() => _LoginState();
}

class _LoginState extends State<Login> {
  // TextEditingController를 정의
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

  @override
  void dispose() {
    // 페이지가 dispose될 때 컨트롤러를 해제해야 합니다.
    usernameController.dispose();
    passwordController.dispose();
    super.dispose();
  }

  Future<void> loginUser() async {
    String username = usernameController.text;
    String password = passwordController.text;

    // Dio 인스턴스 생성
    final dio = Dio();

    // POST 요청 보낼 URL 설정
    const url =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/users/login';

    // POST 요청 본문 데이터 (JSON 형식)
    final postData = {
      'id': username,
      'password': password,
    };

    try {
      // POST 요청 보내기
      final response = await dio.post(
        url,
        data: postData, // 요청 본문 데이터 설정
      );

      // 응답 데이터 확인
      print("보냄");
      if (response.statusCode == 200) {
        final jsonResponse = response.data;
        String message = jsonResponse['message'];
        Map<String, dynamic> data = jsonResponse['data'];

        print("메시지: $message");
        print("사용자 아이디: ${data['id']}");
        print("사용자 이름: ${data['name']}");
        print("토큰: ${data['token']}");
        print("부모야? ${data['isParent']}");

        // 다음 화면으로 이동 예제
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) =>
                data['isParent'] ? const ParentMain() : const BabyMain(),
          ),
        );
      } else {
        // 요청 실패
        print("API 호출 실패: ${response.statusCode}");
        print("에러 응답: ${response.data}");
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('로그인'),
      ),
      body: Column(
        children: [
          InputBoxWidget(
            name: "아이디",
            controller: usernameController, // 컨트롤러 연결
          ),
          InputBoxWidget(
            name: "비밀번호",
            controller: passwordController, // 컨트롤러 연결
          ),
          const SizedBox(
            height: 20,
          ),
          InkWell(
            onTap: () async {
              await loginUser(); // API 호출 함수 호출
            },
            child: const Button(
              text: "로그인",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          InkWell(
            onTap: () {
              // 회원가입 페이지로 이동
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => const Join1(),
                  fullscreenDialog: false,
                ),
              );
            },
            child: const Button(
              text: "회원가입",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
        ],
      ),
    );
  }
}
