import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class PlusBaby extends StatelessWidget {
  const PlusBaby({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('자녀 추가'),
      ),
      body: Center(
        child: Column(
          children: [
            const Text("자녀 추가"),
            const Text("아이디로 자녀를 찾은 후 추가 버튼을 누르세요. 자녀가 수락하면 등록됩니다."),
            const SizedBox(
              height: 100,
            ),
            const InputBoxWidget(name: "자녀와의 관계"),
            Row(
              children: [
                const InputBoxWidget(name: "자녀 아이디"),
                InkWell(
                  onTap: () {
                    // 검색
                  },
                  child: const Button(
                    text: "검색",
                    bgColor: Colors.white,
                    textColor: Colors.black,
                  ),
                ),
              ],
            ),
            const Text("김신한 님이 맞습니까?"),
            const SizedBox(
              height: 100,
            ),
            const Button(
              text: "확인 >",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ],
        ),
      ),
    );
  }
}
