import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_end_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Join3 extends StatelessWidget {
  // 변수 부모인지 자녀인지

  const Join3({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('회원가입'),
      ),
      body: Center(
        child: Column(
          children: [
            Row(
              children: [
                const InputBoxWidget(name: "아이디"),
                InkWell(
                  onTap: () {
                    // 백이랑 중복확인하기
                  },
                  child: const Button(
                    text: "중복확인",
                    bgColor: Colors.white,
                    textColor: Colors.black,
                  ),
                ),
              ],
            ),
            const Text("사용 가능한 아이디입니다"),
            const InputBoxWidget(name: "비밀번호"),
            const InputBoxWidget(name: "비밀번호 확인"),
            const Text("비밀번호가 일치합니다"),
            const SizedBox(
              height: 100,
            ),
            InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const JoinEnd(),
                    // true: 밑에서 가져오고 x
                    // false: 옆에서 가져오고 <
                    fullscreenDialog: false,
                  ),
                );
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
