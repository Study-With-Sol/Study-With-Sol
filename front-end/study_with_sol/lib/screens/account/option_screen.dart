import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class Option extends StatefulWidget {
  const Option({Key? key}) : super(key: key);

  @override
  _OptionState createState() => _OptionState();
}

class _OptionState extends State<Option> {
  final Dio _dio = Dio(); // Dio 인스턴스 생성
  late List<AccountInfo> accountInfoList = [];

  @override
  void initState() {
    super.initState();
    // 계좌 정보를 불러오는 함수 호출
    loadAccountInfo();
  }

  @override
  void dispose() {
    _dio.close(); // Dio 인스턴스를 사용한 후 꼭 닫아줍니다.
    super.dispose();
  }

  Future<void> loadAccountInfo() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/accounts/list'; // 계좌 정보 불러오기 API 엔드포인트로 수정
    try {
      final response = await _dio.get(apiUrl,
          options: Options(headers: {
            'Authorization':
                'Bearer ${prefs.getString('token')}', // 토큰을 header에 추가
          }));

      if (response.statusCode == 200) {
        final jsonResponse = response.data;
        final List<dynamic> accountList = jsonResponse['data']['content'];

        // 응답에서 받은 데이터를 AccountInfo 객체로 변환하여 리스트에 저장
        accountInfoList = accountList
            .map((account) => AccountInfo.fromJson(account))
            .toList();

        // 계좌 정보를 가져왔으므로 화면을 다시 렌더링
        setState(() {});
      } else {
        // 요청 실패
        print("API 호출 실패: ${response.statusCode}");
        print("에러 응답: ${response.data}");
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
        title: const Text('설정'),
      ),
      body: Column(
        children: [
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("연결 계좌 변경"),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: accountInfoList.length,
              itemBuilder: (context, index) {
                final accountInfo = accountInfoList[index];
                return ListTile(
                  title: Text(accountInfo.accountName),
                  subtitle: Text(accountInfo.accountNumber),
                  trailing: Text('\$${accountInfo.balance.toStringAsFixed(2)}'),
                );
              },
            ),
          ),
          InkWell(
            onTap: () {
              // 이메일 인증 보내기 api
            },
            child: const Button(
              text: "+",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("회원정보 변경"),
          ),
          const InputBoxWidget(
            name: "현재 비밀번호",
            isPassword: true,
          ),
          const InputBoxWidget(
            name: "변경 비밀번호",
            isPassword: true,
          ),
          const InputBoxWidget(
            name: "변경 비밀번호 재입력",
            isPassword: true,
          ),
          const SizedBox(
            height: 100,
          ),
          InkWell(
            onTap: () {
              // 비밀번호 변경 완료
            },
            child: const Button(
              text: "변경",
              bgColor: Colors.white,
              textColor: Colors.black,
            ),
          ),
        ],
      ),
    );
  }
}

class AccountInfo {
  final int id;
  final String accountNumber;
  final String accountName;
  final double balance;
  final bool isMainAccount;

  AccountInfo({
    required this.id,
    required this.accountNumber,
    required this.accountName,
    required this.balance,
    required this.isMainAccount,
  });

  factory AccountInfo.fromJson(Map<String, dynamic> json) {
    return AccountInfo(
      id: json['id'],
      accountNumber: json['accountNumber'],
      accountName: json['accountName'],
      balance: json['balance'],
      isMainAccount: json['isMainAccount'],
    );
  }
}
