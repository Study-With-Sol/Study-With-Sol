import 'package:flutter/material.dart';

class InputBoxWidget extends StatelessWidget {
  final String name;
  final TextEditingController? controller; // 컨트롤러 추가

  const InputBoxWidget({
    Key? key, // key 추가
    required this.name,
    this.controller, // 컨트롤러 추가
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(20.0),
      child: TextField(
        controller: controller, // 컨트롤러 연결
        decoration: InputDecoration(
          labelText: name, // placeholder
        ),
      ),
    );
  }
}
