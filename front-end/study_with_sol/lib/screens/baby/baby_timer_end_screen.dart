import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../main.dart'; // 패키지 임포트 추가

class BabyTimerEnd extends StatefulWidget {
  const BabyTimerEnd({
    super.key,
  });

  @override
  State<BabyTimerEnd> createState() => _BabyTimerEndState();
}

class _BabyTimerEndState extends State<BabyTimerEnd> {
  String dropdownValue = ''; // 초기에는 선택하지 않은 상태로 시작
  List<String> itemList = []; // itemList 초기화
  String elapsedTimeString = '00:00:00'; // 경과 시간을 나타낼 변수
  String token = ''; // token을 나타낼 변수
  List<Map<String, dynamic>> parentData = []; //부모 별칭 데이터를 가져오는 함수 호출
  String selectedOption = 'DIRECT'; // 초기에 "용돈"이 선택되도록 설정
  int parentId = 0; // 선택한 부모의 userId를 저장할 변수

  // 컨트롤러를 클래스 수준에서 선언
  final TextEditingController contentController = TextEditingController();

  @override
  void initState() {
    super.initState();
    loadElapsedStudyTime(); // 경과 시간을 가져오는 함수 호출
    fetchParentData(); // 부모 별칭 데이터를 가져오는 함수 호출
  }

  void loadElapsedStudyTime() async {
    final prefs = await SharedPreferences.getInstance();
    final elapsedSeconds = prefs.getInt('study_time') ?? 0;

    final hours = elapsedSeconds ~/ 3600;
    final minutes = (elapsedSeconds % 3600) ~/ 60;
    final seconds = elapsedSeconds % 60;

    setState(() {
      elapsedTimeString =
          '$hours시간 $minutes분 $seconds초'; // 경과 시간을 문자열로 변환하여 변수에 저장
    });
  }

