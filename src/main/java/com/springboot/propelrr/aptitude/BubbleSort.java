package com.springboot.propelrr.aptitude;

public class BubbleSort {

    int[] bubble_sorted_numbers;
    int[] original;

    // array of integer as parameter
    public BubbleSort(int[] numbers) {
        original = (int[]) numbers.clone();
        // Do Bubble Sorting on Init

        int temp;
        for (int j = 0; j <= numbers.length - 2; j++) {
            for (int i = 0; i <= numbers.length - 2; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    temp = numbers[i + 1];
                    numbers[i + 1] = numbers[i];
                    numbers[i] = temp;

                }
            }
        }

        // Assiged Bubble Sorted Params to internal variable
        bubble_sorted_numbers = numbers;

    }

    public int[] getSortedValue() {
        return bubble_sorted_numbers;
    }

    public int getMedian() {
        int n = bubble_sorted_numbers.length;
        float median;
        if (n % 2 == 0) {
            median = (bubble_sorted_numbers[(n / 2) - 1] + bubble_sorted_numbers[n / 2]) / 2.0F;
        } else {
            median = bubble_sorted_numbers[(n / 2)];
        }
        return (int) median;
    }

    public int getLargest() {
        return bubble_sorted_numbers[bubble_sorted_numbers.length - 1];
    }

    public int[] getOriginal() {
        return original;
    }

}