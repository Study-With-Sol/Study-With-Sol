class ResponseDataModel {
  final String message;
  final Map<String, dynamic> data;

  ResponseDataModel({
    required this.message,
    required this.data,
  });

  factory ResponseDataModel.fromJson(Map<String, dynamic> json) {
    return ResponseDataModel(
      message: json['message'] as String,
      data: json['data'] as Map<String, dynamic>,
    );
  }
}