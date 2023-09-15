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
            color: bgColor, borderRadius: BorderRadius.circular(45)),
        child: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: 20,
            horizontal: 50,
          ),
          child: Text(
            text,
            style: TextStyle(
              color: textColor,
              fontSize: 22,
            ),
          ),
        ),
      ),
    );
  }
}
