import 'package:flutter/material.dart';

class AccountList extends StatefulWidget {
  const AccountList({super.key});

  @override
  State<AccountList> createState() => _AccountListState();
}

class _AccountListState extends State<AccountList> {
  late Future<List<AccountList>> accountlist;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('계좌 정보'),
      ),
      body: Column(
        children: [
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("연결 계좌 변경"),
          ),
          FutureBuilder(
            // 계좌 내역 보여주기
            future: accountlist,
            builder: (context, snapshot) {
              if (snapshot.hasData) {
                return Column(
                  children: [
                    for (var account in snapshot.data!) Container(),
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
