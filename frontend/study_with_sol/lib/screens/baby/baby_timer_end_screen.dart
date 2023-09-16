import 'package:flutter/material.dart';

class BabyTimerEnd extends StatefulWidget {
  const BabyTimerEnd({super.key});

  @override
  State<BabyTimerEnd> createState() => _BabyTimerEndState();
}

class _BabyTimerEndState extends State<BabyTimerEnd> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('타이머'),
      ),
      body: Column(
        children: [
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("누구한테 자랑할까요"),
          ),
          const Text("엄마"), // dropbox
        ],
      ),
    );
  }
}
