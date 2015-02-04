package kuvaldis.algorithm.sort;

public class HeapSort extends Sort {

    @Override
    public int[] sort(int[] input) {
        final int[] heap = makeHeap(input);
        for (int i = 0; i < heap.length; i++) {
            final int lastElementIndex = heap.length - 1 - i;
            swap(heap, lastElementIndex, 0);
            bubbleDown(heap, 0, lastElementIndex);
        }
        return heap;
    }

    private void bubbleDown(final int[] heap, final int index, final int heapLength) {
        int currentIndex = index;
        while (index < heapLength) {
            int newIndex = currentIndex;
            final int leftIndex = leftIndex(currentIndex);
            final int rightIndex = rightIndex(currentIndex);
            if (leftIndex < heapLength && heap[leftIndex] > heap[newIndex]) {
                newIndex = leftIndex;
            }
            if (rightIndex < heapLength && heap[rightIndex] > heap[newIndex]) {
                newIndex = rightIndex;
            }
            if (currentIndex != newIndex) {
                swap(heap, currentIndex, newIndex);
                currentIndex = newIndex;
            } else {
                break;
            }
        }
    }

    private int[] makeHeap(final int[] input) {
        final int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i];
            bubbleUp(result, i);
        }
        return result;
    }

    private void bubbleUp(final int[] heap, final int index) {
        int currentIndex = index;
        int parentIndex = parentIndex(currentIndex);
        while (parentIndex != -1 && heap[parentIndex] < heap[currentIndex]) {
            swap(heap, parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex(currentIndex);
        }
    }

    private int parentIndex(final int index) {
        if (index == 0) return -1;
        return (index - 1) / 2;
    }

    private int leftIndex(final int index) {
        return index * 2 + 1;
    }

    private int rightIndex(final int index) {
        return index * 2 + 2;
    }
}
