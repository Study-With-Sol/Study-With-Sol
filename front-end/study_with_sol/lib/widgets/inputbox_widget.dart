import 'package:flutter/material.dart';

class InputBoxWidget extends StatelessWidget {
  final String name;
  final TextEditingController? controller; // 컨트롤러 추가
  final bool isPassword; // 비밀번호 입력 여부 추가

  const InputBoxWidget({
    Key? key, // key 추가
    required this.name,
    this.controller, // 컨트롤러 추가
    this.isPassword = false, // 기본값은 false로 설정 (일반 텍스트 필드)
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: TextField(
        controller: controller, // 컨트롤러 연결
        obscureText: isPassword, // 비밀번호 입력 필드로 설정
        decoration: InputDecoration(
          labelText: name, // placeholder
        ),
      ),
    );
  }
}
