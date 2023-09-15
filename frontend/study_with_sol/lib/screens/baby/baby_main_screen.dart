import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/account/account_list_screen.dart';
import 'package:study_with_sol/screens/baby/baby_study_screen.dart';
import 'package:study_with_sol/screens/baby/baby_timer_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class BabyMain extends StatefulWidget {
  const BabyMain({super.key});

  @override
  State<BabyMain> createState() => _BabyMain();
}

class _BabyMain extends State<BabyMain> {
  late Future<List<BabyMain>> homeworkList; // 숙제 리스트 불러오기

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: [
            const Text('Study with sol'),
            IconButton(onPressed: () {}, icon: const Icon(Icons.settings)),
          ],
        ),
      ),
      body: Column(
        children: [
          Container(
              decoration: const BoxDecoration(
                color: Colors.blue,
              ),
              child: const Row(
                children: [
                  Text("전교 1등 하기"),
                  Text("지금까지 10,000원"),
                ],
              )),
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
                )
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
          FutureBuilder(
            // 오늘 숙제 리스트
            future: homeworkList,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  children: [
                    // slide
                    for (var homework in snapshot.data!) Container(),
                  ],
                );
              }
              return Container();
            },
          ),
        ],
      ),
    );
  }
}
