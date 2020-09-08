package com.quantumn.future.algorithm;

import java.util.Arrays;

public class ArrayTranverse {
    private static final int RESIZE_STAMP_BITS = 16;
    /**
     * The bit shift for recording size stamp in sizeCtl.
     */
    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;

    public void tranverse(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            swap(a, left++, right--);
        }
    }

    private void swap(int[] a, int left, int right) {
        //借助异或操作，异或有两个特性，a^a=0,a^0=a,对于整数可以理解成4^2=6 6^2=4 6^4=2,完成了值的交换
        a[left] = a[left] ^ a[right];
        a[right] = a[left] ^ a[right];
        a[left] = a[left] ^ a[right];
    }

    public static void main(String[] args) {
//        int[] a = new int[]{1, 2, 3, 4, 5};
//        ArrayTranverse demo = new ArrayTranverse();
//        demo.tranverse(a);
//        Arrays.stream(a).forEach(i-> System.out.print(i+","));

//        System.out.println(1&3);
//        System.out.println(2&3);
//        System.out.println(3&3);

//        System.out.println(Integer.numberOfLeadingZeros(0));
//        System.out.println(Integer.numberOfLeadingZeros(15));
//        System.out.println(Integer.numberOfLeadingZeros(16));
//        System.out.println(Integer.numberOfLeadingZeros(31));
//        System.out.println(Integer.numberOfLeadingZeros(32));
//        int initialCapacity = 16;
//        System.out.println(tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));

        System.out.println(resizeStamp(16));
        int rs = resizeStamp(16);
        System.out.println(rs>>RESIZE_STAMP_SHIFT);
        int sc = rs << RESIZE_STAMP_SHIFT + 2;
        System.out.println(sc);
        System.out.println(sc>>>RESIZE_STAMP_SHIFT);

    }

    private static final int MAXIMUM_CAPACITY = 1<<30;
    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
    }
}
