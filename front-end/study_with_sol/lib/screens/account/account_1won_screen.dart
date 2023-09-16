import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Account1Won extends StatelessWidget {
  const Account1Won({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('계좌 등록'),
      ),
      body: const Center(
        child: Column(
          children: [
            Text("계좌 인증"),
            Text("인증하신 계좌로 1원을 보내드렸습니다. 계좌의 입금내역에 표시된 6자리 숫자를 입력해주세요."),
            SizedBox(
              height: 100,
            ),
            InputBoxWidget(name: "인증번호"),
            Text("올바른 번호를 다시 입력해주세요."),
            SizedBox(
              height: 100,
            ),
            Button(
              text: "다음 >",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ],
        ),
      ),
    );
  }
}
