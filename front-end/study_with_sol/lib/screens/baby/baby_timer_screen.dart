import 'dart:async';

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/screens/baby/baby_timer_end_screen.dart';
import 'package:intl/intl.dart';

// 타이머 만들기
class BabyTimer extends StatefulWidget {
  const BabyTimer({super.key});

  @override
  _BabyTimerState createState() => _BabyTimerState();
}

class _BabyTimerState extends State<BabyTimer> {
  int secondsElapsed = 0;
  Timer? timer;

  @override
  void initState() {
    super.initState();
    startTimer();
  }

  void startTimer() {
    timer = Timer.periodic(const Duration(seconds: 1), (timer) {
      setState(() {
        secondsElapsed++;
      });
    });
  }

  void stopTimerAndSaveTime() async {
    timer?.cancel();
    final prefs = await SharedPreferences.getInstance();
    await prefs.setInt('study_time', secondsElapsed);
    Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => const BabyTimerEnd(),
    ));
  }

  @override
  Widget build(BuildContext context) {
    final hours = secondsElapsed ~/ 3600;
    final minutes = (secondsElapsed % 3600) ~/ 60;
    final seconds = secondsElapsed % 60;

    return Scaffold(
      appBar: AppBar(
        title: const Text("Study Timer"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text("오늘 날짜 텍스트"),
            Text(DateTime.now().toString()), // 현재 날짜 및 시간 표시
            Text(DateFormat('yyyy.MM.dd')
                .format(DateTime.now())), // "yyyy.MM.dd" 형식으로 날짜 포맷
            Text(DateFormat('HH:mm:ss')
                .format(DateTime.now())), // "HH:mm:ss" 형식으로 시간 포맷
            Text("경과 시간: $hours시간 $minutes분 $seconds초"),
            ElevatedButton(
              onPressed: () {
                stopTimerAndSaveTime();
              },
              child: const Text("끝내기 버튼"),
            ),
            const Text("끝내기 버튼을 누르지 않으면 공부시간 등록이 되지 않습니다"),
          ],
        ),
      ),
    );
  }
}

class NextScreen extends StatelessWidget {
  const NextScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Next Screen"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text("지금까지 공부한 시간:"),
            FutureBuilder<int>(
              future: SharedPreferences.getInstance().then((prefs) {
                return prefs.getInt('study_time') ?? 0;
              }),
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  final seconds = snapshot.data!;
                  final hours = seconds ~/ 3600;
                  final minutes = (seconds % 3600) ~/ 60;
                  final secondsRemaining = seconds % 60;

                  return Text("$hours시간 $minutes분 $secondsRemaining초");
                } else {
                  return const CircularProgressIndicator();
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
