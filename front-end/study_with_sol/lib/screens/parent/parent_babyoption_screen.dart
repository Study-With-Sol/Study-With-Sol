import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class BabyOption extends StatelessWidget {
  const BabyOption({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('설정'),
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              decoration: const BoxDecoration(
                color: Colors.blue,
              ),
              child: const Text("정기 용돈 설정"),
            ),
            const InputBoxWidget(name: "금액"),
            const Text("매월 3일"), // dropbox 넣기
            const SizedBox(
              height: 100,
            ),
            const Button(
              text: "설정 >",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ],
        ),
      ),
    );
  }
}
