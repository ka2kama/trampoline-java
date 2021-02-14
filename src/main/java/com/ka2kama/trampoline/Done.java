package com.ka2kama.trampoline;

public class Done<A> implements Trampoline<A> {
    public final A result;
    public Done(A result) {
        this.result = result;
    }
}
