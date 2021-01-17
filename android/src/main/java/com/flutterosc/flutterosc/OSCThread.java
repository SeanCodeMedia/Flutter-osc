package com.flutterosc.flutterosc;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface OSCThread {

    ExecutorService executor = Executors.newFixedThreadPool(1);

}
