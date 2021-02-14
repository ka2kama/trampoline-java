package com.ka2kama.trampoline;

import java.util.function.Function;

public interface Trampoline<A> {

    default <B> Trampoline<B> map(Function<A, B> f) {
        return new FlatMap<>(this, f.andThen(Done::new));
    }

    default <B> Trampoline<B> flatMap(Function<A, Trampoline<B>> f) {
        return new FlatMap<>(this, f);
    }
}

