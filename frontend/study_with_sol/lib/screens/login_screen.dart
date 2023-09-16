import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_1_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Login extends StatelessWidget {
  const Login({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('로그인'),
      ),
      body: Column(
        children: [
          const InputBoxWidget(name: "아이디"),
          const InputBoxWidget(name: "비밀번호"),
          InkWell(
            onTap: () {
              // 아이디랑 비밀번호 적혀있는 것 확인해서 백이랑 연결하기

              // 백에서 정보 받아서 메인화면 부모인지 자녀인지 이동하기

              // 메인 화면으로 가기
            },
            child: const Button(
              text: "로그인",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
          InkWell(
            onTap: () {
              // 회원가입 페이지로 이동
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => const Join1(),
                  // true: 밑에서 가져오고 x
                  // false: 옆에서 가져오고 <
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
