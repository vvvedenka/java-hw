package edu.phystech.hw1;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SortTest {

    private static void Swap(int[] copy, int i, int j) {
        int tmp = copy[i];
        copy[i] = copy[j];
        copy[j] = tmp;
    }
    private static int PartitionLomuto(int[] copy, int low, int high) {
        int pivot = copy[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (copy[j] <= pivot) {
                Swap(copy, i, j);
                i++;
            }
        }
        Swap(copy, i, high);
        return i;
    }
    private static void QuickSortImpl(int[] copy, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot_idx = PartitionLomuto(copy, low, high);
        QuickSortImpl(copy, low, pivot_idx - 1);
        QuickSortImpl(copy, pivot_idx + 1, high);
    }
    private static int[] sort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        int size = nums.length;
        QuickSortImpl(copy, 0, size - 1);
        return copy;
    }

    @Test
    public void sortWorks() {
        Assertions.assertArrayEquals(new int[]{1}, sort(new int[]{1}));
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sort(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void sortReturnsNewArray() {
        int[] input = {9, 1, 3, 11, 45, 499};
        int[] copy = Arrays.copyOf(input, input.length);

        int[] sorted = sort(input);

        Assertions.assertArrayEquals(new int[]{1, 3, 9, 11, 45, 499}, sorted);
        Assertions.assertArrayEquals(copy, input);
    }
}
