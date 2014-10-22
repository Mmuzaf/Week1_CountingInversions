import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Maksim.Muzafarov on 16.10.14.
 * Project
 */
public class W1CountingInversions {

    public long counter;

    private class ArrayCounterContainer {
        int [] array;
        int counter;

        private ArrayCounterContainer(int[] array, int counter) {
            this.array = array;
            this.counter = counter;
        }
    }

    private int [] mergeAndCountSplitInv(int[] A, int[] B, int size) {
        int [] merged = new int[size];
        int i = 0, j = 0;
        for (int k = 0; k < size; k++) {
            if (i < A.length & j < B.length) {
                if (A[i] < B[j]) {
                    merged[k] = A[i];
                    i++;
                } else if (B[j] < A[i]) {
                    merged[k] = B[j];
                    j++;
                    this.counter += A.length - i;
                }
            } else if (i < A.length) {
                merged[k] = A[i];
                i++;
            } else if (j < B.length) {
                merged[k] = B[j];
                j++;
            }
        }
        return merged;
    }

    private int[] sortAndCount(int[] array, int size) {
        int x, y, z;
        int [] result = new int [size];
        if (size == 1)
            return array;
        int ns = size / 2;
        int [] A = Arrays.copyOfRange(array, 0, ns);
        int [] B = Arrays.copyOfRange(array, ns, array.length);
        A = sortAndCount(A, A.length);
        B = sortAndCount(B, B.length);
        result = mergeAndCountSplitInv(A, B, size);
        return result;
    }

    private long bruteForce(int[] array) {
        long result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (i < j & array[i] > array[j])
                    result++;
            }
        }
        return result;
    }

    public static void main (String[] args) {
        W1CountingInversions alg = new W1CountingInversions();
        alg.counter = 0;

        //int array[] = {1, 2, 5, 3, 4, 6};

        Scanner s = null;
        ArrayList<Integer> aList = new ArrayList<Integer>();
        try {
            s = new Scanner(new File("./resources/IntegerArray.txt"));
            while (s.hasNext()) {
                aList.add(s.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] array = new int[aList.size()];
        for (int i = 0; i < aList.size(); i++)
            array[i] = aList.get(i);

        int R[];
        long startTime1 = System.nanoTime();
        R = alg.sortAndCount(array, array.length);
        long endTime1   = System.nanoTime();
        long startTime2 = System.nanoTime();
        long brute = alg.bruteForce(array);
        long endTime2   = System.nanoTime();

        long totalTime1 = endTime1 - startTime1;
        long totalTime2 = endTime2 - startTime2;

        System.out.println("Len = " + array.length);
        System.out.println("Brute force = " + brute + " at time " + totalTime2);
        System.out.println("Counter = " + alg.counter + " at time " + totalTime1);
    }
}
