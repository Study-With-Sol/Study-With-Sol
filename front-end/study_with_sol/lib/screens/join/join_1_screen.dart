import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/join/join_2_screen.dart';
import 'package:shared_preferences/shared_preferences.dart'; // 패키지 임포트 추가

class Join1 extends StatelessWidget {
  const Join1({super.key});

  // SharedPreferences를 사용하여 상태 저장
  _saveUserType(bool isParent) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await prefs.setBool('isParent', isParent);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('회원가입'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
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
                      _saveUserType(true); // 부모로 설정
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const Join2(),
                          fullscreenDialog: false,
                        ),
                      );
                    },
                    icon: const Icon(
                      Icons.check_box_outlined,
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(
              height: 50,
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
                      // 자녀라고 체크하고 다음으로 가기
                      _saveUserType(false); // 자녀로 설정
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const Join2(),
                          fullscreenDialog: false,
                        ),
                      );
                    },
                    icon: const Icon(
                      Icons.check_box_outlined,
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
