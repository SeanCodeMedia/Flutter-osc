package com.flutterosc.flutterosc;


import android.util.Log;
import com.google.gson.reflect.TypeToken;
import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPacket;
import com.illposed.osc.OSCPortOut;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class OSCBundleSender implements OSCThread {

        final String TAG = "Flutter OSC";
        ArrayList<OSCPacket> packets = new ArrayList<OSCPacket>();
        Gson gson = new Gson();
        String ip;
        int port;
        OSCBundle OSCBundle;


        public void bundleSenderString(String IPAddress, int outGoingPort, String bundle){

            JSONObject jsonObject;
            this.ip = IPAddress;
            this.port = outGoingPort;

            try{
                 jsonObject = new JSONObject(bundle);
                 JSONArray keys = jsonObject.names();

                assert keys != null;
                for(int y = 0; y < keys.length(); y++){
                     String key = keys.getString(y);
                     OSCMessage message = new OSCMessage(key);
                     String value = jsonObject.getString(key);
                        // help from https://howtodoinjava.com/gson/gson-parse-json-array/
                     Type argArrayType = new TypeToken<ArrayList<String>>(){}.getType();
                     ArrayList<String> argArray = gson.fromJson(value, argArrayType);

                     for(int i=0; i <argArray.size(); i++){
                        message.addArgument(argArray.get(i));
                      }
                      message.addArgument(""); // for some reason the
                    // JAVA OSC does not like when you add odd number
                    //          arguments so I added this to make it even all the time
                    packets.add(message);
                 }


            } catch (Exception e){
                Log.d(TAG, e.toString());
            }

            Date newDate = new Date();
            long time = newDate.getTime();
            Integer delayTime = Integer.valueOf("2");
            time = time + delayTime.longValue();
            newDate.setTime(time);
            OSCBundle = new OSCBundle(packets, newDate);

            Future<String> future = executor.submit(task);

            try{
                String result = future.get();
                Log.d(TAG, result.toString());
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }

            };

        public void bundleSenderInt(String IPAddress, int outGoingPort, String bundle){

        JSONObject jsonObject;
        this.ip = IPAddress;
        this.port = outGoingPort;

        try{
            jsonObject = new JSONObject(bundle);
            JSONArray keys = jsonObject.names();

            assert keys != null;
            for(int y = 0; y < keys.length(); y++){
                String key = keys.getString(y);
                OSCMessage message = new OSCMessage(key);
                String value = jsonObject.getString(key);
                // help from https://howtodoinjava.com/gson/gson-parse-json-array/
                Type argArrayType = new TypeToken<ArrayList<String>>(){}.getType();
                ArrayList<String> argArray = gson.fromJson(value, argArrayType);

                for(int i=0; i <argArray.size(); i++){
                    message.addArgument(Integer.parseInt(argArray.get(i)));
                }
                message.addArgument(""); // for some reason the
                // JAVA OSC does not like when you add odd number
                //          arguments so I added this to make it even all the time
                packets.add(message);
            }


        } catch (Exception e){
            Log.d(TAG, e.toString());
        }

        Date newDate = new Date();
        long time = newDate.getTime();
        Integer delayTime = Integer.valueOf("2");
        time = time + delayTime.longValue();
        newDate.setTime(time);
        OSCBundle = new OSCBundle(packets, newDate);

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    };

        public void bundleSenderFloat(String IPAddress, int outGoingPort, String bundle){

        JSONObject jsonObject;
        this.ip = IPAddress;
        this.port = outGoingPort;

        try{
            jsonObject = new JSONObject(bundle);
            JSONArray keys = jsonObject.names();

            assert keys != null;
            for(int y = 0; y < keys.length(); y++){
                String key = keys.getString(y);
                OSCMessage message = new OSCMessage(key);
                String value = jsonObject.getString(key);
                // help from https://howtodoinjava.com/gson/gson-parse-json-array/
                Type argArrayType = new TypeToken<ArrayList<String>>(){}.getType();
                ArrayList<String> argArray = gson.fromJson(value, argArrayType);

                for(int i=0; i <argArray.size(); i++){
                    message.addArgument(Float.parseFloat(argArray.get(i)));
                }
                message.addArgument(""); // for some reason the
                // JAVA OSC does not like when you add odd number
                //          arguments so I added this to make it even all the time
                packets.add(message);
            }


        } catch (Exception e){
            Log.d(TAG, e.toString());
        }

        Date newDate = new Date();
        long time = newDate.getTime();
        Integer delayTime = Integer.valueOf("2");
        time = time + delayTime.longValue();
        newDate.setTime(time);
        OSCBundle = new OSCBundle(packets, newDate);

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    };

        public void bundleSenderBool(String IPAddress, int outGoingPort, String bundle){

        JSONObject jsonObject;
        this.ip = IPAddress;
        this.port = outGoingPort;

        try{
            jsonObject = new JSONObject(bundle);
            JSONArray keys = jsonObject.names();

            assert keys != null;
            for(int y = 0; y < keys.length(); y++){
                String key = keys.getString(y);
                OSCMessage message = new OSCMessage(key);
                String value = jsonObject.getString(key);
                // help from https://howtodoinjava.com/gson/gson-parse-json-array/
                Type argArrayType = new TypeToken<ArrayList<String>>(){}.getType();
                ArrayList<String> argArray = gson.fromJson(value, argArrayType);

                for(int i=0; i <argArray.size(); i++){
                    message.addArgument(Boolean.parseBoolean(argArray.get(i)));
                }
                message.addArgument(""); // for some reason the
                // JAVA OSC does not like when you add odd number
                //          arguments so I added this to make it even all the time
                packets.add(message);
            }


        } catch (Exception e){
            Log.d(TAG, e.toString());
        }

        Date newDate = new Date();
        long time = newDate.getTime();
        Integer delayTime = Integer.valueOf("2");
        time = time + delayTime.longValue();
        newDate.setTime(time);
        OSCBundle = new OSCBundle(packets, newDate);

        Future<String> future = executor.submit(task);

        try{
            String result = future.get();
            Log.d(TAG, result.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    };

    Callable<String> task = new Callable<String>() {
        @Override
        public String call() throws Exception {
            try{
                OSCPortOut oscPortOut = new OSCPortOut(InetAddress.getByName(ip), port);
                oscPortOut.send(OSCBundle);
                packets.clear();
                oscPortOut.close();
                return "OSC BUNDLE SENT";
            } catch (Exception e){
                Log.e(TAG, e.toString());
                return "OSC BUNDLE NOT SENT";
            }
        }
    };

    }
