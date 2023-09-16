import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/screens/account/account_list_screen.dart';
import 'package:study_with_sol/screens/account/option_screen.dart';
import 'package:study_with_sol/screens/baby/baby_study_screen.dart';
import 'package:study_with_sol/screens/parent/parent_babyoption_screen.dart';
import 'package:study_with_sol/screens/parent/parent_plusbaby_screen.dart';
import 'package:study_with_sol/screens/parent/parent_study_screen.dart';
import 'package:study_with_sol/screens/reports/report_study.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class ParentMain extends StatefulWidget {
  const ParentMain({Key? key}) : super(key: key);

  @override
  State<ParentMain> createState() => _ParentMainState();
}

class _ParentMainState extends State<ParentMain> {
  String token = ''; // token을 나타낼 변수
  List<Map<String, dynamic>> childrenData = []; // 부모 별칭 데이터를 가져오는 함수 호출
  List<Map<String, dynamic>> studyList = []; // 학습 목록 데이터
  String selectedChildName = ''; // 선택된 자녀의 이름을 저장할 변수
  int myAccountBalance  = 0;

  @override
  void initState() {
    super.initState();
    fetchChildrenData(); // 자녀 데이터 가져오기
    getMyAccountBalance(); //주계좌 잔액 조회
    getStudyList();
  }

