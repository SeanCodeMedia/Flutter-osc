import 'package:flutter/services.dart';
import 'dart:async';
import 'dart:convert';

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

  dynamic convertJson(dynamic param) {
    const JsonEncoder encoder = JsonEncoder();
    final dynamic object = encoder.convert(param);
    return object;
  }

  Future<void> sendOSCBundle() async {
    try {

      final String result = await _platform.invokeMethod('bundleSender',<String, dynamic>{
        "IP":_ip,
        "port":_port,
        "bundle": convertJson(_oSCBundleList),
      });

      print("DART SIDE " +  _oSCBundleList.runtimeType.toString());
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