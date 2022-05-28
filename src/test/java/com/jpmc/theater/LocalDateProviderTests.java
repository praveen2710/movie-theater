package com.jpmc.theater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateProviderTests {
    @Test
    void makeSureSingelton() {
        LocalDate instance1 =  LocalDateProvider.singleton().currentDate();
        LocalDate instance2 =  LocalDateProvider.singleton().currentDate();
        Assertions.assertEquals(instance1,instance2);
    }
}
