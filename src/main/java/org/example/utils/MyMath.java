package org.example.utils;

import java.util.List;

public class MyMath {
    public static double calculateAverage(List<Double> l) {
        double sum = 0.0;
        for (double v : l) { sum += v; }
        double average = sum / l.size();

        return average;
    }

    public static double calculateStandardDeviation(List<Double> l) {
        double sum = 0.0;
        double average = calculateAverage(l);
        for (double v : l) {
            sum = sum + Math.pow((v - average), 2);
        }
        double standardDeviation = Math.sqrt(sum / l.size());

        return standardDeviation;
    }
}
