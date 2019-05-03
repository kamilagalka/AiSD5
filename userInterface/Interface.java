import java.util.Scanner;

public class Interface {

    static void printSequence(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i] + " ");
        }
    }

    static void sort_sequence(String alg, int[] sequence) {
        System.out.println("Before sort:");
        printSequence(sequence);
        long estimatedTime = 0;
        switch (alg) {
            case "quicksort": {
                Quicksort quicksort = new Quicksort();
                long startTime = System.nanoTime();
                quicksort.runAlgorithm(sequence);
                estimatedTime = System.nanoTime() - startTime;
            }
        }
        System.out.println("\n\nAfter sort:");
        printSequence(sequence);
        System.out.println("\nTime: " + estimatedTime + "ns");
    }

    public static void main(String[] args) {
        String alg;
        int[] sequence;
        int length;
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("ALGORITHM:\n[1] - Quicksort");
        int a = scanner.nextInt();
        switch (a) {
            case 1: {
                alg = "quicksort";
                break;
            }
            default:
                alg = null;
        }

        System.out.println("Insert sequence length: ");
        length = scanner.nextInt();

        System.out.println("Insert lower bound: ");
        int lowerBound = scanner.nextInt();

        System.out.println("Insert upper bound: ");
        int upperBound = scanner.nextInt();


        System.out.println("Sequence type:\n[1] - random\n[2] - half sorted\n[3] - sorted\n[4] - sorted backwards");
        a = scanner.nextInt();
        switch (a) {
            case 1: {
                sequence = sequenceGenerator.generateRandomSequence(length, lowerBound, upperBound);
                break;
            }
            case 2: {
                sequence = sequenceGenerator.generateHalfSortedSequence(length, lowerBound, upperBound);
                break;
            }
            case 3: {
                sequence = sequenceGenerator.generateSortedSequence(length, lowerBound, upperBound);
                break;
            }
            case 4: {
                sequence = sequenceGenerator.generateSortedBackwardsSequence(length, lowerBound, upperBound);
                break;
            }
            default:
                sequence = sequenceGenerator.generateRandomSequence(length, lowerBound, upperBound);
        }

        sort_sequence(alg, sequence);
    }

}
