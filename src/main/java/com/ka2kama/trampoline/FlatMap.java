package com.ka2kama.trampoline;

import java.util.function.Function;

public class FlatMap<A, B> implements Trampoline<B> {
    public final Trampoline<A> a;
    public final Function<A, Trampoline<B>> f;
    public FlatMap(Trampoline<A> a, Function<A, Trampoline<B>> f) {
        this.a = a;
        this.f = f;
    }
}
