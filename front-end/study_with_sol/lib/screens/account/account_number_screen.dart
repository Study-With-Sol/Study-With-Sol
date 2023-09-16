import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/account/account_1won_screen.dart';
import 'package:study_with_sol/screens/login_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class AccountNumber extends StatelessWidget {
  const AccountNumber({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('계좌 등록'),
      ),
      body: Center(
        child: Column(
          children: [
            const Text("계좌 인증"),
            const Text("실명인증과 계좌등록을 위해 보유하고 있는 신한은행 계좌정보를 입력해주세요."),
            const SizedBox(
              height: 100,
            ),
            const InputBoxWidget(name: "계좌번호"),
            const Text("본인 명의의 계좌번호만 입력 가능합니다"),
            const SizedBox(
              height: 100,
            ),
            InkWell(
              onTap: () {
                // 로그인화면으로 돌아가기
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (BuildContext context) {
                      return const Account1Won(); // 이동할 화면의 위젯
                    },
                  ),
                );
              },
              child: const Button(
                text: "등록 >",
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