  Future<void> fetchChildrenData() async {
    // Dio 인스턴스 생성
    final _dio = Dio();
    final prefs = await SharedPreferences.getInstance();
    final savedToken = prefs.getString('token') ?? '';

    // get 요청 보낼 URL 설정
    const url = 'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/users/child';

    try {
      final response = await _dio.get(url, options: Options(headers: {'Authorization': 'Bearer $savedToken'}));

      if (response.statusCode == 200) {
        final data = response.data;
        final userList = data['data'] as List;

        setState(() {
          childrenData = userList.cast<Map<String, dynamic>>();

          // 리스트가 비어있지 않고, 첫 번째 객체가 존재할 때만 처리
          if (childrenData.isNotEmpty) {
            final firstChild = childrenData.first;
            selectedChildName = firstChild['name'].toString();
          }
        });
      } else {
        // 요청이 실패한 경우
        print('Failed to load user data');
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }

  void goPlusBaby() async {
    Navigator.of(context).push(MaterialPageRoute(
      builder: (context) => const PlusBaby(),
    ));
  }




  Future<void> getMyAccountBalance() async {
    // Dio 인스턴스 생성
    final _dio = Dio();
    final prefs = await SharedPreferences.getInstance();
    final savedToken = prefs.getString('token') ?? '';

    print("savedToken ::"+ savedToken);

    // POST 요청 보낼 URL 설정
    const url = 'ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/accounts/balance';


    try {
      final response = await _dio.get(url,
          options: Options(headers: {
            'Authorization': 'Bearer ${savedToken}',
          }));


      if (response.statusCode == 200) {
        final data = response.data;
        final balance = data['data']['balance']; // 잔액 가져오기

        // UI에 잔액 표시를 위해 setState를 사용하여 상태 업데이트
        setState(() {
          myAccountBalance = balance; // myAccountBalance는 클래스 멤버 변수로 선언되어야 함
        });
      } else {
        // 요청이 실패한 경우
        print('Failed to load account balance');
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }

  Future<void> getStudyList() async {
    // Dio 인스턴스 생성
    final _dio = Dio();
    final prefs = await SharedPreferences.getInstance();
    final savedToken = prefs.getString('token') ?? '';

    // get 요청 보낼 URL 설정
    const url = 'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/study/list/need-check';

    try {
      final response = await _dio.post(url, options: Options(headers: {'Authorization': 'Bearer $savedToken'}));

      if (response.statusCode == 200) {
        final data = response.data;
        final studyListData = data['studyList'] as List;

        setState(() {
          studyList = studyListData.cast<Map<String, dynamic>>();
          print(studyList.toString());

        });
      } else {
        // 요청이 실패한 경우
        print('Failed to load user data');
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }


  // "거절" 버튼을 눌렀을 때 수행할 작업
  void reject(int studyId) async {
    final url = 'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/study/money/$studyId';

    try {
      final response = await Dio().patch(url, data: {"state": false}); // "거절" 요청 보내기

      if (response.statusCode == 200) {
        // 성공한 경우 처리
        print('Study rejected successfully.');
      } else {
        // 요청이 실패한 경우 처리
        print('Failed to reject study.');
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }

  // "완료" 버튼을 눌렀을 때 수행할 작업
  void complete(int studyId) async {
    final url = 'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/study/money/$studyId';

    try {
      final response = await Dio().patch(url, data: {"state": true}); // "완료" 요청 보내기

      if (response.statusCode == 200) {
        // 성공한 경우 처리
        print('Study completed successfully.');
      } else {
        // 요청이 실패한 경우 처리
        print('Failed to complete study.');
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: [
            const Text('Study with sol'),
            IconButton(onPressed: () {
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (BuildContext context) {
                    return Option(); // 이동할 화면의 위젯
                  },
                ),
              );
            }, icon: const Icon(Icons.settings)),
          ],
        ),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              children: [
                IconButton(
                  onPressed: () {
                    goPlusBaby();
                  },
                  icon: const Icon(Icons.add), // + 아이콘
                ),
                ClipOval(
                  child: Material(
                    color: Colors.transparent,
                    child: InkWell(
                      onTap: () {
                        // 프로필 사진을 눌렀을 때 할 작업을 여기에 추가하세요
                      },
                      child: SizedBox(
                        width: 72,
                        height: 72,
                        child: Center(
                          child: Icon(
                            Icons.account_circle,
                            size: 80,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                const SizedBox(width: 10), // 간격 조정
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      selectedChildName, // 선택된 자녀의 이름 표시
                      style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
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
                  const Text("내 계좌 잔액"),
                  Row(
                    children: [
                      Text('내 계좌 잔액: ${myAccountBalance.toStringAsFixed(2)}원',
                        style: TextStyle(fontSize: 20),),
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
          Expanded(
            child: ListView.builder(
              itemCount: studyList.length,
              itemBuilder: (context, index) {
                final study = studyList[index];
                final studyId = study['studyId'] as int; // study_id 가져오기
                final content = study['content'] as String;
                final payMoney = study['payMoney'] as int;
                final isDone = study['isDone'] as bool;
                return Container(
                  decoration: BoxDecoration(
                    border: Border(
                      // 테두리 스타일 지정
                      top: BorderSide(color: Colors.black, width: 1.0),
                      bottom: BorderSide(color: Colors.black, width: 1.0),
                      left: BorderSide(color: Colors.black, width: 1.0),
                      right: BorderSide(color: Colors.black, width: 1.0),
                    ),
                    borderRadius: BorderRadius.all(Radius.circular(8.0)), // 테두리의 모서리를 둥글게 만듦
                    // 다른 스타일링 속성들을 추가로 지정할 수 있습니다.
                  ),
                  padding: EdgeInsets.all(8.0), // 요소 주변의 간격 조정
                  margin: EdgeInsets.symmetric(vertical: 8.0), // 요소 간의 간격 조정
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      ListTile(
                        title: Text(content),
                        subtitle: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text("금액: $payMoney원"),
                            // Text("완료 여부: ${isDone ? '완료' : '미완료'}"),
                            // Text("결제 상태: $payState"),
                            // Text("마감일: $formattedDeadline"),
                          ],
                        ),
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end, // 오른쪽 정렬
                        children: [
                          IconButton(
                            icon: Icon(Icons.close), // 왼쪽 버튼 아이콘을 "거절" 아이콘으로 변경
                            onPressed: () {
                              reject(studyId);
                              // "거절" 버튼 클릭 시 수행할 작업 추가
                            },
                          ),
                          IconButton(
                            icon: Icon(Icons.check), // 오른쪽 버튼 아이콘을 "완료" 아이콘으로 변경
                            onPressed: () {
                              complete(studyId);
                              // "완료" 버튼 클릭 시 수행할 작업 추가
                            },
                          ),
                        ],
                      ),

                    ],
                  ),
                );
              },
            ),
          ),


          // FutureBuilder(
          //   // 계좌 내역 보여주기
          //   future: homeworkList,
          //   builder: (context, snapshot) {
          //     if (snapshot.hasData) {
          //       return Column(
          //         children: [
          //           // slide
          //           for (var homework in snapshot.data!) Container(),
          //         ],
          //       );
          //     }
          //     return Container();
          //   },
          // ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("학습량 인증"),
          ),
          // FutureBuilder(
          //   // 계좌 내역 보여주기
          //   future: homeworkList,
          //   builder: (context, snapshot) {
          //     if (snapshot.hasData) {
          //       return Column(
          //         children: [
          //           // slide
          //           for (var homework in snapshot.data!) Container(),
          //         ],
          //       );
          //     }
          //     return Container();
          //   },
          // ),
        ],
      ),
    );
  }
}
