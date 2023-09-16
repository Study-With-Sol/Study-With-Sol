// st : Flutter Stateless Widget 만들기

import 'package:flutter/material.dart';

class Button extends StatelessWidget {
  final String text;
  final Color bgColor;
  final Color textColor;

  const Button(
      {super.key,
      required this.text,
      required this.bgColor,
      required this.textColor}); // 프로퍼티
  // 생성자 함수 (constructors)
  //Button({required this.text, required this.bgColor, required this.textColor});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      child: Container(
        decoration: BoxDecoration(
          color: bgColor,
          borderRadius: BorderRadius.circular(15),
          boxShadow: const [
            BoxShadow(
              color: Color.fromARGB(255, 166, 166, 166), // 그림자 색상 설정
              offset: Offset(0, 2), // 그림자 위치 설정
              blurRadius: 0, // 그림자 흐림 정도 설정
              spreadRadius: 0, // 그림자 확산 정도 설정
            ),
          ],
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: 15,
            horizontal: 20,
          ),
          child: Text(
            text,
            style: TextStyle(
              color: textColor,
              fontSize: 15,
            ),
          ),
        ),
      ),
    );
  }
}
