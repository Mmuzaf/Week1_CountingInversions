package me.mmuzaf.w6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by Maksim.Muzafarov on 27.11.14.
 * Project Week1_CountingInversions
 */
public class MedianMaintenance {

    private static int solveMedianMaintenance(String filename, PriorityQueue<Integer> lowestH, PriorityQueue<Integer> highestH) {
        int sum = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                sum += addValue(line, lowestH, highestH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum % 10000;
    }

    private static int addValue(String line, PriorityQueue<Integer> lowestH, PriorityQueue<Integer> highestH) {
        int val = Integer.parseInt(line);
        long mid = highestH.isEmpty() ? Long.MIN_VALUE : highestH.peek();
        // add
        if( val > mid)
            highestH.add(val);
        else
            lowestH.add(val);
        // balance
        if(highestH.size() > lowestH.size() + 1)
            lowestH.add(highestH.poll());
        else if(highestH.size() < lowestH.size())
            highestH.add(lowestH.poll());
        // return mid
        if(highestH.size() > lowestH.size())
            return highestH.peek();
        else
            return lowestH.peek();


    }

    public static void main(String[]args) {
        PriorityQueue<Integer> lowestH = new PriorityQueue<Integer>(11, Collections.reverseOrder());
        PriorityQueue<Integer> highestH = new PriorityQueue<Integer>();

        System.out.println(solveMedianMaintenance("./resources/Median.txt", lowestH, highestH));
    }
}
