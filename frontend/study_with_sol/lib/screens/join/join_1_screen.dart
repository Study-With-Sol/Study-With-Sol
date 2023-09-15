import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_2_screen.dart';

// 부모인가용 자녀인가용

class Join1 extends StatelessWidget {
  const Join1({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('회원가입'),
      ),
      body: Center(
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(20.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Text(
                    "부모인가요?",
                    style: TextStyle(
                      fontSize: 20,
                    ),
                  ),
                  IconButton(
                    onPressed: () {
                      // 부모라고 체크하고 다음으로 가기
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const Join2(isParent: true),
                          // true: 밑에서 가져오고 x
                          // false: 옆에서 가져오고 <
                          fullscreenDialog: false,
                        ),
                      );
                    },
                    icon: const Icon(
                      Icons.check_box,
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(20.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Text(
                    "자녀인가요?",
                    style: TextStyle(
                      fontSize: 20,
                    ),
                  ),
                  IconButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const Join2(isParent: false),
                          // true: 밑에서 가져오고 x
                          // false: 옆에서 가져오고 <
                          fullscreenDialog: false,
                        ),
                      );
                    },
                    icon: const Icon(
                      Icons.check_box,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
