
public class Quicksort extends Algorithm {


    @Override
    public int[] runAlgorithm(int[] sequence) {

        return quicksort(sequence,0,sequence.length-1);
    }


    private int[] quicksort(int sequence[], int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int partitionIndex = partition(sequence, startIndex, endIndex);

            quicksort(sequence, startIndex, partitionIndex-1);
            quicksort(sequence, partitionIndex+1, endIndex);
        }
        return sequence;
    }

    private int partition(int sequence[], int startIndex, int endIndex) {
        int pivot = sequence[endIndex];
        int i = (startIndex-1);

        for (int j = startIndex; j < endIndex; j++) {
            if (sequence[j] <= pivot) {
                i++;

                int swapTemp = sequence[i];
                sequence[i] = sequence[j];
                sequence[j] = swapTemp;
            }
        }

        int swapTemp = sequence[i+1];
        sequence[i+1] = sequence[endIndex];
        sequence[endIndex] = swapTemp;

        return i+1;
    }
}
