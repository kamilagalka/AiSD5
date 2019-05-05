public class Quicksort extends Algorithm {

    @Override
    int[] runAlgorithm(int[] sequence) {

        if (sequence == null) {
            return null;
        }
        if (sequence.length == 0) {
            return new int[0];
        }

        return sort(sequence, 0, sequence.length - 1);
    }

    @Override
    public String getAlgorithmName() {
        return "QUICKSORT";
    }

    private int[] sort(int[] sequence, int startIndex, int endIndex) {
        int i, j, pivot;

        i = startIndex;
        j = endIndex;
        pivot = sequence[(startIndex + endIndex) / 2];
        do {
            while (sequence[i] < pivot)
                i++;
            while (pivot < sequence[j])
                j--;
            if (i <= j) {
                int temp = sequence[i];
                sequence[i] = sequence[j];
                sequence[j] = temp;
                i++;
                j--;
            }
        }
        while (i <= j);
        if (startIndex < j)
            sort(sequence, startIndex, j);
        if (i < endIndex)
            sort(sequence, i, endIndex);
        return sequence;
    }
}
