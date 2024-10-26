package com.ciot.base.util;

public class NavigationUtils {
    public static double distanceBetweenTwoPoints(double d, double d2, double d3, double d4) {
        double d5 = d - d3;
        double d6 = d2 - d4;
        return Math.sqrt(Math.abs((d5 * d5) + (d6 * d6)));
    }
}
