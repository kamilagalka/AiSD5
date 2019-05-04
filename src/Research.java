import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Research {

    static void clearAllResults(Algorithm[] algorithms, int[] sequence_lengths, String[] sequence_types) {
        try {
            PrintWriter pw;
            for (Algorithm algorithm : algorithms) {
                for (int sequence_length : sequence_lengths) {
                    for (String sequence_type : sequence_types) {
                        pw = new PrintWriter("results\\" + ((Integer) sequence_length).toString() + "_" + algorithm.getAlgorithmName() + "_" + sequence_type + ".txt");
                        pw.print("");
                        pw.close();
                    }
                }
            }

        } catch (Exception e) {

        }
    }

    static void saveResult(String result, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
            output.write(result);
            output.newLine();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void sort_sequence(Algorithm algorithm, int sequence_length, String sequence_type, int sequence_ID) throws IOException {
        String filePath = "sequences\\" + sequence_length + "_" + sequence_type + ".txt";
        String resultFilePath = "results\\" + sequence_length + "_" + algorithm.getAlgorithmName() + "_" + sequence_type + ".txt";


        String sequenceLine = Files.lines(Paths.get(filePath)).skip(sequence_ID).findFirst().get();
        int[] sequence = new int[sequence_length];
        int[] sequence2 = new int[sequence_length];
        String[] temp = sequenceLine.split(" ");
        for (int i = 0; i < sequence_length; i++) {
            sequence[i] = Integer.parseInt(temp[i]);
            sequence2[i] = Integer.parseInt(temp[i]);
        }
        for (int j = 0; j < 1; j++) {

            long startTime = System.nanoTime();
            //long startTime = System.currentTimeMillis();
            algorithm.runAlgorithm(sequence);
            long estimatedTime = System.nanoTime() - startTime;
            //long estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println("time:" + estimatedTime);
            String result = ((Integer) (int) estimatedTime).toString();
            saveResult(result, resultFilePath);


        }
    }

    public static void main(String[] args) throws IOException {
        Algorithm[] algorithms = new Algorithm[2];
        algorithms[0] = new Quicksort();
        algorithms[1] = new Shellsort();

        int[] sequence_lengths = new int[1];
        sequence_lengths[0] = 2000000;

        String[] sequence_types = new String[4];
        sequence_types[0] = "sorted_backwards";
        sequence_types[1] = "half_sorted";
        sequence_types[2] = "sorted";
        sequence_types[3] = "random";

        clearAllResults(algorithms,sequence_lengths, sequence_types);

        for (Algorithm algorithm : algorithms) {
            for (int sequence_legth : sequence_lengths) {
                for (String sequence_type : sequence_types) {
                    for (int sequenceID = 0; sequenceID < 4; sequenceID++) {
                        sort_sequence(algorithm, sequence_legth, sequence_type, sequenceID);
                    }
                }
            }
        }
    }
}
