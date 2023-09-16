import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/screens/account/account_list_screen.dart';
import 'package:study_with_sol/screens/account/option_screen.dart';
import 'package:study_with_sol/screens/baby/baby_study_screen.dart';
import 'package:study_with_sol/screens/baby/baby_timer_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class BabyMain extends StatefulWidget {
  const BabyMain({Key? key}) : super(key: key);

  @override
  State<BabyMain> createState() => _BabyMainState();
}

class _BabyMainState extends State<BabyMain> {
  final Dio _dio = Dio();
  String accountName = '';
  double balance = 0;
  String accountNumber = '';
  int id = 0;
  List<TransactionInfo> transactionList = [];
  List<Map<String, dynamic>> longTermGoals = [];

  // API에서 받아온 정보
  List<Map<String, dynamic>> homeworkList = [];

  String todayStudyTime = '0:00:00'; // 오늘의 공부 시간 표시

  @override
  void initState() {
    super.initState();
    // 페이지가 로딩되기 전에 API를 불러옵니다.
    loadLongTermGoals();
    loadAccountInfo();
    loadHomeworkList();
    loadTodayStudyTime(); // 오늘의 공부 시간을 불러옵니다.
  }

  @override
  void dispose() {
    _dio.close();
    super.dispose();
  }

  Future<void> loadLongTermGoals() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/goal/child';

    try {
      final response = await _dio.get(apiUrl,
          options: Options(headers: {
            'Authorization': 'Bearer ${prefs.getString('token')}',
          }));

      if (response.statusCode == 200) {
        final jsonResponse = response.data as List<dynamic>;
        print("API 호출 성공");
        setState(() {
          longTermGoals = List<Map<String, dynamic>>.from(jsonResponse);
        });
      } else {
        print("API 호출 실패: ${response.statusCode}");
        print("에러 응답: ${response.data}");
      }
    } catch (e) {
      print('Error: $e');
    }
  }

  Future<void> loadAccountInfo() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/accounts/balance';
    try {
      final response = await _dio.get(apiUrl,
          options: Options(headers: {
            'Authorization': 'Bearer ${prefs.getString('token')}',
          }));

      if (response.statusCode == 200) {
        final jsonResponse = response.data['data'];

        setState(() {
          id = jsonResponse['id'];
          accountName = jsonResponse['name'];
          balance = jsonResponse['balance'];
          accountNumber = jsonResponse['accountNumber'];
        });
      } else {
        print("API 호출 실패: ${response.statusCode}");
        print("에러 응답: ${response.data}");
      }
    } catch (e) {
      print('Error: $e');
    }
  }

  Future<void> loadHomeworkList() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/study/list';

    final currentDate = DateTime.now();
    final formattedDate =
        "${currentDate.year}-${currentDate.month.toString().padLeft(2, '0')}-${currentDate.day.toString().padLeft(2, '0')}";
    print(formattedDate);
    final requestData = {
      "deadline": formattedDate,
    };

    try {
      final response = await _dio.post(apiUrl,
          data: requestData,
          options: Options(headers: {
            'Authorization': 'Bearer ${prefs.getString('token')}',
          }));

      if (response.statusCode == 200) {
        final jsonResponse = response.data['studyList'];
        print("API 호출 성공");
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

  Future<void> loadTodayStudyTime() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    final today = DateTime.now();
    final todayKey =
        "${today.year}-${today.month}-${today.day}"; // 오늘의 날짜를 키로 사용
    final todayStudyTimeMinutes = prefs.getInt(todayKey) ?? 0; // 기본값은 0

    final hours = todayStudyTimeMinutes ~/ 60;
    final minutes = todayStudyTimeMinutes % 60;
    const seconds = 0; // 초는 0으로 초기화

    setState(() {
      todayStudyTime =
          '$hours:${minutes.toString().padLeft(2, '0')}:${seconds.toString().padLeft(2, '0')}';
    });
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
      body: SingleChildScrollView(
        child: Column(
          children: [
            const SizedBox(
              height: 10,
            ),
            Container(
              decoration: const BoxDecoration(
                color: Colors.blue,
              ),
              child: const Text("장기 목표"),
            ),
            for (var goal in longTermGoals)
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
                        goal['content'] ?? '',
                        style: const TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 18,
                        ),
                      ),
                      Text(
                        "총 ${goal['money'] ?? 0}원",
                        style: const TextStyle(
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
                      Text("$balance원"),
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
                      Text(
                        todayStudyTime,
                        style: const TextStyle(
                          color: Colors.white,
                        ),
                      ),
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
                // 만들기
                decoration: const BoxDecoration(
                  color: Colors.grey,
                ),
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            homework['content'] ?? '',
                            style: const TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold,
                              fontSize: 18,
                            ),
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: [
                              Text(
                                "${homework['payMoney'] ?? 0}원",
                                style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                ),
                              ),
                              const SizedBox(
                                width: 20,
                              ),
                              Text(
                                homework['payState'] ?? '',
                                style: const TextStyle(
                                  color: Colors.white,
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18,
                                ),
                              ),
                            ],
                          ),
                        ],
                      ),
                      Column(
                        children: [
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
                              text: "저금",
                              bgColor: Colors.white,
                              textColor: Colors.black,
                            ),
                          ),
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
                              text: "용돈",
                              bgColor: Colors.white,
                              textColor: Colors.black,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ),
          ],
        ),
      ),
    );
  }
}
