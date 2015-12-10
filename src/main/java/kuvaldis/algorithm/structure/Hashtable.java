package kuvaldis.algorithm.structure;

/**
 * To make things easier it's always String to String map.
 */
public class Hashtable {

    private static final int DEFAULT_INITIAL_BUCKETS_SIZE = 8;
    private static final int INCREASE_FACTOR = 2;

    private static final int MINIMUM_CAPACITY = 1 << 4; // 16
    private static final int MAXIMUM_CAPACITY = 1 << 30; // 1 << 31 gives negative value

    private static final float LOAD_FACTOR = 0.5f;

    private Node[] buckets = new Node[DEFAULT_INITIAL_BUCKETS_SIZE];

    private int capacity = MINIMUM_CAPACITY;
    private int threshold = (int) (capacity * LOAD_FACTOR);
    private int size = 0;

    private class Node {
        private final int hash;
        private final String key;
        private String value;
        private Node next;

        private Node(final int hash, final String key, final String value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    public String put(final String key, final String value) {
        final int hash = key.hashCode();
        Node node = buckets[hash % buckets.length];
        if (node != null) {
            Node currentNode = node;
            while (true) {
                if (currentNode.key.equals(key)) {
                    final String oldValue = currentNode.value;
                    currentNode.value = value;
                    return oldValue;
                }
                if (currentNode.next == null) {
                    break;
                }
                currentNode = currentNode.next;
            }
            node = currentNode;
        }
        if (size + 1 > threshold) {
            increaseCapacity();
            node = buckets[hash % buckets.length];
            while (node.next != null) {
                node = node.next;
            }
        }
        final Node newNode = new Node(hash, key, value);
        if (node != null) {
            node.next = newNode;
        } else {
            buckets[hash % buckets.length] = newNode;
        }
        size++;
        return null;
    }

    private void increaseCapacity() {
        final Node[] oldBuckets = buckets;
        final int oldCapacity = capacity;
        capacity = capacity * INCREASE_FACTOR;
        if (oldCapacity >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
        } else {
            threshold = (int) (capacity * LOAD_FACTOR);
        }
        buckets = new Node[buckets.length * INCREASE_FACTOR];
        for (int i = 0; i < oldBuckets.length; i++) {
            Node node = oldBuckets[i];
            if (node != null) {
                oldBuckets[i] = null;
                if (node.next == null) {
                    buckets[node.hash % buckets.length] = node;
                } else {
                    Node sameBucketHead = null;
                    Node sameBucketTail = null;
                    Node moveBucketHead = null;
                    Node moveBucketTail = null;
                    while (node != null) {
                        final int newBucketNumber = node.hash % buckets.length;
                        // should be in the same bucket
                        if (newBucketNumber == i) {
                            if (sameBucketHead == null) {
                                sameBucketHead = node;
                                sameBucketTail = node;
                                buckets[newBucketNumber] = sameBucketHead;
                            } else {
                                sameBucketTail.next = node;
                                sameBucketTail = node;
                            }
                        } else { // move to a new bucket
                            if (moveBucketHead == null) {
                                moveBucketHead = node;
                                moveBucketTail = node;
                                buckets[newBucketNumber] = moveBucketHead;
                            } else {
                                moveBucketTail.next = node;
                                moveBucketTail = node;
                            }
                        }
                        final Node next = node.next;
                        node.next = null;
                        node = next;
                    }
                }
            }
        }
    }

    public String get(final String key) {
        if (key == null) {
            return null;
        }
        final int hash = key.hashCode();
        Node node = buckets[hash % buckets.length];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
