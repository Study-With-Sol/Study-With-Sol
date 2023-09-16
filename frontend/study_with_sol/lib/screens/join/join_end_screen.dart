import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/login_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class JoinEnd extends StatelessWidget {
  // 자녀인지 부모인지에 따라서 문구 달라지기

  const JoinEnd({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(
      //   title: const Text('회원가입'),
      // ),
      body: Center(
        child: Column(
          children: [
            const Text("가입 완료"),
            const Text("가입 완료 메시지 / 부모인지 자녀인지에 따라"),
            const SizedBox(
              height: 100,
            ),
            InkWell(
              onTap: () {
                // 로그인화면으로 돌아가기
                Navigator.of(context).pushReplacement(
                  MaterialPageRoute(
                    builder: (BuildContext context) {
                      return const Login(); // 이동할 화면의 위젯
                    },
                  ),
                );
              },
              child: const Button(
                text: "로그인하러가기 >",
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
