import java.util.Scanner;

public class Interface {

    private static void printSequence(int[] sequence) {
        for (int element : sequence) {
            System.out.print(element + " ");
        }
    }

    private static void sort_sequence(Algorithm alg, int[] sequence) {
        System.out.println("Before sort:");
        printSequence(sequence);
        long estimatedTime;

        long startTime = System.nanoTime();
        alg.runAlgorithm(sequence);
        estimatedTime = System.nanoTime() - startTime;


        System.out.println("\n\nAfter sort:");
        printSequence(sequence);
        System.out.println("\nTime: " + estimatedTime + "ns");
    }

    private static Algorithm algorithmChoice(){
        Scanner scanner = new Scanner(System.in);
        Algorithm alg;

        System.out.println("ALGORITHM:\n[1] - Quicksort\n[2] - Shellsort(sedgewick)\n[3] - Shellsort(knuth)\n[4] - Combsort");
        String a = scanner.next();
        switch (a) {
            case "1": {
                alg = new Quicksort();
                break;
            }
            case "2": {
                alg = new Shellsort("sedgewick");
                break;
            }
            case "3": {
                alg = new Shellsort("knuth");
                break;
            }
            case "4": {
                alg = new Combsort();
                break;
            }
            default:
                System.out.println("[invalid input] - using default algorithm (quicksort)");
                alg = new Quicksort();
        }
        return alg;
    }

    private static int sequenceLengthChoice(){
        Scanner scanner = new Scanner(System.in);
        int length;
        System.out.println("\nINSERT SEQUENCE LENGTH: ");
        try {
            length = new Integer(scanner.next());
        } catch (Exception e) {
            System.out.println("[invalid input] - using default length (10)");
            length = 10;
        }
        if (length < 0) {
            System.out.println("[invalid input] - using default length (10)");
            length = 10;
        }
        return length;
    }

    private static int lowerBoundChoice(){
        Scanner scanner = new Scanner(System.in);
        int lowerBound;
        System.out.println("\nINSERT LOWER BOUND: ");
        try {
            lowerBound = new Integer(scanner.next());
        } catch (Exception e) {
            System.out.println("[invalid input] - using default lowerBound (0)");
            lowerBound = 0;
        }
        return lowerBound;
    }

    private static int upperBoundChoice(){
        Scanner scanner = new Scanner(System.in);
        int upperBound;
        System.out.println("\nINSERT UPPER BOUND: ");
        try {
            upperBound = new Integer(scanner.next());
        } catch (Exception e) {
            System.out.println("[invalid input] - using default upperBound (100)");
            upperBound = 100;
        }
        return upperBound;
    }

    private static int[] sequenceTypeChoice(String type, int length, int lowerBound, int upperBound){
        Scanner scanner = new Scanner(System.in);
        SequenceGenerator sequenceGenerator = new SequenceGenerator();
        int[] sequence;
        System.out.println("\nSEQUENCE TYPE:\n[1] - random\n[2] - half sorted\n[3] - sorted\n[4] - sorted backwards");
        String a = scanner.next();
        switch (a) {
            case "1": {
                sequence = sequenceGenerator.generateRandomSequence(length, lowerBound, upperBound);
                type = "RANDOM";
                break;
            }
            case "2": {
                sequence = sequenceGenerator.generateHalfSortedSequence(length, lowerBound, upperBound);
                type = "HALF SORTED";
                break;
            }
            case "3": {
                sequence = sequenceGenerator.generateSortedSequence(length, lowerBound, upperBound);
                type = "SORTED";
                break;
            }
            case "4": {
                sequence = sequenceGenerator.generateSortedBackwardsSequence(length, lowerBound, upperBound);
                type = "SORTED BACKWARDS";
                break;
            }
            default:
                System.out.println("[invalid input] - using default sequence type (random)");
                sequence = sequenceGenerator.generateRandomSequence(length, lowerBound, upperBound);
                type = "RANDOM";
        }
        scanner.close();
        return sequence;
    }

    private static boolean checkBounds(int lowerBound, int upperBound){
        if(upperBound>lowerBound){
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        int[] sequence;
        String type = "";
        Algorithm alg;
        int length;
        int lowerBound;
        int upperBound;

        alg = algorithmChoice();
        length = sequenceLengthChoice();
        lowerBound = lowerBoundChoice();
        upperBound = upperBoundChoice();
        if(!checkBounds(lowerBound,upperBound)){
            System.out.println("[invalid input] - using default lowerBound (0) and upperBound (100)");
            lowerBound = 0;
            upperBound = 100;
        }
        sequence = sequenceTypeChoice(type,length,lowerBound,upperBound);

        System.out.println("\n\nSorting " + length +" "+ type+ " elements " + "(bounds from " + lowerBound + " to " + upperBound+") using "+alg.getAlgorithmName()+ " algorithm");
        sort_sequence(alg, sequence);
    }

}
