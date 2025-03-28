package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class RemoveNumberTest {

    private static int[] removeElement(int[] input, int element) {
        int size = input.length;
        int size_new = size;
        for (int i = 0; i < size; i++) {
            if(input[i] == element) {
                size_new--;
            }
        }
        int[] copy = new int[size_new];
        int delta = 0;
        for (int i = 0; i < size; i++) {
            if (input[i] != element) {
                copy[i - delta] = input[i];
            } else {
                delta++;
            }
        }
        return copy;
    }

    @Test
    public void removeElement() {
        Assertions.assertArrayEquals(new int[]{1}, removeElement(new int[]{1, 2}, 2));
        Assertions.assertArrayEquals(new int[]{}, removeElement(new int[]{1}, 1));
    }

    @Test
    public void removeReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 3, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] removed = removeElement(input, 3);

        Assertions.assertArrayEquals(new int[]{9, 1, 11, 45, 499}, removed);
        Assertions.assertArrayEquals(copy, input);
    }
}
