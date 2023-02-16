package com.crestdata.utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class PseudoRandomStringCollector<E> {
    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public PseudoRandomStringCollector() {
        this(new Random());
    }

    public PseudoRandomStringCollector(Random random) {
        this.random = random;
    }

    public PseudoRandomStringCollector<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
    public static void generator(String fileName) throws Exception {

        PseudoRandomStringCollector<Object> rc = new PseudoRandomStringCollector<>().
                add(60, "CDS").
                add(20, "wewew23").
                add(20, "R2323aja").
                add(20, "dget34").
                add(20, "3err").
                add(20, "CDStitiu").
                add(20, "sbfnCDSrueeir").
                add(20, "ccxcmCDS");

        long start = System.currentTimeMillis();
        FileWriterUtil.writeRandomStingToFile(rc, fileName);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000f + " seconds");
    }
}