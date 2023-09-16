import 'package:flutter/material.dart';
import 'package:dio/dio.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:study_with_sol/screens/reports/report_account.dart';
import 'package:study_with_sol/widgets/button_widget.dart';

class AccountList extends StatefulWidget {
  const AccountList({Key? key}) : super(key: key);

  @override
  _AccountListState createState() => _AccountListState();
}

class _AccountListState extends State<AccountList> {
  final Dio _dio = Dio();
  String accountName = '';
  double balance = 0;
  String accountNumber = '';
  int id = 0;
  List<dynamic> transactionList = [];

  @override
  void initState() {
    super.initState();
    // 계좌 정보를 먼저 불러오고, 그 후 거래 내역을 불러옵니다.
    loadAccountInfo().then((_) {
      loadTransactionList();
    });
  }

  @override
  void dispose() {
    _dio.close();
    super.dispose();
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

  Future<void> loadTransactionList() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    const apiUrl =
        'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/transactions/list';
    print(id);
    final requestData = {
      "accountId": id,
    };

    try {
      print("try");
      final response = await _dio.post(
        apiUrl,
        data: requestData,
      );
      print("시도");
      if (response.statusCode == 200) {
        final jsonResponse = response.data['data']['content'];
        print("성공");
        print(jsonResponse);
        List<dynamic> tempList = jsonResponse
            .map((transaction) => TransactionInfo.fromJson(transaction))
            .toList();
        //-------------
        // final jsonResponse = response.data;
        // final List<dynamic> accountList = jsonResponse['data']['content'];

        // // 응답에서 받은 데이터를 AccountInfo 객체로 변환하여 리스트에 저장
        // accountInfoList = accountList
        //     .map((account) => AccountInfo.fromJson(account))
        //     .toList();
        //-------------
        setState(() {
          transactionList = tempList;
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
        title: const Text('계좌 정보'),
      ),
      body: Column(
        children: [
          const SizedBox(
            height: 20,
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
                    Text("\$$balance"),
                    // InkWell(
                    //   onTap: () {
                    //     // 계좌정보화면으로
                    //     Navigator.of(context).push(
                    //       MaterialPageRoute(
                    //         builder: (BuildContext context) {
                    //           return ReportAccount();
                    //         },
                    //       ),
                    //     );
                    //   },
                    //   child: const Button(
                    //     text: "레포트",
                    //     bgColor: Colors.white,
                    //     textColor: Colors.black,
                    //   ),
                    // ),
                  ],
                ),
                Text(accountName),
                Text("계좌번호: $accountNumber"),
                Text("ID: $id"),
              ],
            ),
          ),
          const SizedBox(height: 20),
          Expanded(
            child: ListView.builder(
              itemCount: transactionList.length,
              itemBuilder: (context, index) {
                final transaction = transactionList[index];
                return ListTile(
                  title: Text(transaction.content),
                  subtitle: Text(transaction.isDeposit
                      ? "입금: \$${transaction.amount.toStringAsFixed(2)}"
                      : "출금: \$${transaction.amount.toStringAsFixed(2)}"),
                  trailing: Text(transaction.useDate),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

class TransactionInfo {
  final int id;
  final String content;
  final double amount;
  final String useDate;
  final bool isDeposit;

  TransactionInfo({
    required this.id,
    required this.content,
    required this.amount,
    required this.useDate,
    required this.isDeposit,
  });

  factory TransactionInfo.fromJson(Map<String, dynamic> json) {
    return TransactionInfo(
      id: json['id'] as int, // id 필드를 int로 파싱
      content: json['content'] as String, // content 필드를 String으로 파싱
      amount: (json['amount'] as num).toDouble(), // amount 필드를 double로 파싱
      useDate: json['useDate'] as String, // useDate 필드를 String으로 파싱
      isDeposit: json['isDeposit'] as bool, // isDeposit 필드를 bool로 파싱
    );
  }
}
