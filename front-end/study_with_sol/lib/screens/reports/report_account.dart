import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:math';

class ReportAccount extends StatelessWidget {
  Future<List<Map<String, dynamic>>> fetchData() async {
    // JSON 데이터를 POST 요청 본문으로 전송
    final response = await http.post(
      Uri.parse(
          'http://ec2-3-12-34-166.us-east-2.compute.amazonaws.com:8080/reports/spending'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode({
        "connectionId": 1,
        "year": 2023,
        "month": 8,
      }),
    );

    if (response.statusCode == 200) {
      final parsedData = json.decode(utf8.decode(response.bodyBytes));

      return List<Map<String, dynamic>>.from(parsedData['data']);
    } else {
      throw Exception('Failed to load data');
    }
  }

  Color _generateRandomColor() {
    final random = Random();
    return Color.fromARGB(
      255,
      random.nextInt(256),
      random.nextInt(256),
      random.nextInt(256),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('레포트'),
      ),
      body: FutureBuilder<List<Map<String, dynamic>>>(
        future: fetchData(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return Center(child: Text('No data available.'));
          } else {
            final data = snapshot.data;
            return Center(
              child: PieChart(
                PieChartData(
                  sections: data?.map((item) {
                    return PieChartSectionData(
                      value: item['percent'],
                      color: _generateRandomColor(),
                      title: item['content'],
                      radius: 80,
                      titleStyle: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: const Color(0xffffffff),
                      ),
                    );
                  }).toList(),
                ),
              ),
            );
          }
        },
      ),
    );
  }
}
