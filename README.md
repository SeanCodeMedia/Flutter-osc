
# flutterosc

Flutter OSC allows flutter developers to send Open Sound Control (OSC) messages from android.
This plugin is dependent on Java OSC com.illposed.osc to send OSC messages. Hence, this package does not work on IOS. I am planning on added IOS support shortly.


# Example OSC Sender 

 ```dart 
     import 'package:flutterosc/flutterOSCSender.dart';

     FlutterOSCSender sender = new FlutterOSCSender("192.168.0.101",  9076); 
    
     sender.sendOSC("/address", ["hello", "hello 2"]); 
    
```


# Example OSC Bundle 

 ```dart 
     import 'package:flutterosc/flutterOSCSender.dart';

     FlutterOSCSender sender = new FlutterOSCSender("192.168.0.101",  9076); 
    
     sender.sendOSC("/address", ["hello", "hello 2"]); 
    
```