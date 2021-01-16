package com.flutterosc.flutterosc;

import android.util.Log;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;


/** FlutteroscPlugin */
public class FlutteroscPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private EventChannel  eventChannel;
  final String TAG = "Flutter OSC";
  private static final String CHANNEL = "flutter.native/osc";
  private static final String EVENTS_STREAM_CHANNEL = "flutter.native/oscEvent";
  private  MethodChannel methodChannel;
  private ListenerOSC listenerOSC;

  private SendOSC sendOSC = new SendOSC();
  private  OSCBundleSender bundleSender = new OSCBundleSender();

  void setIncomingPort(int incomingPort, String addressSelector){
    try{
      listenerOSC = new ListenerOSC(incomingPort, addressSelector);

    } catch (Exception e){
      Log.d(TAG,e.toString());
    }
  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), CHANNEL);
    channel.setMethodCallHandler(this);

    new EventChannel(flutterPluginBinding.getBinaryMessenger(), EVENTS_STREAM_CHANNEL).setStreamHandler(new EventChannel.StreamHandler() {
      @Override
      public void onListen(Object listener, EventChannel.EventSink events) {
        try {
          listenerOSC.JavaOSCListener(events);
        } catch (SocketException e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onCancel(Object arguments) {
        listenerOSC.stopListening();
      }
    });


  }


  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("sendOSCMessage")) {
      result.success("working");
      Log.d(TAG, call.argument("address").getClass().toString());
      Log.d(TAG, call.argument("message").getClass().toString());
      Log.d(TAG, call.argument("IP").getClass().toString());
      Log.d(TAG, call.argument("port").getClass().toString());

      sendOSC.sendOSCMessage(
              (String) call.argument("IP"),
              (Integer) call.argument("port"),
              (String) call.argument("address"),
              (ArrayList<String>) call.argument("message")
      );

    } else if(call.method.equals("bundleSender")){
      result.success("working2");

      Log.d(TAG, call.argument("bundle").getClass().toString());
      Log.d(TAG, call.argument("IP").getClass().toString());
      Log.d(TAG, call.argument("port").getClass().toString());
      Log.d(TAG, call.argument("bundle").toString());


      bundleSender.bundleSender((String) call.argument("IP"),
                               ((Integer) call.argument("port")),
              (HashMap<String, ArrayList<String>>) call.argument("bundle"));


    }  else if(call.method.equals("setIncomingPort")){
      result.success("working3");

      Log.d(TAG, call.argument("incomingPort").getClass().toString());
      setIncomingPort((Integer)call.argument("incomingPort"),
                       (String)call.argument("addressSelector")
                      );

    }
    else {
      result.notImplemented();
    }

  }


  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

}
