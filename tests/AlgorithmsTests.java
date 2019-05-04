import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTests {

    Quicksort quicksort = new Quicksort();
    Shellsort shellsort = new Shellsort();

    @Test
    void nullAsArrayTest(){
        int[] emptyArray = null;
        int[] expected = null;
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected,actual);
    }

    @Test
    void emptyArrayTest() {
        int[] emptyArray = new int[0];
        int[] expected = {};
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected,actual);
    }

    @Test
    void smallArrayTest() {
        int[] emptyArray = {-12,123,-4,-12,0,0,1,1634,-4112412};
        int[] expected = {-4112412,-12,-12,-4,0,0,1,123,1634};
        int[] actual = quicksort.runAlgorithm(emptyArray);
        assertArrayEquals(expected,actual);
    }

    @Test
    void smallArrayTestShellsort(){
        int[] emptyArray = {-12,123,-4,-12,0,0,1,1634,-4112412};
        int[] expected = {-4112412,-12,-12,-4,0,0,1,123,1634};
        int[] actual = shellsort.runAlgorithm(emptyArray);
        assertArrayEquals(expected,actual);
    }
}