package com.ka2kama.trampoline;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static com.ka2kama.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrampolineTest {
    @Test
    void even() {
        Trampoline<Boolean> t1 = evenTp(100000);
        Boolean result1 = TrampolineRunner.run(t1);
        assertEquals(Boolean.TRUE, result1);

        Trampoline<Boolean> t2 = evenTp(1000001);
        Boolean result2 = TrampolineRunner.run(t2);
        assertEquals(Boolean.FALSE, result2);
    }

    @Test
    void odd() {
        Trampoline<Boolean> t1 = oddTp(100000);
        Boolean result1 = TrampolineRunner.run(t1);
        assertEquals(Boolean.FALSE, result1);

        Trampoline<Boolean> t2 = oddTp(1000001);
        Boolean result2 = TrampolineRunner.run(t2);
        assertEquals(Boolean.TRUE, result2);
    }

    @Test
    void factorial() {
        Trampoline<BigInteger> f = factorialTp(BigInteger.valueOf(5));
        BigInteger m = TrampolineRunner.run(f);
        assertEquals(BigInteger.valueOf(120), m);
    }
}