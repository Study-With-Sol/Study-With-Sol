import 'package:flutter/material.dart';

void main() {
  runApp(const App());
}

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        backgroundColor: Colors.red.shade200, // 투명도
        body: Center(
          child: Text('study with sol'),
        ),
      ),
    );
  }
}
