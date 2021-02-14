package com.ka2kama.trampoline;

import java.util.function.Function;
import java.util.function.Supplier;

public class TrampolineRunner {
    private TrampolineRunner() {}

    @SuppressWarnings("unchecked")
    public static <T> T run(Trampoline<T> trampoline) {
        Trampoline<T> t = trampoline;
        while (!(t instanceof Done<?>)) {
            if (t instanceof More<?>) {
                t = ((More<T>) t).next.get();
            } else if (t instanceof FlatMap<?, ?>) {
                FlatMap<Object, T> fm = (FlatMap<Object, T>) t;
                Trampoline<?> x = fm.a;
                Function<Object, Trampoline<T>> f = fm.f;
                if (x instanceof Done<?>)  {
                    Object a = ((Done<?>) x).result;
                    t = f.apply(a);
                } else if (x instanceof More<?>) {
                    Supplier<Trampoline<Object>> k = ((More<Object>) x).next;
                    Trampoline<Object> next = k.get();
                    t = new FlatMap<>(next, f);
                } else if (x instanceof FlatMap<?, ?>)  {
                    FlatMap<Object, Object> fm2 = (FlatMap<Object, Object>) x;
                    Trampoline<Object> y = fm2.a;
                    Function<Object, Trampoline<Object>> g = fm2.f;
                    Function<Object, Trampoline<T>> h = (Object a) -> {
                        Trampoline<Object> next = g.apply(a);
                        return new FlatMap<>(next, f);
                    };
                    t = new FlatMap<>(y, h);
                } else {
                    throw new UnsupportedOperationException("予期せぬクラスです｡");
                }
            }
        }

        return ((Done<T>) t).result;
    }
}
