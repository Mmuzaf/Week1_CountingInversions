package me.mmuzaf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Maksim.Muzafarov on 22.10.14.
 * Project Week1_CountingInversions
 */
public class Utils {

    public static Comparable<Integer>[] getArrayFromFile(String filePath) {

        Scanner s = null;
        List<Integer> aList = new ArrayList<Integer>();
        try {
            s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                aList.add(s.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int t = 0;
        Comparable<Integer>[] result = new Integer[aList.size()];
        for (Integer i : aList) {
            result[t] = i;
            t++;
        }
        return result;
    }

    public static void getArraysFromFile(String filePath, SortedSet<Long> positiveSortedSet, SortedSet<Long> negativeSortedSet) {
        Scanner s = null;
        try {
            s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                Long val = s.nextLong();
                if (val >= 0)
                    positiveSortedSet.add(val);
                else
                    negativeSortedSet.add(val);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static Long[] getSortedLongArrayFromFile(String filePath) {

        Scanner s = null;
        Set<Long> aList = new TreeSet<Long>();
        try {
            s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                aList.add(s.nextLong());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Long[] result = new Long[aList.size()];
        aList.toArray(result);
        return result;
    }

    /**
     *
     * @param a  The sorted array 'a'
     * @param value input value to search
     * @return   The index of nearest element to 'value'
     */
    public static <T> int getLowerBoundToKey(T[] a, T value) {
        int low = 0;
        int high = a.length - 1;
        int lowerBound = low;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable midVal = (Comparable) a[mid];
            int cmp = midVal.compareTo(value);

            if (cmp < 0) {
                low = mid + 1;
                lowerBound = low;
            }
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found

        }
        return lowerBound;
    }

    public static int randomInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
