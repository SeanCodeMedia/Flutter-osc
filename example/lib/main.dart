import 'package:flutter/material.dart';
import 'package:flutterosc/FlutterOSCBundleString.dart';
import 'package:flutterosc/FlutterOSCBundleInteger.dart';
import 'package:flutterosc/FlutterOSCBundleFloat.dart';
import 'package:flutterosc/FlutterOSCBundleBoolean.dart';
import 'package:flutterosc/flutterOSCListener.dart';
import 'package:flutterosc/flutterOSCSender.dart';
void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  FlutterOSCSender sender = new FlutterOSCSender("192.168.0.103",  9076);
  FlutterOSCBundleString bundle3 = new FlutterOSCBundleString("192.168.0.103", 9076, false);
  FlutterOSCBundleInt bundle2 = new    FlutterOSCBundleInt("192.168.0.103", 9076, false);
  FlutterOSCBundleFloat bundle = new   FlutterOSCBundleFloat("192.168.0.103", 9076, false);
  FlutterOSCBundleBool bundle4 = new   FlutterOSCBundleBool("192.168.0.103", 9076, false);
  FlutterOSCListener listener = new    FlutterOSCListener(9003, "");

  @override
  void initState() {
    super.initState();
     bundle.addOSCMessageToBundle("/igloo1", [1.2, 14.4, 12.2]);
     bundle.addOSCMessageToBundle("/igloo2", [1.2, 14.4, 12.2]);
     bundle.addOSCMessageToBundle("/igloo3", [1.2, 14.4, 12.2]);
     bundle.addOSCMessageToBundle("/igloo4", [1.2, 14.4, 12.2, 1.2, 14.4, 12.2]);

    bundle2.addOSCMessageToBundle("/igloo5", [1,2,3]);
    bundle2.addOSCMessageToBundle("/igloo6", [1,2,3]);
    bundle2.addOSCMessageToBundle("/igloo7", [1,2,3]);
    bundle2.addOSCMessageToBundle("/igloo8", [1,2,3,4,1,2,3,4]);

    bundle3.addOSCMessageToBundle("/igloo9", ["1","2","3"]);
    bundle3.addOSCMessageToBundle("/igloo10", ["1","2","3"]);
    bundle3.addOSCMessageToBundle("/igloo11", ["1","2","3"]);
    bundle3.addOSCMessageToBundle("/igloo12", ["1","2","3","4"]);

    listener.oscStream.listen((event) {
             // print(event.toString());
    });



  }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion\n'),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed:(){
            bundle.sendOSCBundleFloat();
            bundle2.sendOSCBundleInt();
            bundle3.sendOSCBundleString();
            sender.sendOSCInt("/address", [1, 2]);
            sender.sendOSCString("/address", ["1s", "2s"]);
            sender.sendOSCString("/address", ["3s", "5s"]);
            sender.sendOSCFloat("/address1", [1.000, 1234.4]);
            sender.sendOSCFloat("/address2", [2.000, 1234.4]);
            sender.sendOSCFloat("/address3", [3.000, 1234.4]);
            sender.sendOSCFloat("/address4", [4.000, 1234.4]);
            sender.sendOSCFloat("/address5", [5.000, 1234.4]);
            sender.sendOSCBool("/address", [true, false]);
          },
          tooltip: 'Increment',
          child: Icon(Icons.add),
        ), // This trailing comma makes auto-formatting nicer for build methods.
      ),
    );
  }
}
