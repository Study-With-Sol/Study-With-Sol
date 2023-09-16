import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/baby/baby_main_screen.dart';
import 'package:study_with_sol/screens/join/join_1_screen.dart';
import 'package:study_with_sol/screens/parent/parent_main_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart'; // SharedPreferences 임포트 추가

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
      if (response.statusCode == 200) {
        final jsonResponse = response.data;
        String message = jsonResponse['message'];
        Map<String, dynamic> data = jsonResponse['data'];

        // 토큰을 SharedPreferences에 저장
        await saveToken(data['token']);

        // 다음 화면으로 이동 예제
        Navigator.pushReplacement(
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

  Future<void> saveToken(String token) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setString('token', token);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(
      //   backgroundColor: Color(0xFFEFFBFF), // 배경색 설정
      //   title: const Text('로그인'),
      // ),
      backgroundColor: Color(0xFFEFFBFF), // 배경색 설정
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center, // 수직 방향 중앙 정렬
        children: [
          InputBoxWidget(
            name: "아이디",
            controller: usernameController, // 컨트롤러 연결
          ),
          InputBoxWidget(
            name: "비밀번호",
            controller: passwordController, // 컨트롤러 연결
            isPassword: true,
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
              bgColor: Color(0xFF222C36), // 배경색 설정
              textColor: Colors.white,
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
            child: const Text(
              "아직 회원가입을 하지 않으셨나요?",
              style: TextStyle(
                color: Color(0xFF222C36), // 텍스트 색상 설정
                decoration: TextDecoration.underline, // 밑줄 추가
              ),
            ),
          ),
        ],
      ),
    );
  }
}
