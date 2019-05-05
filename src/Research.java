import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Research {

    private static void clearAllResults(Algorithm[] algorithms) {
        try {
            PrintWriter pw;
            pw = new PrintWriter("results\\RESULTS.txt");
            pw.print("");
            for (Algorithm algorithm : algorithms) {
                pw = new PrintWriter("results\\" + algorithm.getAlgorithmName() + ".txt");
                pw.print("");
                pw.close();
            }
        } catch (Exception ignored) {
        }
    }

    private static void saveToFile(String text, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
            output.write(text);
            output.write(" ");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static long sort_sequence_and_return_time(Algorithm algorithm, int sequence_length, String sequence_type, int sequence_ID) throws IOException {
        String filePath = "sequences\\" + sequence_length + "_" + sequence_type + ".txt";


        String sequenceLine = Files.lines(Paths.get(filePath)).skip(sequence_ID).findFirst().get();
        int[] sequence = new int[sequence_length];
        String[] temp = sequenceLine.split(" ");
        for (int i = 0; i < sequence_length; i++) {
            sequence[i] = Integer.parseInt(temp[i]);
        }

        long startTime = System.nanoTime();
        algorithm.runAlgorithm(sequence);
        long estimatedTime = System.nanoTime() - startTime;
        estimatedTime /= 1000000;
        String result = ((Integer) (int) estimatedTime).toString();
        saveToFile(result, "results\\" + algorithm.getAlgorithmName() + ".txt");

        return estimatedTime;
    }


    private static double calcAvg(long[] results) {
        double sum = 0;
        for (int i = 1; i < results.length; i++) {
            sum += results[i];
        }
        return sum / (results.length - 1);
    }

    private static double calcStandardDeviation(long[] results, double avg) {
        double difsum = 0;
        for (int i = 1; i < results.length; i++) {
            difsum += Math.pow((results[i] - avg), 2);
        }
        return Math.sqrt(difsum / (results.length - 2));
    }

    public static void main(String[] args) throws IOException {
        Algorithm[] algorithms = new Algorithm[4];
        algorithms[0] = new Quicksort();
        algorithms[1] = new Shellsort("knuth");
        algorithms[2] = new Shellsort("sedgewick");
        algorithms[3] = new Combsort();

        int[] sequence_lengths = new int[4];
        sequence_lengths[0] = 100000;
        sequence_lengths[1] = 500000;
        sequence_lengths[2] = 1000000;
        sequence_lengths[3] = 2000000;

        String[] sequence_types = new String[4];
        sequence_types[0] = "sorted_backwards";
        sequence_types[1] = "half_sorted";
        sequence_types[2] = "sorted";
        sequence_types[3] = "random";

        clearAllResults(algorithms);

        for (Algorithm algorithm : algorithms) {
            saveToFile(algorithm.getAlgorithmName(), "results\\" + algorithm.getAlgorithmName() + ".txt");
            for (int sequence_legth : sequence_lengths) {
                for (String sequence_type : sequence_types) {
                    saveToFile("\n" + ((Integer) sequence_legth).toString() + " " + sequence_type + "\n", "results\\" + algorithm.getAlgorithmName() + ".txt");
                    long[] results = new long[100];
                    for (int sequenceID = 0; sequenceID < 100; sequenceID++) {
                        results[sequenceID] = sort_sequence_and_return_time(algorithm, sequence_legth, sequence_type, sequenceID);
                    }
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
                    double avg = calcAvg(results);
                    double standardDeviation = calcStandardDeviation(results, avg);
                    String avgs = df.format(avg);
                    String sds = df.format(standardDeviation);
                    saveToFile("\navg: " + avgs + "\tstandardDeviation: " + sds, "results\\" + algorithm.getAlgorithmName() + ".txt");
                    saveToFile("\n" + avgs + "\t\t" + sds + "\t\t\t\t" + algorithm.getAlgorithmName() + " " + ((Integer) sequence_legth).toString() + " " + sequence_type, "results\\RESULTS.txt");
                }
            }
        }
    }
}
