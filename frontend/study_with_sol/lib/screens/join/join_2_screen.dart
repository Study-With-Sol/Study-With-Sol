import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_3_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

// 부모인가용 자녀인가용

class Join2 extends StatelessWidget {
  // 변수 부모인지 자녀인지
  final bool isParent;

  const Join2({
    super.key,
    required this.isParent,
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
            const InputBoxWidget(name: "이름을 입력하세요"),
            Row(
              children: [
                const InputBoxWidget(name: "이메일을 입력하세요"),
                InkWell(
                  onTap: () {
                    // 이메일 인증 보내기 api
                  },
                  child: const Button(
                    text: "인증하기",
                    bgColor: Colors.white,
                    textColor: Colors.black,
                  ),
                ),
              ],
            ),
            const InputBoxWidget(name: "이메일 인증 번호를 입력하세요"),
            const Text("인증 완료되었습니다"),
            const SizedBox(
              height: 100,
            ),
            InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const Join3(),
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
