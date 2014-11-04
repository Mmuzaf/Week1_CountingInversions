import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
