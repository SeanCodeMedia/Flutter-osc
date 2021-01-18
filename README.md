
# flutterosc

Flutter OSC allows flutter developers to send Open Sound Control (OSC) messages from android.
This plugin is dependent on Java OSC com.illposed.osc to send OSC messages. Hence, this package does not work on IOS. I am planning on added IOS support shortly.
for more details on Java osc you can go to this link https://www.illposed.com/software/javaoscdoc/ 

# Example OSC Sender 

 ```dart 
     import 'package:flutterosc/flutterOSCSender.dart';

     FlutterOSCSender sender = new FlutterOSCSender("192.168.0.103",  9076); 
     // sending strings 
     sender.sendOSC("/address", ["hello", "hello 2"]); 
     // sending intergers 
     sender.sendOSCInt("/address", [1, 2]);
     // sending floats 
     sender.sendOSCFloat("/address1", [1.000, 1234.4]);
     // sending boolean
      sender.sendOSCBool("/address", [true, false]);
```

# Example OSC Bundle 

flutter osc bundles are sent immediately. scheduler is currently not supported but will be added. Read more about bundles at 
https://www.illposed.com/software/javaoscdoc/ 

 ```dart 
    import 'package:flutterosc/FlutterOSCBundleString.dart';
    import 'package:flutterosc/FlutterOSCBundleInteger.dart';
    import 'package:flutterosc/FlutterOSCBundleFloat.dart';
    import 'package:flutterosc/FlutterOSCBundleBoolean.dart';
     
     // string bundle 
     FlutterOSCBundleString stringBundle =  new  FlutterOSCBundleString("192.168.0.103", 9076, false);
     // int bundle 
     FlutterOSCBundleInt    intBundle    =  new  FlutterOSCBundleInt("192.168.0.103", 9076, false);
     // float bundle 
     FlutterOSCBundleFloat  floatBundle  =  new  FlutterOSCBundleFloat("192.168.0.103", 9076, false);
     // boolBundle 
     FlutterOSCBundleBool   boolBundle   =  new  FlutterOSCBundleBool("192.168.0.103", 9076, false);
        
     stringBundle.addOSCMessageToBundle("/oscAddress", ["hello world"]);
     intBundle.addOSCMessageToBundle("/oscAddress", [1, 2, 3]);
     floatBundle.addOSCMessageToBundle("/oscAddress",[12.0, 15.3, 1.5]); 
     boolBundle.addOSCMessageToBundle("oscAddress", [true, false]); 
       
     // send osc bundle  
     floatBundle.sendOSCBundleFloat();
     intBundle.sendOSCBundleInt();
     stringBundle.sendOSCBundleString();
     boolBundle.sendOSCBundleBool()


```

# Example OSC Listener 
 FlutterOSCListener(<port number>, <"address pattern you want to listen for">);

 FlutterOSCListener(<port number>, <"address pattern you want to listen for">);

  ```dart 

    FlutterOSCListener listener = new    FlutterOSCListener(9003, "");  

   listener.oscStream.listen((event) {
             print(event.toString());
    });
  
  

```
