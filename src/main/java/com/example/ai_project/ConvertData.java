package com.example.ai_project;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.*;

public class ConvertData {

    public static void main(String[] args) throws Exception {
        String csvFilePath = "Height_Weight.csv";
        String csvFilePathNew = "output_data.csv";

        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvFilePath));
        Instances data = loader.getDataSet();

        for (int i = 0; i < data.numInstances(); i++) {
            double height = data.instance(i).value(1) * 2.54;
            double weight = data.instance(i).value(2) * 0.453592;
            data.instance(i).setValue(1, height);
            data.instance(i).setValue(2, weight);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePathNew))) {
            writer.write("\"Gender\",\"Height\",\"Weight\"");
            writer.newLine();

            for (int i = 0; i < data.numInstances(); i++) {
                writer.write("\"" + data.instance(i).stringValue(0) + "\"");
                for (int j = 1; j < data.numAttributes(); j++) {
                    writer.write(",\"" + data.instance(i).value(j) + "\"");
                }
                writer.newLine();
            }
        }

        System.out.println("Data conversion complete, The data saved in: " + csvFilePathNew);
    }
}
