import java.util.ArrayList;
import java.util.Collections;

class Shellsort extends Algorithm {
    private String gapSequence;
    private String algorithmName;

    Shellsort(String gapSequence) {
        this.gapSequence = gapSequence;
        algorithmName = "SHELLSORT"+gapSequence;
    }

    @Override
    int[] runAlgorithm(int[] sequence) {
        if(sequence==null){
            return null;
        }
        if(gapSequence.equals("knuth")) {
            return sort(sequence, knuthDistance(sequence.length));
        }
        else if(gapSequence.equals("shell")) {
            return sort(sequence, shellDistance(sequence.length));
        }
        else return sort(sequence,sedgewickDistance(sequence.length));
    }

    @Override
    String getAlgorithmName() {
        return algorithmName;
    }


    private int[] sort(int[] sequence, Integer[] distances) {
        if(sequence==null){
            return null;
        }
        if(sequence.length==0){
            return new int[0];
        }
        int k = 1;
        int distance = distances[distances.length - k];
        int current, otherID;
        while (distance >= 1) {
            for (int i = distance; i < sequence.length; i++) {
                current = sequence[i];
                otherID = i;
                while (otherID >= distance && current < sequence[otherID - distance]) {
                    sequence[otherID] = sequence[otherID - distance];
                    otherID -= distance;
                }
                sequence[otherID] = current;
            }
            distance = distances[distances.length - ++k];
        }
        return sequence;
    }

    private static Integer[] shellDistance(int numberOfElements) {
        ArrayList<Integer> distances = new ArrayList<>();
        int iteration = 1;
        int generated;
        do {
            generated = numberOfElements / (int) Math.pow(2, iteration);
            distances.add(generated);
            iteration++;
        } while (generated > 0);
        Collections.reverse(distances);
        return distances.toArray(new Integer[distances.size()]);
    }

    private static Integer[] sedgewickDistance(int numberOfElements) {
        int generated = 0;
        ArrayList<Integer> distances = new ArrayList<>();
        int k = 0;
        while (generated < numberOfElements) {
            if (distances.isEmpty()) {
                distances.add(0);
                distances.add(1);
            } else {
                generated = (int) (Math.pow(4, k) + 3 * Math.pow(2, k - 1) + 1);
                if (generated < numberOfElements) {
                    distances.add(generated);

                }
            }
            k++;
        }
        return distances.toArray(new Integer[distances.size()]);
    }

    private static Integer[] knuthDistance(int numberOfElements) {
        int generated = 0;
        ArrayList<Integer> distances = new ArrayList<>();
        int k = 0;
        while (generated < numberOfElements) {

            generated = (int) ((Math.pow(3, k) - 1) / 2);
            if (generated < numberOfElements) {
                distances.add(generated);
            }
            k++;
        }

        return distances.toArray(new Integer[distances.size()]);
    }

}
