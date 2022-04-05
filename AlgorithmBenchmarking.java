package ie.gmit.dip;

import java.util.*;

/*
This class performs the Benchmarking of various Sorting Algorithms. This application should include implementations of the five sorting algorithms,
along with a main method which tests each of them. To benchmark the algorithms, this program uses arrays of randomly generated integers with
different input sizes n. It uses varieties of different input sizes, e.g. n=10,n=100,n=500,. . . ,n=10,000 etc. to test the effect of the input size
on the running time of each algorithm. The running time (in milliseconds) for each algorithm is measured 10 times, and the average of the 10 runs for
each algorithm and each input size n is printed in the output to the console when the program finishes executing.
 */
public class AlgorithmBenchmarking {

    public static final int sampleSize1 = 100;
    public static final int sampleSize2 = 250;
    public static final int sampleSize3 = 500;
    public static final int sampleSize4 = 750;
    public static final int sampleSize5 = 1000;
    public static final int sampleSize6 = 1250;
    public static final int sampleSize7 = 2500;
    public static final int sampleSize8 = 3750;
    public static final int sampleSize9 = 5000;
    public static final int sampleSize10 = 6250;
    public static final int sampleSize11 = 7500;
    public static final int sampleSize12 = 8750;
    public static final int sampleSize13 = 10000;
    public static final int sampleSize14 = 20000;
    public static final int sampleSize15 = 50000;
    public static final String BUBBLE_SORT = "Bubble Sort";
    public static final String SELECTION_SORT = "Selection Sort";
    public static final String MERGE_SORT = "Merge Sort";
    public static final String COUNTING_SORT = "Counting Sort";
    public static final String QUICK_SORT = "Quick Sort";

    /*
    / This is the main method which tests each of the Algorithms for its performance
    */
    public static void main(String[] args) {


        // An array of Sample Sizes are defined
        int[] sampleSizes = new int[]{sampleSize1, sampleSize2, sampleSize3, sampleSize4, sampleSize5, sampleSize6, sampleSize7,
                sampleSize8, sampleSize9, sampleSize10, sampleSize11, sampleSize12, sampleSize13, sampleSize14, sampleSize15};

        // The list of Algorithm Names to be considered for the Benchmarking is stored in an ArrayList
        List<String> algorithmList = new ArrayList<>();
        algorithmList.add(BUBBLE_SORT);
        algorithmList.add(SELECTION_SORT);
        algorithmList.add(MERGE_SORT);
        algorithmList.add(COUNTING_SORT);
        algorithmList.add(QUICK_SORT);

        // The below code is for Bubble Sort Benchmarking
        // A Map is declared to store the Sample Size to Average Time to print later in the console
        Map<Integer, Double> bubbleSortSampleSizeToAvgTimeMap = getSampleSizeToAvgTimeMap(sampleSizes, BUBBLE_SORT);

        // The below code is for Selection Sort Benchmarking
        // A Map is declared to store the Sample Size to Average Time to print later in the console
        Map<Integer, Double> selectionSortSampleSizeToAvgTimeMap = getSampleSizeToAvgTimeMap(sampleSizes, SELECTION_SORT);

        // Merge sort Benchmarking
        // A Map is declared to store the Sample Size to Average Time to print later in the console
        Map<Integer, Double> mergeSortSampleSizeToAvgTimeMap = getSampleSizeToAvgTimeMap(sampleSizes, MERGE_SORT);

        // Counting Sort Benchmarking
        // A Map is declared to store the Sample Size to Average Time to print later in the console
        Map<Integer, Double> countSortSampleSizeToAvgTimeMap = getSampleSizeToAvgTimeMap(sampleSizes, COUNTING_SORT);

        // Quick Sort Benchmarking
        // A Map is declared to store the Sample Size to Average Time to print later in the console
        Map<Integer, Double> quickSortSampleSizeToAvgTimeMap = getSampleSizeToAvgTimeMap(sampleSizes, QUICK_SORT);

        // String format for the Table heading
        String leftAlignFormat = "%-15s  %3.3f     %3.3f     %3.3f     %3.3f     %3.3f       %3.3f     %3.3f      %8.3f     %8.3f      %8.3f       %8.3f       %8.3f        %8.3f        %8.3f        %8.3f %n";


        // Table Heading with Sample Sizes are defined
        System.out.format("Size             100       250       500       750       1000        1250      2500          3750         5000          6250           7500          8750            10000           20000          50000 %n");


        // Checking the Algorithm Name from the algorithmList to print the Benchmarking data is stored in Sample Size to Average Time Map
        for (String algorithmName : algorithmList) {
            // Fetches the corresponding Algorithm Benchmarking data and print to the console
            if (algorithmName.equals(BUBBLE_SORT)) {
                printFormattedData(bubbleSortSampleSizeToAvgTimeMap, leftAlignFormat, algorithmName);
            } else if (algorithmName.equals(SELECTION_SORT)) {
                printFormattedData(selectionSortSampleSizeToAvgTimeMap, leftAlignFormat, algorithmName);
            } else if (algorithmName.equals(MERGE_SORT)) {
                printFormattedData(mergeSortSampleSizeToAvgTimeMap, leftAlignFormat, algorithmName);
            } else if (algorithmName.equals(COUNTING_SORT)) {
                printFormattedData(countSortSampleSizeToAvgTimeMap, leftAlignFormat, algorithmName);
            } else if (algorithmName.equals(QUICK_SORT)) {
                printFormattedData(quickSortSampleSizeToAvgTimeMap, leftAlignFormat, algorithmName);
            }
        }

    }

