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
        for (int i = 0; i < sortedArray.length - 1; i++) {
            int j = Utils.getLowerBoundToKey(sortedArray, i, sortedArray.length - 1, goal);
            while (j >= 0 && j < sortedArray.length &&  i < sortedArray.length && i <= j)  {
                long sum = sortedArray[i] + sortedArray[j];
                if (sum == goal) {
                    hits++;
                    break;
                }
                if (sum > goal)
                    j--;
                else
                    i++;
            }
        }
        return hits;
    }

    public static int count2SumHits_ (Set<Long> set, long goal) {
        int hits = 0;
        for (Long t : set) {
            if ((!t.equals(goal - t)) && set.contains(goal - t))
                hits++;
        }
        return hits;
    }

    public static void main(String args[]) {
        // Initialize arrays from file
        Set<Long> set = new HashSet<Long>();
        Utils.getSetFromFile("./resources/algo1-programming_prob-2sum.txt", set);
        long result2Sum = 0;
        for (long i = -10000L; i <= 10000L; i ++) {
            result2Sum += count2SumHits_(set, i);
        }
        System.out.println("result2Sum = " + result2Sum);

    }
}
