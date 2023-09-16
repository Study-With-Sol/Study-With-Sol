import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AccountNumber extends StatefulWidget {
  const AccountNumber({Key? key}) : super(key: key);

  @override
  _AccountNumberState createState() => _AccountNumberState();
}

class _AccountNumberState extends State<AccountNumber> {
  final Dio _dio = Dio();
  final TextEditingController _accountNumberController =
      TextEditingController();
  bool _isAccountValid = true;
  bool _isRegistrationComplete = false;
  late String _token; // 토큰 값

  @override
  void initState() {
    super.initState();
    _loadToken(); // 토큰 값 불러오기
  }

  Future<void> _loadToken() async {
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      _token = prefs.getString('token') ?? ''; // token 키로 저장된 값을 불러옴
    });
  }

  Future<void> _validateAccountAndRegister() async {
    final accountNumber = _accountNumberController.text;
    print(accountNumber);
    final validateRequestData = {
      "accountNumber": accountNumber,
    };

    try {
      final validateResponse = await _dio.post(
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/accounts/owner', // 계좌 번호 검증 API 엔드포인트를 여기에 채워 넣으세요.
        data: validateRequestData,
        options: Options(
          headers: {
            'Authorization': 'Bearer $_token', // 불러온 토큰 값 사용
          },
        ),
      );
      print("?");
      final validateData = validateResponse.data['data'];
      final isAccountValid = validateData['check'];

      setState(() {
        _isAccountValid = isAccountValid;
      });

      if (!isAccountValid) {
        // 계좌 번호가 유효하지 않을 때 처리
        return;
      }

      final registerRequestData = {
        "accountNumber": accountNumber,
      };

      final registerResponse = await _dio.post(
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/accounts', // 계좌 등록 API 엔드포인트를 여기에 채워 넣으세요.
        data: registerRequestData,
        options: Options(
          headers: {
            'Authorization': 'Bearer $_token', // 불러온 토큰 값 사용
          },
        ),
      );

      final registerData = registerResponse.data['data'];

      if (registerData != null) {
        // 등록이 성공한 경우 처리
        setState(() {
          _isRegistrationComplete = true;
        });
      }
    } catch (e) {
      // 오류 처리
      print('Error: $e');

      setState(() {
        _isAccountValid = false;
      });
    }
  }

  @override
  void dispose() {
    _dio.close();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('계좌 등록'),
      ),
      body: Center(
        child: Column(
          children: [
            const Text("계좌 인증"),
            const Text("실명인증과 계좌등록을 위해 보유하고 있는 신한은행 계좌정보를 입력해주세요."),
            const SizedBox(
              height: 100,
            ),
            TextField(
              controller: _accountNumberController,
              decoration: const InputDecoration(
                labelText: "계좌번호",
              ),
            ),
            //const Text("본인 명의의 계좌번호만 입력 가능합니다"),
            const SizedBox(
              height: 100,
            ),
            ElevatedButton(
              onPressed: _validateAccountAndRegister,
              child: const Text("등록 >"),
            ),
            if (_isRegistrationComplete) const Text("계좌 등록이 완료되었습니다."),
            if (!_isAccountValid) const Text("유효한 계좌번호가 아닙니다."),
          ],
        ),
      ),
    );
  }
}
