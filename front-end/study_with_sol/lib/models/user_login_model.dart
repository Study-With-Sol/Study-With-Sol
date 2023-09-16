class UserLoginModel {
  final String id;
  final String name;
  final String phoneNumber;
  final bool isParent;
  final String token;

  UserLoginModel({
    required this.id,
    required this.name,
    required this.phoneNumber,
    required this.isParent,
    required this.token,
  });

  factory UserLoginModel.fromJson(Map<String, dynamic> json) {
    return UserLoginModel(
      id: json['id'] as String,
      name: json['name'] as String,
      phoneNumber: json['phoneNumber'] as String,
      isParent: json['isParent'] as bool,
      token: json['token'] as String,
    );
  }
}
