package com.example.ai_project;

public class EntropyCalculation {

    public static void main(String[] args) {
        // Example usage
        int positive = 4; // Number of injured 'Yes'
        int negative = 6; // Number of injured 'No'


        double entropy = calculateEntropy(positive, negative);
        System.out.println("Entropy of the dataset is: " + entropy);
    }

    /**
     * This method calculates the entropy of a dataset based on the count of positive and negative examples.
     *
     * @param positive The count of positive examples.
     * @param negative The count of negative examples.
     * @return The entropy value.
     */
    public static double calculateEntropy(int positive, int negative) {
        int total = positive + negative;
        double pPositive = positive / (double) total;
        double pNegative = negative / (double) total;

        // Calculate entropy for positive and negative separately to handle cases where one of them is zero
        double entropyPositive = pPositive == 0 ? 0 : -pPositive * (Math.log(pPositive) / Math.log(2));
        double entropyNegative = pNegative == 0 ? 0 : -pNegative * (Math.log(pNegative) / Math.log(2));

        // Total entropy
        return entropyPositive + entropyNegative;
    }
}