    private static  Map<Integer, Double> getSampleSizeToAvgTimeMap(int[] sampleSizes, String algorithmName) {

        Map<Integer, Double> sampleSizeToAvgTimeMap = new HashMap<>();
        // Iterate through all the sample sizes defined in the array
        for (int i = 0; i < sampleSizes.length; i++) {
            // Initialised the totalElapsedMillis
            double totalElapsedMillis = 0.0d;
            // The running time (in milliseconds) for each algorithm is measured 10 times
            for (int j = 0; j < 10; j++) {
                int[] array = generateRandomArray(sampleSizes[i]);
                long startTime = System.nanoTime();
                selectAlgorithmAndExecute(algorithmName, array);
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                double elapsedMillis = timeElapsed / 1000000;
                // Adding Total Elapsed Millis time with it's previous value
                totalElapsedMillis += elapsedMillis;
            }
            // The average of the 10 runs for each algorithm and each input size n is calculated
            double avgTimeElapsedMillis = totalElapsedMillis / 10;
            // Sample Size to Average Time is stored in the Map to print later in the console
            sampleSizeToAvgTimeMap.put(sampleSizes[i], avgTimeElapsedMillis);
        }

        return sampleSizeToAvgTimeMap;
    }

    private static void selectAlgorithmAndExecute(String algorithmName, int[] array) {
        switch (algorithmName) {
            case BUBBLE_SORT:
                bubbleSort(array);
                break;
            case SELECTION_SORT:
                selectionSort(array);
                break;
            case MERGE_SORT:
                mergeSort(array, array.length);
                break;
            case COUNTING_SORT:
                countSort(array, array.length);
                break;
            case QUICK_SORT:
                quickSort(array, 0, array.length - 1);
                break;
            default:
                System.out.println("Unknown Algorithm!");
                break;
        }
    }

    private static void printFormattedData(Map<Integer, Double> sortedSampleSizeToAvgTimeMap, String leftAlignFormat, String algorithmName) {
        System.out.format(leftAlignFormat, algorithmName, sortedSampleSizeToAvgTimeMap.get(sampleSize1), sortedSampleSizeToAvgTimeMap.get(sampleSize2),
                sortedSampleSizeToAvgTimeMap.get(sampleSize3), sortedSampleSizeToAvgTimeMap.get(sampleSize4), sortedSampleSizeToAvgTimeMap.get(sampleSize5),
                sortedSampleSizeToAvgTimeMap.get(sampleSize6), sortedSampleSizeToAvgTimeMap.get(sampleSize7), sortedSampleSizeToAvgTimeMap.get(sampleSize8),
                sortedSampleSizeToAvgTimeMap.get(sampleSize9), sortedSampleSizeToAvgTimeMap.get(sampleSize10), sortedSampleSizeToAvgTimeMap.get(sampleSize11),
                sortedSampleSizeToAvgTimeMap.get(sampleSize12), sortedSampleSizeToAvgTimeMap.get(sampleSize13), sortedSampleSizeToAvgTimeMap.get(sampleSize14),
                sortedSampleSizeToAvgTimeMap.get(sampleSize15));
    }

    /*
    Generates Random Array arrays of randomly generated integers with different input sizes n
    */
    public static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }

    // Bubble sort
    //reference: https://www.geeksforgeeks.org/bubble-sort/ 
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    // Selection sort
    //reference:https://www.geeksforgeeks.org/selection-sort/ 
    public static void selectionSort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted sub array
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // Merge Sort
    //reference: https://www.baeldung.com/java-merge-sort 
    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private static void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    // Counting Sort
    // https://www.programiz.com/dsa/counting-sort
    public static void countSort(int array[], int size) {
        int[] output = new int[size + 1];

        // Find the largest element of the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }

        // Store the count of each element
        for (int i = 0; i < size; i++) {
            count[array[i]]++;
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the sorted elements into original array
        for (int i = 0; i < size; i++) {
            array[i] = output[i];
        }
    }

    // Quick Sort
    //reference: https://www.geeksforgeeks.org/quick-sort/ 

    // A utility function to swap two elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    private static int partition(int[] arr, int low, int high) {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

}



