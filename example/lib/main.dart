import 'package:flutter/material.dart';
import 'package:flutterosc/flutterOSCBundle.dart';
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

  FlutterOSCSender sender = new FlutterOSCSender("192.168.0.101",  9076);
  FlutterOSCBundle bundle = new FlutterOSCBundle("192.168.0.101", 9076, false);
  FlutterOSCListener listener = new FlutterOSCListener(9003, "");

  @override
  void initState() {
    super.initState();
     bundle.addOSCMessageToBundle("/igloo1", ["1","2","3"]);
     bundle.addOSCMessageToBundle("/igloo2", ["1","2","3"]);
     bundle.addOSCMessageToBundle("/igloo3", ["1","2","3"]);
     bundle.addOSCMessageToBundle("/igloo4", ["1","2","3","4"]);

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
            bundle.sendOSCBundle();
            sender.sendOSC("/address", ["hello", "hello 2"]);
          },
          tooltip: 'Increment',
          child: Icon(Icons.add),
        ), // This trailing comma makes auto-formatting nicer for build methods.
      ),
    );
  }
}
