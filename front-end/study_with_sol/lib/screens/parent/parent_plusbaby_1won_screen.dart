import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/parent/parent_plusbaby_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class PlusBaby1Won extends StatelessWidget {
  const PlusBaby1Won({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(child: Text('자녀 연결')),
      ),
      body: const Center(
        child: Column(
          children: [
            Expanded(flex: 3, child: Notice()),
            Expanded(flex: 4, child: Content()),
            Expanded(flex: 3, child: Check()),
          ]
        ),
      ),
    );
  }
}

class Notice extends StatelessWidget {
  const Notice({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: const Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            '자녀의 계좌로 1원을 송금했습니다.',
            style: TextStyle(
              fontSize: 18,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
          SizedBox(height: 8), // 문장 사이 간격 조절 (원하는 값으로 변경)
          Text(
            '계좌의 입금내역에 표시된',
            style: TextStyle(
              fontSize: 16,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
          Text(
            '4글자를 입력해주세요.',
            style: TextStyle(
              fontSize: 16,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
          // 원하는 만큼 추가 문장을 추가할 수 있습니다.
        ],
      ),
    );
  }
}

class Content extends StatelessWidget {
  const Content({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: FractionallySizedBox(
        widthFactor: 0.9, // 부모 위젯의 90%로 설정
        alignment: Alignment.topCenter,
        child: InputBoxBaby(name: "X X X X"),
      ),
    );
  }
}

class Check extends StatelessWidget {
  const Check({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.topCenter,
      child: const Button(
        text: "연결 >",
        bgColor: Color.fromARGB(255, 221, 248, 255),
        textColor: Colors.black,
      ),
    );
  }
}

class InputBoxBaby extends StatelessWidget {
  final String name;

  const InputBoxBaby({Key? key, required this.name}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(5), // 원하는 radius 값으로 변경
        border: Border.all(
          color: const Color.fromARGB(255, 1, 144, 184), // 테두리 색상 설정
          width: 2, // 테두리 두께 설정
        ),
      ),
      // width: 350,
      height: 60,
      padding: const EdgeInsets.all(16),
      child: Align(
        alignment: Alignment.topCenter,
        child: TextField(
          decoration: InputDecoration(
            hintText: name,
            border: InputBorder.none, // 텍스트 필드의 기본 테두리를 제거
          ),
        ),
      ),
    );
  }
}