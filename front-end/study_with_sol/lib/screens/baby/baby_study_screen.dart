import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:table_calendar/table_calendar.dart';
import 'package:dio/dio.dart';

class BabyStudy extends StatefulWidget {
  const BabyStudy({Key? key}) : super(key: key);

  @override
  _BabyStudyState createState() => _BabyStudyState();
}

class _BabyStudyState extends State<BabyStudy> {
  final CalendarFormat _calendarFormat = CalendarFormat.month;
  DateTime _focusedDay = DateTime.now();
  DateTime? _selectedDay;
  List<Map<String, dynamic>> studyList = [];
  String selectedDate = '';

  @override
  void initState() {
    super.initState();
    // 페이지가 로딩되기 전에 API를 불러옵니다.
    loadStudyList();
  }

  Future<void> loadStudyList() async {
    // TODO: Dio를 사용하여 API 호출 및 데이터 받아오기
    SharedPreferences prefs = await SharedPreferences.getInstance();
    print(_selectedDay.toString());

    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/study/list'; // API 엔드포인트 수정
    final requestData = {
      "deadline": selectedDate, // 선택된 날짜를 요청에 포함
    };

    try {
      final response = await Dio().post(
        apiUrl,
        data: requestData,
        options: Options(headers: {
          'Authorization': 'Bearer ${prefs.getString('token')}',
        }),
      );

      if (response.statusCode == 200) {
        final jsonResponse = response.data['studyList'];
        setState(() {
          studyList = List<Map<String, dynamic>>.from(jsonResponse);
        });
      } else {
        print("API 호출 실패: ${response.statusCode}");
        print("에러 응답: ${response.data}");
      }
    } catch (e) {
      print('Error: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('숙제 관리'),
      ),
      body: Column(
        children: [
          TableCalendar(
            calendarFormat: _calendarFormat,
            headerStyle: const HeaderStyle(
              formatButtonVisible: false,
              titleCentered: true,
            ),
            focusedDay: _focusedDay,
            firstDay: DateTime(2020, 9, 1),
            lastDay: DateTime(2033, 9, 30),
            locale: 'ko-KR',
            daysOfWeekHeight: 30,
            selectedDayPredicate: (day) {
              // 선택된 날짜 표시를 위한 조건
              return isSameDay(_selectedDay, day);
            },
            onDaySelected: (selectedDay, focusedDay) {
              setState(() {
                _selectedDay = selectedDay;
                _focusedDay = focusedDay; // 포커스 이동
                selectedDate = DateFormat('yyyy-MM-dd')
                    .format(selectedDay); // 선택된 날짜를 원하는 형식으로 포맷
              });
              loadStudyList(); // 날짜가 선택될 때마다 API 다시 호출
            },
          ),
          // TODO: studyList를 화면에 출력하는 부분 추가
          Expanded(
            child: ListView.builder(
              itemCount: studyList.length,
              itemBuilder: (context, index) {
                final studyItem = studyList[index];
                return ListTile(
                  title: Text(studyItem['content'] ?? ''),
                  subtitle: Text("총 ${studyItem['payMoney'] ?? 0}원"),
                  // TODO: 여기에 슬라이더 또는 버튼 추가
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
