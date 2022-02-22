import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.util.*;

public class Poem {
    public static void main(String[] args) throws IOException {

        int count = 0;

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        // ArrayList to store each iteration
        ArrayList<String> result = new ArrayList<>();

        while (count < 8) {
            // Scanner Object to ask user for input
            Scanner sb = new Scanner(System.in);

            // Store all the poems existing in the "poems" folder
            File directoryPath = new File("poems");
            String[] contents = directoryPath.list();

            // Print out the contents of the poem folder
            System.out.println("Which poem would you like to take from?");
            for (String s : contents) {
                System.out.println(s);
            }

            // Ask the user which poem they want to take from
            String poem = sb.nextLine();

            // Asking the user which line they want to take
            System.out.println("Which line would you like to take from " + poem);

            int place = sb.nextInt();

            // Printing out the available lines left
            System.out.println("Lines you have left");
            int index = findIndex(arr, place);
            arr = removeTheElement(arr, index);
            printLines(arr);

            // Adding the line to an ArrayList to print out later
            result.add(pickLine(poem, place));
            count++;
        }

        for(String s :result){
            System.out.println(s);
        }
        JTextField myOutput = new JTextField("someInitialValue", 20);
        myOutput.isVisible();
        myOutput.setVisible(true);
    }

    /**
     * The core of the program, returns a line from a poem that the user specifies
     * @param fileName The File object, as a String
     * @param place  The line that a user will pick out, as an int
     * @return the line
     * @throws FileNotFoundException Signals that an attempt to open the file denoted by a specified pathname has failed.
     */
    public static String pickLine(String fileName, int place) throws FileNotFoundException {
        String line = "";
        try {
            line = Files.readAllLines(Paths.get("poems/" + fileName + ".txt")).get(place);
            } catch (IOException er) {
            System.out.println("An error occurred");
            er.printStackTrace();
        }
        return line;
    }

    /**
     * Function to move element from array. From GeeksForGeeks
     * https://www.geeksforgeeks.org/remove-an-element-at-specific-index-from-an-array-in-java/
     * @param arr
     * @param index
     * @return
     */
    public static int[] removeTheElement(int[] arr, int index)
    {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        int[] anotherArray = new int[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    /**
     *  // Linear-search function to find the index of an element from GeeksForGeeks
     *  https://www.geeksforgeeks.org/find-the-index-of-an-array-element-in-java/
     * @param arr
     * @param t
     * @return
     */
    public static int findIndex(int arr[], int t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }

    /**
     * Prints out the array of the lines the user has left
     * @param arr
     */
    public static void printLines(int[] arr){
        for(int i : arr){
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
