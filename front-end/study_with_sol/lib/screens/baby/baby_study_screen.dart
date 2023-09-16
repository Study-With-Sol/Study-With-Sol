// calender 라이브러리 사용해서 구현

import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class BabyStudy extends StatelessWidget {
  const BabyStudy({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('로그인'),
      ),
      body: TableCalendar(
        headerStyle: const HeaderStyle(
          formatButtonVisible: false,
          titleCentered: true,
        ),
        focusedDay: DateTime.now(),
        firstDay: DateTime(2020, 9, 1),
        lastDay: DateTime(2033, 9, 30),
        locale: 'ko-KR',
        daysOfWeekHeight: 30,
      ),
    );
  }
}
