package me.mmuzaf.w6;

import me.mmuzaf.Utils;

import java.util.*;

/**
 * Created by Maksim.Muzafarov on 25.11.14.
 * Project Week1_CountingInversions
 */
public class Algorithm2SumImpl {

    public static long count2SumHits(Long[] sortedArray, long goal) {
        int hits = 0;
        int i = 0;
        int j = Utils.getLowerBoundToKey(sortedArray, goal);
        while (j >= 0 && j < sortedArray.length &&  i < sortedArray.length)  {
            long sum = sortedArray[i] + sortedArray[j];
            if (sum == goal) {
                hits++;
                break;
            }
            if (sum > goal)
                j--;
            else
                i++;
            if (i > j)
                break;
        }
        return hits;
    }

    public static void main(String args[]) {
        // Initialize arrays from file
        Long[] inputArray = Utils.getSortedLongArrayFromFile("./resources/algo1-programming_prob-2sum.txt");
        long minIncrementConstant = Math.abs(inputArray[0]) + 1;
        for (int i = 0; i < inputArray.length; i ++) {
            inputArray[i] += minIncrementConstant;
        }
        int result2Sum = 0;
        for (long i = -10000L; i <= 10000L; i ++) {
            result2Sum += count2SumHits(inputArray, i + minIncrementConstant);
        }
        System.out.println("result2Sum = " + result2Sum);

    }
}
