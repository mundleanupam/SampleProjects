package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;



// This also includes all the problems from the Cracking code chapter 10 Sorting and Searching
// Revisit - Peaks and Valleys, Rank of streams

public class Sort {

    public static void main(String args[])
    {
        int arr[] = {64, 34, 25, 12, 22, 11, 90, 70};
        printArrayBubble("Sorted Array Before Bubble Sort", arr);
        bubbleSort(arr);
        printArrayBubble("Sorted Array After Bubble Sort", arr);
        printArrayBubble("Sorted Array Before Selection Sort", arr);
        selectionSort(arr);
        printArrayBubble("Sorted Array After Selection Sort", arr);
        printArrayBubble("Sorted Array Before Insertion Sort", arr);
        insertionSort(arr);
        printArrayBubble("Sorted Array After Insertion Sort", arr);

        int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
        int[] b = {1, 4, 7, 9, 10, 11};
        int merged[] = merge(a, b, 8, 6);

        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        sort(array);
        System.out.println(AssortedMethods.stringArrayToString(array));

        int arr1[] = {15, 16, 19, 20, 25, 1, 3 ,4 ,5 ,7 ,10 , 14};
        int searchInRotatedArr = searchRotatedArray(a, 0, 11, 5);
        System.out.println(searchInRotatedArr);

        int[] arr2 = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
        Listy list = new Listy(arr2);
        System.out.println(search(list, 14));
        System.out.println(search(list, 1));
        System.out.println(search(list, 18));

        int[][] matrix = new int[10][5];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = 10 * i + j;
            }
        }
        printMatrix(matrix);
        boolean isAvailable = findElementMatrix(matrix, 62);
        System.out.println("Is 62 present in matrix? - " + isAvailable);
        boolean isAvailable1 = findElementMatrix(matrix, 100);
        System.out.println("Is 62 present in matrix? - " + isAvailable1);

    }

    public static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("[");
            for (int j = 0; j < a[0].length; j++) {
                System.out.print("  " + a[i][j] + "  ");
            }
            System.out.println("]");
        }
    }

    // Bubble sort is to have n-1 passes for an int array of size n
    // Keep swapping the elements starting from 0 to n-i-1 (which is everything but the last element)
    // This way every following pass will have 1 less number to compare (rightmost number is the highest which will be excluded)

    public static void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    //Selection sort is to divide an array in 2
    // 1. Sorted   2. Unsorted
    // Find the minimum element from the unsorted array and place it at the beginning which is sorted array
    // Always find the smallest element index(minIndex) from an array and swap it with ith location in a loop

    public static void selectionSort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

    public static void printArrayBubble(String message, int arr[])
    {
        int n = arr.length;
        System.out.print("[ ");
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + ", ");
        System.out.println("]"+ '\n' + message);
    }


    /** Merges array
     * @param a first array
     * @param b second array
     * @param countA number of "real" elements in a
     * @param countB number of "real" elements in b
     */
    public static int[] merge(int[] a, int[] b, int countA, int countB) {
        int indexMerged = countB + countA - 1; /* Index of last location of merged array */
        int indexA = countA - 1; /* Index of last element in array b */
        int indexB = countB - 1; /* Index of last element in array a */

        /* Merge a and b, starting from the last element in each */
        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of A is bigger than end of B */
                a[indexMerged] = a[indexA]; // copy element
                indexA--;
            } else {
                a[indexMerged] = b[indexB]; // copy element
                indexB--;
            }
            indexMerged--; // move indices
        }
        return a;
    }

    // Gather all the palindrome string in an array next to each other
    // Input - {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
    // Output - {"apple", papel, "carrot", "tarroc", "duck", "cudk", "ele", "eel", "lee", "banana"};

    public static void sort(String[] array) {
        HashMap<String, ArrayList<String>> mapList = new HashMap<String, ArrayList<String>>();
        /* Group words by anagram */
        for (String s : array) {
            String key = sortChars(s);
            if(mapList.containsKey(key)){
                ArrayList<String> list = mapList.get(key);
                list.add(s);
                mapList.put(key, list);
            }
            else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(s);
                mapList.put(key, list);
            }
        }

        /* Convert hash table to array */
        int index = 0;
        for (String key : mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);
            for (String t : list) {
                array[index] = t;
                index++;
            }
        }
    }

    public static String sortChars(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }


    // Search in an sorted array which is rotated multiple times
    // Input - find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5 ,7, 10, 14}
    // 0+11/2
    // Output - 8 (the index of 5 in the array)

    public static int searchRotatedArray(int a[], int left, int right, int x) {
        int mid = (left + right) / 2;
        if (x == a[mid]) { // Found element
            return mid;
        }
        if (right < left) {
            return -1;
        }

        /* While there may be an inflection point due to the rotation, either the left or
         * right half must be normally ordered.  We can look at the normally ordered half
         * to make a determination as to which half we should search.
         */
        if (a[left] < a[mid]) { // Left is normally ordered.
            if (x >= a[left] && x < a[mid]) {
                return searchRotatedArray(a, left, mid - 1, x);
            } else {
                return searchRotatedArray(a, mid + 1, right, x);
            }
        } else if (a[mid] < a[right]) { // Right is normally ordered.
            if (x > a[mid] && x <= a[right]) {
                return searchRotatedArray(a, mid + 1, right, x);
            } else {
                return searchRotatedArray(a, left, mid - 1, x);
            }
        } else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) { // If right half is different, search there
                return searchRotatedArray(a, mid + 1, right, x);
            } else { // Else, we have to search both halves
                int result = searchRotatedArray(a, left, mid - 1, x);
                if (result == -1) {
                    return searchRotatedArray(a, mid + 1, right, x);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }

    public static int binarySearch(Listy list, int value, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            int middle = list.elementAt(mid);
            if (middle > value || middle == -1) {
                high = mid - 1;
            } else if (middle < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search(Listy list, int value) {
        int index = 1;
        while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
            index *= 2;
        }
        return binarySearch(list, value, index / 2, index);
    }

    // This is a sorted matrix meaning all the elements at rows and columns at any given index [m][n] are
    // 1. Greater than their previous rows
    // 2. Greater than their previous columns

    public static boolean findElementMatrix(int[][] matrix, int elem) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == elem) {
                return true;
            } else if (matrix[row][col] > elem) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

}
