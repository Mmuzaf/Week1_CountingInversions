import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Maksim.Muzafarov on 22.10.14.
 * Project Week1_CountingInversions
 */
public class ArrayFromFileGetter {

    public static Comparable<Integer>[] get(String filePath) {

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
}