  Future<void> fetchParentData() async {
    // Dio 인스턴스 생성
    final dio = Dio();
    final prefs = await SharedPreferences.getInstance();
    final savedToken = prefs.getString('token') ?? '';

    print("savedToken ::$savedToken");

    // POST 요청 보낼 URL 설정
    const url = 'http://192.168.8.199:8080/users/parent';

    try {
      final response = await dio.get(url,
          options: Options(headers: {
            'Authorization': 'Bearer $savedToken',
          }));

      if (response.statusCode == 200) {
        final data = response.data;
        final userList = data['data'] as List;

        setState(() {
          parentData = userList.cast<Map<String, dynamic>>();
          print(parentData.toString());

          // 부모 데이터의 별칭을 itemList로 설정
          itemList =
              parentData.map((item) => item['alias'].toString()).toList();

          // 초기 dropdownValue 설정 (첫 번째 항목 선택)
          if (itemList.isNotEmpty) {
            dropdownValue = itemList[0];
            final selectedParent = parentData.firstWhere(
              (parent) => parent['alias'] == dropdownValue,
              orElse: () => <String, dynamic>{},
            );
            parentId = selectedParent['userId'] ?? 0;
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

  void handleRadioValueChanged(String? value) {
    setState(() {
      selectedOption = value ?? '';
    });
  }

  Future<void> readTodayElapsedStudyTime() async {
    final prefs = await SharedPreferences.getInstance();

    // 오늘 날짜를 문자열로 생성 (예: "2023-09-15")
    final now = DateTime.now();
    final formattedDate =
        "${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}";

    final elapsedMinutes = prefs.getInt(formattedDate) ?? 0;

    print('오늘의 경과 시간 (분): $elapsedMinutes');
  }

  Future<void> sendAction() async {
    if (selectedOption.isNotEmpty) {
      // final prefs = await SharedPreferences.getInstance();
      // final parentId = prefs.getInt('parent_id') ?? 0; // parentData의 UserId 대신 SharedPreferences에서 parentId 가져오기
      final content = contentController.text; // 메모 등록 내용
      final time = elapsedTimeString; // 경과 시간
      final wantPay = selectedOption; // 선택된 옵션

      // POST 요청을 보낼 URL 설정
      const url = 'http://192.168.8.199:8080/timer';

      // POST 요청에 보낼 데이터 구성
      final requestData = {
        'parentId': parentId,
        'content': content,
        'time': time,
        'wantPay': wantPay,
      };

      print(requestData.toString());

      saveElapsedStudyTime();

      await readTodayElapsedStudyTime();

      // Dio 인스턴스 생성
      final dio = Dio();

      try {
        final response = await dio.post(
          url,
          data: requestData,
          options: Options(
            headers: {
              'Authorization': 'Bearer $token',
              'Content-Type': 'application/json',
            },
          ),
        );

        if (response.statusCode == 200) {
          // 요청이 성공한 경우
          print('Request successful: ${response.data}');
          // 여기에서 응답 데이터를 처리할 수 있습니다.

          // 다음 화면으로 이동 예제
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => const MyHomePage(), // 로그인 후 페이지로 이동
            ),
          );
        } else {
          // 요청이 실패한 경우
          print('Request failed with status code ${response.statusCode}');
        }
      } catch (e) {
        // 오류 처리
        print('Error: $e');
      }
    } else {
      // 라디오 버튼이 선택되지 않은 경우에 대한 처리를 여기에 추가하세요.
      print('라디오 버튼을 선택하세요.');
    }
  }

  @override
  void dispose() {
    // 페이지가 dispose될 때 컨트롤러를 해제해야 합니다.
    contentController.dispose();
    super.dispose();
  }

  Future<void> saveElapsedStudyTime() async {
    final prefs = await SharedPreferences.getInstance();
    final elapsedSeconds = prefs.getInt('study_time') ?? 0;

    final minutes = elapsedSeconds ~/ 60; // 분으로 변환

    // 오늘 날짜를 문자열로 생성 (예: "2023-09-15")
    final now = DateTime.now();
    final formattedDate =
        "${now.year}-${now.month.toString().padLeft(2, '0')}-${now.day.toString().padLeft(2, '0')}";

    // 해당 날짜에 저장된 값을 불러와서 현재 경과 시간을 더합니다.
    final todayElapsedMinutes = prefs.getInt(formattedDate) ?? 0;
    final updatedMinutes = todayElapsedMinutes + minutes;

    // 업데이트된 값을 SharedPreferences에 저장
    prefs.setInt(formattedDate, updatedMinutes);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('타이머'),
      ),
      body: Column(
        children: [
          Text('Token: $token'),
          Text(elapsedTimeString),
          // const InputBoxWidget(name: "메모 등록"),
          InputBoxWidget(
            name: "메모",
            controller: contentController, // 컨트롤러 연결
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("누구한테 자랑할까요"),
          ),
          DropdownButton(
            value: dropdownValue,
            menuMaxHeight: 150,
            items: itemList.map((String itemText) {
              return DropdownMenuItem<String>(
                value: itemText,
                child: SizedBox(child: Text(itemText)),
              );
            }).toList(),
            onChanged: (String? newValue) {
              setState(() {
                dropdownValue = newValue!;
                final selectedParent = parentData.firstWhere(
                  (parent) => parent['alias'] == dropdownValue,
                  orElse: () => <String, dynamic>{},
                );
                parentId = selectedParent['userId'] ?? 0;
              });
            },
          ),
          const SizedBox(
            height: 20,
          ),
          const ListTile(
            title: Text('라디오 버튼으로 선택하세요:'),
          ),
          RadioListTile<String>(
            title: const Text('저금'),
            value: 'KEEP',
            groupValue: selectedOption,
            onChanged: handleRadioValueChanged,
          ),
          RadioListTile<String>(
            title: const Text('용돈'),
            value: 'DIRECT',
            groupValue: selectedOption,
            onChanged: handleRadioValueChanged,
          ),
          const SizedBox(
            height: 20,
          ),
          ElevatedButton(
            onPressed: sendAction,
            child: const Text('보내기'),
          ),
        ],
      ),
    );
  }
}
