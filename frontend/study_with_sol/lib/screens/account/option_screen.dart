import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Option extends StatelessWidget {
  const Option({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('설정'),
      ),
      body: Column(
        children: [
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("연결 계좌 변경"),
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.grey,
            ),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text("현재계좌번호"),
                Text("주계좌"),
              ],
            ),
          ),
          Container(
            // 리스트로 빼기
            decoration: const BoxDecoration(
              color: Colors.grey,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text("계좌번호"),
                InkWell(
                  onTap: () {
                    // 이메일 인증 보내기 api
                  },
                  child: const Button(
                    text: "주계좌로",
                    bgColor: Colors.white,
                    textColor: Colors.black,
                  ),
                ),
              ],
            ),
          ),
          InkWell(
            onTap: () {
              // 이메일 인증 보내기 api
            },
            child: const Button(
              text: "+",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("회원정보 변경"),
          ),
          const InputBoxWidget(name: "현재 비밀번호"),
          const InputBoxWidget(name: "변경 비밀번호"),
          const InputBoxWidget(name: "변경 비밀번호 재입력"),
          const SizedBox(
            height: 100,
          ),
          InkWell(
            onTap: () {
              // 비밀번호 변경 완료
            },
            child: const Button(
              text: "변경",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
        ],
      ),
    );
  }
}
