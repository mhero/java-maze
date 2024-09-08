package com.mac.util;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Util {
    private static Scanner sc;

    public static <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public static void pressAnyKey(String message) {
        sc = new Scanner(System.in);
        System.out.println(message);
        sc.nextLine();
    }
}
