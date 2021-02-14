package com.ka2kama.trampoline;

import java.util.function.Supplier;

public class More<A> implements Trampoline<A> {
    public final Supplier<Trampoline<A>> next;
    public More(Supplier<Trampoline<A>> next) {
        this.next = next;
    }
}
