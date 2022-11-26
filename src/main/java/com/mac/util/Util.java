package com.mac.util;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Util {

    private Util() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] concatenate(T[] a, T[] b) {
        T[] result = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void pressAnyKey(String message) {
        System.out.println(message);
        new Scanner(System.in).nextLine();
    }
}
