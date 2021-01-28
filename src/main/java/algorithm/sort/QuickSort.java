package algorithm.sort;

public class QuickSort {
    private void quickSort(int[] array, int left, int right) {
        while (left < right) {
            int pivot = partition(array, left, right);
            if (pivot - left < right - pivot) { // pivot < (right - left) / 2
                quickSort(array, left, pivot - 1); // sort left part with small size
                left = pivot + 1;
            } else {
                quickSort(array, pivot + 1, right);
                right = pivot - 1;
            }
        }
    }

    private int partition(int[] array, int left, int right) {
        if (left == right) {
            return left;
        }
        return left + 1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, -1, 5, -8, 0, 11, 9};
        QuickSort test = new QuickSort();
        test.quickSort(array, 0, array.length - 1);
    }
}
