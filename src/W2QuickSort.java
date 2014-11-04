/**
 * Created by Maksim.Muzafarov on 22.10.14.
 * Project Week1_CountingInversions
 */

abstract class BaseQuickSort {

    private long comparisonsNum;

    public void qSort(Comparable[] A, int low, int high) {

        if(high - low < 1) return;

        int pivot = selectPivot(A,low,high - 1);
        swap(A, low, pivot);

        int pivotPos = partition(A, low + 1 ,high, low);

        qSort(A, low, pivotPos);
        qSort(A, pivotPos + 1, high);
    }

    protected abstract int selectPivot(Comparable[] A, int low, int high);

    protected int partition(Comparable[] A, int low, int high,int pivotID){
        comparisonsNum += high - low;
        int i = low;
        for(int j = i; j < high;j++)
            if(less(A[j], A[pivotID])){
                swap(A, j, i);
                i++;
            }

        swap(A, pivotID, i - 1);
        return i - 1;

    }

    protected boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    protected void swap(Comparable[] A, int i, int j) {
        Comparable temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public long getComparisonsNum(){
        return comparisonsNum;
    }
}

public class W2QuickSort {

    private static class QuickSortPivotFirst extends BaseQuickSort {
        @Override
        protected int selectPivot(Comparable[] A, int low, int high) {
            return low;
        }
    }

    private static class QuickSortPivotLast extends BaseQuickSort {
        @Override
        protected int selectPivot(Comparable[] A, int low, int high) {
            return high;
        }
    }

    private static class QuickSortPivotMiddle extends BaseQuickSort {
        @Override
        protected int selectPivot(Comparable[] A, int low, int high) {
            int mid = low + (high - low) / 2;

            if ((less(A[low], A[mid]) && less(A[mid], A[high]))  // low < mid < high
                    || (less(A[high], A[mid]) && less(A[mid], A[low]))) // high < mid < low
                return mid;
            else if ((less(A[low], A[high]) && less(A[high], A[mid]))  // low < high < mid
                    || (less(A[mid], A[high]) && less(A[high], A[low]))) // mid < high < low
                return high;

            return low;
        }
    }

    private static void run (BaseQuickSort q, Comparable[] in) {
        q.qSort(in, 0, in.length);
        System.out.println(q.getClass().toString() + " compares " + q.getComparisonsNum());
    }

    public static void main(String[] args) {
        Comparable<Integer>[] in = Utils.getArrayFromFile("./resources/QuickSort.txt");
        run(new QuickSortPivotFirst(), in.clone());
        run(new QuickSortPivotLast(), in.clone());
        run(new QuickSortPivotMiddle(), in.clone());

    }

}