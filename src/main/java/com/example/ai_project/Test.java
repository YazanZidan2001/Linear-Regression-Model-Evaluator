package com.example.ai_project;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("Height_Weight.csv"));
        Instances data = loader.getDataSet();

        int numInstances = data.numInstances();
        int numAttributes = data.numAttributes();

        for (int i = 0; i < numInstances; i++) {
            System.out.println("Instance " + (i + 1) + ":");
            for (int j = 0; j < numAttributes; j++) {
                System.out.println("Attribute " + data.attribute(j).name() + ": " + data.instance(i).value(j));
            }
            System.out.println(); // Separate instances with a blank line
        }
    }
}