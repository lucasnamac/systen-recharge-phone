package com.example.RecargasCelular.Rabbit;

import java.util.concurrent.CountDownLatch;

public class Receiver {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private String message;

    public void receiver(String message) {
        System.out.println("Message Received: " + message);
        this.message = message;
        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public String getMessage() {
        return message;
    }
}
