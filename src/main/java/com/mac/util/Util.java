package com.mac.util;

import java.lang.reflect.Array;

public final class Util {

    private Util() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] concatenate(T[] a, T[] b) {
        T[] result = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
