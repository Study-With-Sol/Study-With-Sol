import 'package:flutter/material.dart';
import 'package:study_with_sol/screens/login_screen.dart';
import 'package:study_with_sol/widgets/button_widget.dart';
import 'package:shared_preferences/shared_preferences.dart'; // SharedPreferences 패키지 추가

class JoinEnd extends StatelessWidget {
  const JoinEnd({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<bool>(
      // SharedPreferences에서 isParent 값을 읽어옴
      future: _getIsParent(),
      builder: (context, snapshot) {
        String message = "가입 완료";
        if (snapshot.connectionState == ConnectionState.waiting) {
          // 데이터를 아직 읽어오지 못한 경우 로딩 표시
          return const CircularProgressIndicator();
        } else if (snapshot.hasError) {
          // 오류 발생 시 오류 메시지 표시
          return Text("오류: ${snapshot.error}");
        } else if (snapshot.hasData) {
          // 데이터를 성공적으로 읽어온 경우
          bool isParent = snapshot.data!;
          message = isParent ? "부모 가입이 완료되었습니다." : "자녀 가입이 완료되었습니다.";
        }

        return Scaffold(
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  message,
                  style: const TextStyle(fontSize: 18),
                ),
                const SizedBox(
                  height: 100,
                ),
                InkWell(
                  onTap: () {
                    Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                        builder: (context) => const Login(),
                      ),
                    );
                  },
                  child: const Button(
                    text: "로그인하러가기 >",
                    bgColor: Colors.white,
                    textColor: Colors.black,
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  // SharedPreferences에서 isParent 값을 읽어오는 함수
  Future<bool> _getIsParent() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getBool("isParent") ?? false; // 기본값은 false로 설정
  }
}
