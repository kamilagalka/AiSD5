import java.io.*;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(String text, String filename) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
            output.write(text);
            output.write(" ");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static long sort_sequence_and_return_time(Algorithm algorithm, int[] sequence) {
        String filePath = "results\\" + algorithm.getAlgorithmName() + ".txt";

        long startTime = System.nanoTime();
        algorithm.runAlgorithm(sequence);
        long estimatedTime = System.nanoTime() - startTime;
        estimatedTime /= 1000000;
        String result = ((Integer) (int) estimatedTime).toString();
        saveToFile(result, filePath);

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

    private static int[][] readSequences(String filepath){
        int[][] sequences = null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filepath));
                sequences = (int[][]) objectInputStream.readObject();
                objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return sequences;
    }

    public static void main(String[] args) {
        int numberOfSequences = 100;
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

            for (int sequence_length : sequence_lengths) {

                for (String sequence_type : sequence_types) {
                    saveToFile("\n" + ((Integer) sequence_length).toString() + " " + sequence_type + "\n", "results\\" + algorithm.getAlgorithmName() + ".txt");
                    long[] results = new long[numberOfSequences];
                    String filePath = "sequences\\" + sequence_length + "_" + sequence_type + ".txt";
                    int[][] sequences = readSequences(filePath);

                    for (int sequenceID = 0; sequenceID < numberOfSequences; sequenceID++) {
                        results[sequenceID] = sort_sequence_and_return_time(algorithm, sequences[sequenceID]);
                    }

                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
                    double avg = calcAvg(results);
                    double standardDeviation = calcStandardDeviation(results, avg);
                    String avgs = df.format(avg);
                    String standardDeviations = df.format(standardDeviation);
                    saveToFile("\navg: " + avgs + "\tstandardDeviation: " + standardDeviations, "results\\" + algorithm.getAlgorithmName() + ".txt");
                    saveToFile("\n" + avgs + "\t\t" + standardDeviations + "\t\t\t\t" + algorithm.getAlgorithmName() + " " + ((Integer) sequence_length).toString() + " " + sequence_type, "results\\RESULTS.txt");
                }
            }
        }
    }
}
