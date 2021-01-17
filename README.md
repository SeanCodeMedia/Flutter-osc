<<<<<<< HEAD

# flutterosc

Flutter OSC allows flutter developers to send Open Sound Control (OSC) messages from android.
This plugin is dependent on Java OSC com.illposed.osc to send OSC messages. Hence, this package does not work on IOS. I am planning on added IOS support shortly.


# Example OSC Sender 

 ```dart 
     import 'package:flutterosc/flutterOSCSender.dart';

     FlutterOSCSender sender = new FlutterOSCSender("192.168.0.101",  9076); 
    
     sender.sendOSC("/address", ["hello", "hello 2"]); 
    
```
=======

>>>>>>> 667f31cf8a39c60aac801dc3deaf90a55c90d2b2


# Example OSC Bundle 

 ```dart 
     import 'package:flutterosc/flutterOSCSender.dart';

<<<<<<< HEAD
     FlutterOSCSender sender = new FlutterOSCSender("192.168.0.101",  9076); 
    
     sender.sendOSC("/address", ["hello", "hello 2"]); 
    
```
=======
=======
# Flutter-osc
This is a Flutter plugin for Android that allows the user to send open sound control (OSC) messages via flutter.

>>>>>>> 667f31cf8a39c60aac801dc3deaf90a55c90d2b2
