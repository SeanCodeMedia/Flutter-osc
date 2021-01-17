package com.flutterosc.flutterosc;

import android.util.Log;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;


public class SendOSC implements  OSCThread{

    final String TAG = "Flutter OSC";
          String ip;
          int port;
          OSCMessage messageObject;

    public void sendOSCMessageString(String ipAddress, int outGoingPort, String OSCAddress,  ArrayList<String> OSCMessage) {

        final String address = OSCAddress;
        final ArrayList<String>  message = OSCMessage;

        this.ip = ipAddress;
        this.port = outGoingPort;

        messageObject = new OSCMessage();
        messageObject.setAddress(address);

        for(int i=0; i < message.size(); i++){
            messageObject.addArgument(message.get(i));
        }

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    public void sendOSCMessageInteger(String ipAddress, int outGoingPort, String OSCAddress,  ArrayList<Integer> OSCMessage) {

        final String address = OSCAddress;
        final ArrayList<Integer>  message = OSCMessage;

        this.ip = ipAddress;
        this.port = outGoingPort;

        messageObject = new OSCMessage();
        messageObject.setAddress(address);

        for(int i=0; i < message.size(); i++){
            messageObject.addArgument(message.get(i));
        }

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    public void sendOSCMessageDouble(String ipAddress, int outGoingPort, String OSCAddress,  ArrayList<Double> OSCMessage) {

        final String address = OSCAddress;
        final ArrayList<Double>  message = OSCMessage;

        this.ip = ipAddress;
        this.port = outGoingPort;

        messageObject = new OSCMessage();
        messageObject.setAddress(address);

        for(int i=0; i < message.size(); i++){
            messageObject.addArgument(Float.parseFloat(message.get(i).toString()));
        }

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    public void sendOSCMessageBoolean(String ipAddress, int outGoingPort, String OSCAddress,  ArrayList<Boolean> OSCMessage) {

        final String address = OSCAddress;
        final ArrayList<Boolean>  message = OSCMessage;

        this.ip = ipAddress;
        this.port = outGoingPort;

        messageObject = new OSCMessage();
        messageObject.setAddress(address);

        for(int i=0; i < message.size(); i++){
            messageObject.addArgument(message.get(i));
        }

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }


    Callable<String> task = new Callable<String>() {
        @Override
        public String call() throws Exception {
            try{
                OSCPortOut oscPortOut = new OSCPortOut(InetAddress.getByName(ip), port);
                oscPortOut.send(messageObject);
                oscPortOut.close();
                return "OSC SENT";
            } catch (Exception e){
                Log.e(TAG, e.toString());
                return "OSC NOT SENT";
            }
        }
    };
}

