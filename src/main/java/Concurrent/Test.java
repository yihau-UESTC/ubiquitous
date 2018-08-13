package Concurrent;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Test {


    public static void main(String[] args) {
        new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return null;
            }
        };
    }
}