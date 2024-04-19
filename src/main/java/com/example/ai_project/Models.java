package com.example.ai_project;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.Evaluation;
import java.io.File;
import java.util.Random;

public class Models {

    public static void main(String[] args) throws Exception {
        String file = "output_data.csv";
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(file));
        Instances dataset = loader.getDataSet();
        
        createModel(dataset, 100, "M1");
        createModel(dataset, 1000, "M2");
        createModel(dataset, 5000, "M3");
        createModel(dataset, dataset.numInstances(), "M4");
    }

    private static void createModel(Instances data, int numInstances, String modelName) throws Exception {
        Instances subset;
        Random rand = new Random(1);

        Randomize randFilter = new Randomize();
        randFilter.setRandomSeed(rand.nextInt());
        randFilter.setInputFormat(data);
        Instances randomizedData = Filter.useFilter(data, randFilter);

        if (numInstances < data.numInstances()) {
            randomizedData.randomize(rand);
            subset = new Instances(randomizedData, 0, numInstances);
        } else {
            subset = randomizedData;
        }

        subset.setClassIndex(subset.numAttributes() - 1);

        int trainSize = (int) Math.round(subset.numInstances() * 0.7);
        int testSize = subset.numInstances() - trainSize;
        Instances train = new Instances(subset, 0, trainSize);
        Instances test = new Instances(subset, trainSize, testSize);

        LinearRegression model = new LinearRegression();
        model.buildClassifier(train);

        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(model, test);

        System.out.println("The result of Model :"+modelName);
        System.out.println("These results are for 30% of the sample");
        System.out.println(eval.toSummaryString("", false));

    }
}
