import 'package:flutterosc/flutterOSCBundle.dart';
import 'package:flutter/services.dart';

class FlutterOSCBundleInt extends FlutterOSCBundle{

  static const _platform = const MethodChannel('flutter.native/osc');

  FlutterOSCBundleInt(String ip, int port, bool clearBundle) : super(ip, port, clearBundle);

  void addOSCMessageToBundle(String address, List<int> arguments){
    oSCBundleList[address] = arguments;
  }

  Future<void> sendOSCBundleInt() async {
    try {

      final String result = await _platform.invokeMethod('bundleSenderInt',<String, dynamic>{
        "IP":ip,
        "port":port,
        "bundle": convertJson(oSCBundleList),
      });

      print("DART SIDE " +  oSCBundleList.runtimeType.toString());
      response = result;
      print(response);

      if (isClearBundle){
        oSCBundleList = {};
      }

    } on PlatformException catch (e) {
      response = "Failed to Invoke: '${e.message}'.";
      print(response);
    }
  }


}