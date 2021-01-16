import 'package:flutter/services.dart';
import 'dart:async';


class FlutterOSCBundle {

  String _ip;
  int  _port;
  Map _oSCBundleList = {};
  int _delay = 0;
  String _response = "";
  bool _isClearBundle = false;
  static const _platform = const MethodChannel('flutter.native/osc');

  FlutterOSCBundle(String ip, int port, bool clearBundle){
    this._ip = ip;
    this._port = port;
    this._isClearBundle = clearBundle;
  }


  void addOSCMessageToBundle(String address, List arguments){
    _oSCBundleList[address] = arguments;
  }

  void emptyOSCBundle(){
    _oSCBundleList = {};
  }

  Future<void> sendOSCBundle() async {
    try {

      final String result = await _platform.invokeMethod('bundleSender',<String, dynamic>{
        "IP":_ip,
        "port":_port,
        "bundle":_oSCBundleList
      });
      _response = result;
      print(_response);

      if (_isClearBundle){
        _oSCBundleList = {};
      }

    } on PlatformException catch (e) {
      _response = "Failed to Invoke: '${e.message}'.";
      print(_response);
    }
  }


}