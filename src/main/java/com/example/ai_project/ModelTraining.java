package com.example.ai_project;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ModelTraining {

    public static void main(String[] args) throws Exception {
        // Load the dataset
        String csvFilePath = "output_data.csv"; // Update with your CSV file path
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvFilePath));
        Instances dataset = loader.getDataSet();

        // Assuming last attribute is the target variable
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // Randomize the data
        Randomize randFilter = new Randomize();
        randFilter.setRandomSeed(1); // Seed for reproducibility
        randFilter.setInputFormat(dataset);
        Instances randomizedData = Filter.useFilter(dataset, randFilter);

        // Split data into training (70%) and test (30%) sets
        int trainSize = (int) Math.round(randomizedData.numInstances() * 0.7);
        int testSize = randomizedData.numInstances() - trainSize;
        Instances train = new Instances(randomizedData, 0, trainSize);
        Instances test = new Instances(randomizedData, trainSize, testSize);

        // Create and train the linear regression model
        LinearRegression model = new LinearRegression();
        model.buildClassifier(train);

        // Evaluate the model
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(model, test);
        System.out.println(eval.toSummaryString("\nEvaluation Results\n==================\n", false));

        // Save the trained model
        String modelPath = "linear_regression.model"; // Define your model path here
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelPath));
        oos.writeObject(model);
        oos.flush();
        oos.close();

        System.out.println("Model has been trained and saved to: " + modelPath);
    }
}
