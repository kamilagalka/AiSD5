import java.io.*;
import java.util.Random;

public class SequenceGenerator {

    private static void saveSequence(int[] array, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
            for (int i = 0; i < array.length; i++) {
                output.write(((Integer) array[i]).toString());
                output.write(" ");
            }
            output.newLine();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveSequence2(int[] array, String filename) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(array);
        } catch (IOException e){

        }
    }

    int[] generateRandomSequence(int length, int lowerBound, int upperBound) {
        if (upperBound > 0 && lowerBound < 0 || lowerBound == 0 && upperBound > 0) {
            upperBound += 1;
        }
        int[] sequence = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sequence[i] = random.nextInt(upperBound - lowerBound) + lowerBound;
        }
        return sequence;
    }

    int[] generateSortedSequence(int length, int lowerBound, int upperBound) {
        int[] sequence = generateRandomSequence(length, lowerBound, upperBound);
        Quicksort quicksort = new Quicksort();
        quicksort.runAlgorithm(sequence);
        return sequence;
    }

    int[] generateHalfSortedSequence(int length, int lowerBound, int upperBound) {
        int[] sortedHalf = generateSortedSequence(length / 2, lowerBound, lowerBound + (upperBound - lowerBound) / 2);
        int[] randomHalf = generateRandomSequence(length / 2, lowerBound + (upperBound - lowerBound) / 2, upperBound);

        int[] sequence = new int[length];
        for (int i = 0; i < length / 2; i++) {
            sequence[i] = sortedHalf[i];
            sequence[i + length / 2] = randomHalf[i];
        }
        return sequence;
    }


    int[] generateSortedBackwardsSequence(int length, int lowerBound, int upperBound) {
        int[] sequenceInAscendingOrder = generateSortedSequence(length, lowerBound, upperBound);
        int[] sequence = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            sequence[length - 1 - i] = sequenceInAscendingOrder[i];
        }


        return sequence;
    }

    private static void clearAllSequences(int[] sequence_lengths) {
        String[] sequence_types = new String[4];
        sequence_types[0] = "random";
        sequence_types[1] = "half_sorted";
        sequence_types[2] = "sorted";
        sequence_types[3] = "sorted_backwards";
        try {
            PrintWriter pw;
            for (int sequence_length : sequence_lengths) {
                for (String sequence_type : sequence_types) {
                    pw = new PrintWriter("sequences\\" + ((Integer) sequence_length).toString() + "_" + sequence_type + ".txt");
                    pw.print("");
                    pw.close();
                }
            }

        } catch (Exception e) {
            System.out.println("No such file");
        }
    }


    public static void main(String[] args) {
        SequenceGenerator sequenceGenerator = new SequenceGenerator();

        int lowerBound = -100000;
        int upperBound = 100000;
        int[] sequence_lengths = new int[4];
        sequence_lengths[0] = 100000;
        sequence_lengths[1] = 500000;
        sequence_lengths[2] = 1000000;
        sequence_lengths[3] = 2000000;

        clearAllSequences(sequence_lengths);
        for (int i = 0; i < 100; i++) {
            for (int sequence_length : sequence_lengths) {
                saveSequence(sequenceGenerator.generateRandomSequence(sequence_length, lowerBound, upperBound), "sequences\\" + ((Integer) sequence_length).toString() + "_random.txt");
                saveSequence(sequenceGenerator.generateHalfSortedSequence(sequence_length, lowerBound, upperBound), "sequences\\" + ((Integer) sequence_length).toString() + "_half_sorted.txt");
                saveSequence(sequenceGenerator.generateSortedSequence(sequence_length, lowerBound, upperBound), "sequences\\" + ((Integer) sequence_length).toString() + "_sorted.txt");
                saveSequence(sequenceGenerator.generateSortedBackwardsSequence(sequence_length, lowerBound, upperBound), "sequences\\" + ((Integer) sequence_length).toString() + "_sorted_backwards.txt");
            }

        }
    }
}
