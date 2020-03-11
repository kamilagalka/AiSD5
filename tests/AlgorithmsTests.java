import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTests {

    private Quicksort quicksort = new Quicksort();

    @Test
    void nullAsArrayQuicksortTest() {
        int[] emptyArray = null;
        int[] expected = null;
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }


    @Test
    void emptyArrayQuicksortTest() {
        int[] emptyArray = new int[0];
        int[] expected = {};
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void oneElementQuicksortTest() {
        int[] oneElementArray = {1};
        int[] expected = {1};
        int[] actual = quicksort.runAlgorithm(oneElementArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void twoElementsQuicksortTest() {
        int[] twoElementsArray = {2, 1};
        int[] expected = {1, 2};
        int[] actual = quicksort.runAlgorithm(twoElementsArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void smallArrayQuicksortTest() {
        int[] emptyArray = {-12, 123, -4, -12, 0, 0, 1, 1634, -4112412};
        int[] expected = {-4112412, -12, -12, -4, 0, 0, 1, 123, 1634};
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }


    private Combsort combsort = new Combsort();

    @Test
    void nullAsArrayCombsortTest() {
        int[] emptyArray = null;
        int[] expected = null;
        int[] actual = combsort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void emptyArrayCombsortTest() {
        int[] emptyArray = new int[0];
        int[] expected = {};
        int[] actual = combsort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void oneElementCombsortTest() {
        int[] oneElementArray = {1};
        int[] expected = {1};
        int[] actual = combsort.runAlgorithm(oneElementArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void twoElementsCombsortTest() {
        int[] twoElementsArray = {2, 1};
        int[] expected = {1, 2};
        int[] actual = combsort.runAlgorithm(twoElementsArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void smallArrayCombsortTest() {
        int[] emptyArray = {-12, 123, -4, -12, 0, 0, 1, 1634, -4112412};
        int[] expected = {-4112412, -12, -12, -4, 0, 0, 1, 123, 1634};
        int[] actual = combsort.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }


    private Shellsort shellsortSedgewick = new Shellsort("sedgewick");

    @Test
    void nullAsArrayShellsortSedgewickTest() {
        int[] emptyArray = null;
        int[] expected = null;
        int[] actual = shellsortSedgewick.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void emptyArrayShellsortSedgewickTest() {
        int[] emptyArray = new int[0];
        int[] expected = {};
        int[] actual = shellsortSedgewick.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void oneElementShellsortSedgewickTest() {
        int[] oneElementArray = {1};
        int[] expected = {1};
        int[] actual = shellsortSedgewick.runAlgorithm(oneElementArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void twoElementsShellsortSedgewickTest() {
        int[] twoElementsArray = {2, 1};
        int[] expected = {1, 2};
        int[] actual = shellsortSedgewick.runAlgorithm(twoElementsArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void smallArrayShellsortSedgewickTest() {
        int[] emptyArray = {-12, 123, -4, -12, 0, 0, 1, 1634, -4112412};
        int[] expected = {-4112412, -12, -12, -4, 0, 0, 1, 123, 1634};
        int[] actual = shellsortSedgewick.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }


    private Shellsort shellsortKnuth = new Shellsort("knuth");

    @Test
    void nullAsArrayShellsortKnuthTest() {
        int[] emptyArray = null;
        int[] expected = null;
        int[] actual = shellsortKnuth.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void emptyArrayShellsortKnuthTest() {
        int[] emptyArray = new int[0];
        int[] expected = {};
        int[] actual = shellsortKnuth.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void oneElementShellsortKnuthTest() {
        int[] oneElementArray = {1};
        int[] expected = {1};
        int[] actual = shellsortKnuth.runAlgorithm(oneElementArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void twoElementsShellsortKnuthTest() {
        int[] twoElementsArray = {2, 1};
        int[] expected = {1, 2};
        int[] actual = shellsortKnuth.runAlgorithm(twoElementsArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    void smallArrayShellsortKnuthTest() {
        int[] emptyArray = {-12, 123, -4, -12, 0, 0, 1, 1634, -4112412};
        int[] expected = {-4112412, -12, -12, -4, 0, 0, 1, 123, 1634};
        int[] actual = shellsortKnuth.runAlgorithm(emptyArray);
        assertArrayEquals(expected, actual);
    }
}