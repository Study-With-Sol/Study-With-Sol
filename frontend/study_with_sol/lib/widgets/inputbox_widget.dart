import 'package:flutter/material.dart';

class InputBoxWidget extends StatelessWidget {
  final String name;

  const InputBoxWidget({
    super.key,
    required this.name,
  });

  @override
  Widget build(BuildContext context) {
    return const Padding(
      padding: EdgeInsets.all(20.0),
      child: TextField(
        decoration: InputDecoration(
          labelText: '아이디', // placeholder
        ),
      ),
    );
  }
}
