package com.ka2kama;

import com.ka2kama.trampoline.Done;
import com.ka2kama.trampoline.More;
import com.ka2kama.trampoline.Trampoline;
import com.ka2kama.trampoline.TrampolineRunner;

import java.math.BigInteger;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Trampoline<BigInteger> t = factorialTp(BigInteger.valueOf(100000));
        System.out.println(TrampolineRunner.run(t));
    }

    public static Trampoline<BigInteger> factorialTp(BigInteger n) {
        if (Objects.equals(n, BigInteger.ZERO))
            return new Done<>(BigInteger.ZERO);

        if (Objects.equals(n, BigInteger.ONE))
            return new Done<>(BigInteger.ONE);

        return new More<>(() -> {
            // n - 1の階乗
            Trampoline<BigInteger> prevFractal = factorialTp(n.subtract(BigInteger.ONE));
            // n * 【n - 1の階乗】
            return prevFractal.map(n::multiply);
        });
    }

    public static Trampoline<Boolean> evenTp(int n) {
        if (n == 0) return new Done<>(Boolean.TRUE);

        return n > 0
                ? new More<>(() -> oddTp(n - 1))
                : new More<>(() -> oddTp(n + 1));
    }

    public static Trampoline<Boolean> oddTp(int n) {
        if (n == 0) return new Done<>(Boolean.FALSE);

        return n > 0
                ? new More<>(() -> evenTp(n - 1))
                : new More<>(() -> evenTp(n + 1));
    }

}
