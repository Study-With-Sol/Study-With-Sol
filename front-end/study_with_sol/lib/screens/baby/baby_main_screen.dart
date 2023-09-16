import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/screens/account/account_list_screen.dart';
import 'package:study_with_sol/screens/account/option_screen.dart';
import 'package:study_with_sol/screens/baby/baby_study_screen.dart';
import 'package:study_with_sol/screens/baby/baby_timer_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class BabyMain extends StatefulWidget {
  const BabyMain({super.key});

  @override
  State<BabyMain> createState() => _BabyMain();
}

class _BabyMain extends State<BabyMain> {
  final Dio _dio = Dio();
  String accountName = '';
  double balance = 0;
  String accountNumber = '';
  int id = 0;
  List<TransactionInfo> transactionList = [];

  // API에서 받아온 정보
  List<Map<String, dynamic>> homeworkList = [];

  @override
  void initState() {
    super.initState();
    // 페이지가 로딩되기 전에 API를 불러옵니다.
    loadHomeworkList();
  }

  @override
  void dispose() {
    _dio.close();
    super.dispose();
  }

  Future<void> loadHomeworkList() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl = 'http://your-api-url-here.com'; // API 엔드포인트 수정

    try {
      final response = await _dio.get(apiUrl,
          options: Options(headers: {
            'Authorization': 'Bearer ${prefs.getString('token')}',
          }));

      if (response.statusCode == 200) {
        final jsonResponse = response.data;

        setState(() {
          homeworkList = List<Map<String, dynamic>>.from(jsonResponse);
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
        title: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            const Text('Study with sol'),
            IconButton(
              onPressed: () {
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (BuildContext context) {
                      return const Option(); // 이동할 화면의 위젯
                    },
                  ),
                );
              },
              icon: const Icon(Icons.settings),
            ),
          ],
        ),
      ),
      body: Column(
        children: [
          const SizedBox(
            height: 10,
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Padding(
              padding: EdgeInsets.all(8.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    "전교 1등 하기",
                    style: TextStyle(
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                      fontSize: 18,
                    ),
                  ),
                  Text(
                    "총 10,000원",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 16,
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(
            height: 10,
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: Column(
              children: [
                const Text("내 계좌 잔액"),
                Row(
                  children: [
                    const Text("30,000원"),
                    InkWell(
                      onTap: () {
                        // 계좌정보화면으로
                        Navigator.of(context).push(
                          MaterialPageRoute(
                            builder: (BuildContext context) {
                              return const AccountList(); // 이동할 화면의 위젯
                            },
                          ),
                        );
                      },
                      child: const Button(
                        text: "계좌 정보",
                        bgColor: Colors.white,
                        textColor: Colors.black,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: Column(
              children: [
                const Text("오늘의 공부 시간"),
                Row(
                  children: [
                    const Text("30:00:01"),
                    InkWell(
                      onTap: () {
                        // 타이머화면으로
                        Navigator.of(context).push(
                          MaterialPageRoute(
                            builder: (BuildContext context) {
                              return const BabyTimer(); // 이동할 화면의 위젯
                            },
                          ),
                        );
                      },
                      child: const Button(
                        text: "타이머",
                        bgColor: Colors.white,
                        textColor: Colors.black,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          InkWell(
            onTap: () {
              // 계좌정보화면으로
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (BuildContext context) {
                    return const BabyStudy(); // 이동할 화면의 위젯
                  },
                ),
              );
            },
            child: const Button(
              text: "숙제 더보기",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("오늘 숙제"),
          ),
          for (var homework in homeworkList)
            Container(
              decoration: const BoxDecoration(
                color: Colors.blue,
              ),
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      homework['content'] ?? '',
                      style: const TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                        fontSize: 18,
                      ),
                    ),
                    Text(
                      "총 ${homework['money'] ?? 0}원",
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 16,
                      ),
                    ),
                  ],
                ),
              ),
            ),
        ],
      ),
    );
  }
}
