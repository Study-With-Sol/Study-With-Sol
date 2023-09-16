import 'package:flutter/material.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:study_with_sol/widgets/inputbox_widget.dart';

class BabyTimerEnd extends StatefulWidget {
  const BabyTimerEnd({
    super.key,
  });

  @override
  State<BabyTimerEnd> createState() => _BabyTimerEndState();
}

class _BabyTimerEndState extends State<BabyTimerEnd> {
  String dropdownValue = '1';
  List<String> itemList = ['1', '2', '3', '4', '5', '6', '7', '8'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('타이머'),
      ),
      body: Column(
        children: [
          const Text("00:00:00"),
          const InputBoxWidget(name: "메모 등록"),
          Container(
            decoration: const BoxDecoration(
              color: Colors.blue,
            ),
            child: const Text("누구한테 자랑할까요"),
          ),
          DropdownButton(
            // dropdown : 용돈줄사람 리스트 나오도록
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
              });
            },
          ), //dropbox
          const SizedBox(
            height: 100,
          ),
          Row(
            children: [
              // button 3개
              InkWell(
                onTap: () {
                  // 저금하기
                },
                child: const Button(
                  text: "저금",
                  bgColor: Colors.white,
                  textColor: Colors.black,
                ),
              ),
              InkWell(
                onTap: () {
                  // 용돈보내기
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
    );
  }
}
