import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class PlusBaby extends StatelessWidget {
  const PlusBaby({super.key});

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
            '아이디로 자녀를 찾은 후',
            style: TextStyle(
              fontSize: 18,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
          Text(
            '확인 버튼을 누르세요!!',
            style: TextStyle(
              fontSize: 18,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
          SizedBox(height: 5), // 문장 사이 간격 조절 (원하는 값으로 변경)
          Text(
            '자녀가 수락하면 등록됩니다.',
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
    return Column(
      // mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const FractionallySizedBox(
          widthFactor: 0.9, // 부모 위젯의 80%로 설정
          child: InputBoxBaby(name: "관계"),
        ),
        const SizedBox(height: 12),
        Stack(
          alignment: Alignment.centerRight, // 검색 버튼을 오른쪽에 정렬
          children: [
            const FractionallySizedBox(
              widthFactor: 0.9, // 부모 위젯의 80%로 설정
              child: InputBoxBaby(name: "자녀 아이디"),
            ),
            InkWell(
              onTap: () {
                // 검색
              },
              child: Container(
                margin: const EdgeInsets.only(right: 7), // 오른쪽 마진 추가
                height: 47,
                width: 90,
                child: const Button (
                  text: "Search",
                  bgColor: Colors.white,
                  textColor: Colors.black,
                ),
              ),
            ),
          ],
        ),
        const SizedBox(height: 30),
        const Text(
          '김신한 님이 맞습니까?',
            style: TextStyle(
              fontSize: 16,
              color: Colors.black, // 텍스트 색상 설정
            ),
          ),
      ],
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
        text: "확인 >",
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
