import 'package:flutter/material.dart';

class Player {
  String name = 'nico';

  Player(this.name);
}
//15 2.42
void main() {
  var nico = Player();
  runApp(App());
}

class App extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Hello flutter!'),
        ),
        body: Center(
          child: Text('Hello world!'),
          ),
        ),
    );
  }
}