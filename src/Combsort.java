class Combsort extends Algorithm {

    @Override
    int[] runAlgorithm(int[] sequence) {
        return sort(sequence);
    }

    @Override
    String getAlgorithmName() {
        return "COMBSORT";
    }

    private int[] sort(int[] sequence) {
        if (sequence == null) {
            return null;
        }
        int gap = sequence.length;
        boolean replaced = true;

        while (gap > 1 || replaced) {
            gap = gap * 10 / 13;
            if (gap == 0) {
                gap = 1;
            }

            replaced = false;
            for (int i = 0; i + gap < sequence.length; i++) {
                if (sequence[i + gap] < sequence[i]) {
                    int temp = sequence[i];
                    sequence[i] = sequence[i + gap];
                    sequence[i + gap] = temp;
                    replaced = true;
                }
            }
        }
        return sequence;
    }
}
