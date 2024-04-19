package com.example.ai_project;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.experiment.Stats;
import java.util.Locale;
import java.io.File;

public class PrintStatistics {

    public static void main(String[] args) throws Exception {
        String file = "output_data.csv";
        CSVLoader loaderNew = new CSVLoader();
        loaderNew.setSource(new File(file));
        Instances dataNew = loaderNew.getDataSet();

        String[] attributeNames = {"Height", "Weight"};

        System.out.println("The Statistics:");
        System.out.printf("%-15s", "Attribute");
        for (String attributeName : attributeNames) {
            System.out.printf("%-15s", attributeName);
        }
        System.out.println();

        String[] statsNames = {"Mean", "Median", "StdDev", "Min", "Max"};
        for (String statName : statsNames) {
            System.out.printf("%-15s", statName);
            for (String attributeName : attributeNames) {
                int attrIndex = dataNew.attribute(attributeName).index();
                Stats attributeStats = dataNew.attributeStats(attrIndex).numericStats;

                if (attributeStats != null) {
                    double statValue;
                    switch (statName) {
                        case "Mean":
                            statValue = attributeStats.mean;
                            break;
                        case "Median":
                            double[] values = dataNew.attributeToDoubleArray(attrIndex);
                            java.util.Arrays.sort(values);
                            statValue = calculateMedian(values);
                            break;
                        case "StdDev":
                            statValue = attributeStats.stdDev;
                            break;
                        case "Min":
                            statValue = attributeStats.min;
                            break;
                        case "Max":
                            statValue = attributeStats.max;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + statName);
                    }
                    System.out.printf(Locale.US, "%-15.2f", statValue);
                }
            }
            System.out.println();
        }
    }

    private static double calculateMedian(double[] values) {
        if (values.length % 2 == 0) {
            return (values[values.length / 2] + values[values.length / 2 - 1]) / 2.0;
        } else {
            return values[values.length / 2];
        }
    }
}
