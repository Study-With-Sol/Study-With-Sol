import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/account/account_list_screen.dart';
import 'package:study_with_sol/screens/baby/baby_study_screen.dart';
import 'package:study_with_sol/screens/parent/parent_babyoption_screen.dart';
import 'package:study_with_sol/screens/parent/parent_study_screen.dart';
import 'package:study_with_sol/screens/reports/report_study.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class ParentMain extends StatefulWidget {
  const ParentMain({super.key});

  @override
  State<ParentMain> createState() => _ParentMain();
}

class _ParentMain extends State<ParentMain> {
  late Future<List<ParentMain>> homeworkList; // 숙제 리스트 불러오기

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
          const Row(
            children: [
              // future builder로 자녀 수만큼
            ],
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
                  )
                ],
              )),
          Row(
            children: [
              // button 3개
              InkWell(
                onTap: () {
                  // 계좌정보화면으로
                  Navigator.of(context).push(
                    MaterialPageRoute(
                      builder: (BuildContext context) {
                        return const ParentStudy(); // 이동할 화면의 위젯
                      },
                    ),
                  );
                },
                child: const Button(
                  text: "숙제 관리",
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
                        return const ReportStudy(); // 이동할 화면의 위젯
                      },
                    ),
                  );
                },
                child: const Button(
                  text: "레포트",
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
                        return const BabyOption(); // 이동할 화면의 위젯
                      },
                    ),
                  );
                },
                child: const Button(
                  text: "용돈 설정",
                  bgColor: Colors.white,
                  textColor: Colors.black,
                ),
              ),
            ],
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("완료 숙제"),
          ),
          FutureBuilder(
            // 계좌 내역 보여주기
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
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("학습량 인증"),
          ),
          FutureBuilder(
            // 계좌 내역 보여주기
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
