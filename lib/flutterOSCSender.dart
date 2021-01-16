import 'package:flutter/services.dart';
import 'dart:async';

class FlutterOSCSender {
  static const platform = const MethodChannel('flutter.native/osc');

  String _ip;
  int  _port;
  String _response = "";
  int delay = 0;

  FlutterOSCSender(String ip, int port){
    this._ip = ip;
    this._port = port;
  }

  Future<void> sendOSC(String address, List arguments) async {
    try {
      final String result = await platform.invokeMethod('sendOSCMessage',<String, dynamic>{
        "IP":this._ip,
        "port":this._port,
        "address":address,
        "message":arguments
      });
      _response = result;
      print(_response);
    } on PlatformException catch (e) {
      _response = "Failed to Invoke: '${e.message}'.";
      print(_response);
    }
  }


}