package ru.tanki.service;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;

public class NumberEqualsServiceTest {

    @Test
    public void checkTime() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        long finishTime;
        Thread.sleep(1000);
        finishTime = System.currentTimeMillis();
        int result = Integer.parseInt(String.valueOf((finishTime - beginTime) / 1000));
        then(result).equals(1);
    }
}
