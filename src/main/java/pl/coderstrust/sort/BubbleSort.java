package pl.coderstrust.sort;

class BubbleSort {

    public static int[] sort(int[] array) {
        if (array == null) {
            throw new NullPointerException("Null array can't be sorted.");
        }
        int[] arrayToSort = array.clone();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arrayToSort.length - 1; i++) {
                if (arrayToSort[i] > arrayToSort[i + 1]) {
                    swap(arrayToSort, i, i + 1);
                    swapped = true;
                }
            }
        } while(swapped);
        return arrayToSort;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
}
