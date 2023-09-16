class UserInfoModel {
  final String id;
  final String name;
  final String email;
  final bool isParent;
  final String? mainAccout; // 주어진 JSON 데이터에서 "mainAccout" 필드명을 수정함

  UserInfoModel({
    required this.id,
    required this.name,
    required this.email,
    required this.isParent,
    this.mainAccout, // 주어진 JSON 데이터에서 "mainAccout" 필드명을 수정함
  });

  factory UserInfoModel.fromJson(Map<String, dynamic> json) {
    return UserInfoModel(
      id: json['id'] as String,
      name: json['name'] as String,
      email: json['email'] as String,
      isParent: json['isParent'] as bool,
      mainAccout: json['mainAccout'] as String?, // 주어진 JSON 데이터에서 "mainAccout" 필드명을 수정함
    );
  }
}
