package kuvaldis.algorithm.structure;

/**
 * To make things easier it's always String to String map.
 */
public class Hashtable {

    private static final int DEFAULT_INITIAL_BUCKETS_SIZE = 4;
    private static final int INCREASE_FACTOR = 2;

    private static final int MINIMUM_CAPACITY = 1 << 4; // 16
    private static final int MAXIMUM_CAPACITY = 1 << 30; // 1 << 31 gives negative value

    private static final float LOAD_FACTOR = 0.9f;

    Node[] buckets = new Node[DEFAULT_INITIAL_BUCKETS_SIZE];

    private int capacity = MINIMUM_CAPACITY;
    private int threshold = (int) (capacity * LOAD_FACTOR);
    private int size = 0;

    class Node {
        private final int hash;
        private final String key;
        private String value;
        Node next;

        private Node(final int hash, final String key, final String value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    public String put(final String key, final String value) {
        final int hash = key.hashCode();
        Node node = buckets[index(hash)];
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
            node = buckets[index(hash)];
            if (node != null) {
                while (node.next != null) {
                    node = node.next;
                }
            }
        }
        final Node newNode = new Node(hash, key, value);
        if (node != null) {
            node.next = newNode;
        } else {
            buckets[index(hash)] = newNode;
        }
        size++;
        return null;
    }

    private void increaseCapacity() {
        final Node[] oldBuckets = buckets;
        final int oldCapacity = capacity;
        final int newCapacity = capacity << 1;
        if (oldCapacity >= MAXIMUM_CAPACITY) {
            // increase threshold but don't touch other
            threshold = Integer.MAX_VALUE;
            return;
        } else {
            threshold = (int) (newCapacity * LOAD_FACTOR);
        }
        buckets = new Node[buckets.length * INCREASE_FACTOR];
        capacity = newCapacity;
        for (int i = 0; i < oldBuckets.length; i++) {
            Node node = oldBuckets[i];
            if (node != null) {
                oldBuckets[i] = null;
                if (node.next == null) {
                    buckets[index(node.hash)] = node;
                } else {
                    Node sameBucketHead = null;
                    Node sameBucketTail = null;
                    Node moveBucketHead = null;
                    Node moveBucketTail = null;
                    while (node != null) {
                        final int newBucketNumber = index(node.hash);
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
        Node node = buckets[index(hash)];
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

    public String remove(final String key) {
        if (key == null) {
            return null;
        }
        final int bucketIndex = index(key.hashCode());
        Node bucket = buckets[bucketIndex];
        String removedValue;
        if (bucket == null) {
            return null;
        } else {
            if (bucket.next == null) {
                if (bucket.key.equals(key)) {
                    removedValue = bucket.value;
                    buckets[bucketIndex] = null;
                } else {
                    return null;
                }
            } else {
                Node prev = null;
                Node node = bucket;
                while (node != null && !node.key.equals(key)) {
                    prev = node;
                    node = node.next;
                }
                // there is no such key
                if (node == null) {
                    return null;
                }
                // some node in the linked list contains the key
                if (prev != null) {
                    prev.next = node.next;
                    node.next = null;
                } else { // first node in the bucket contains key
                    buckets[bucketIndex] = node.next;
                    node.next = null;
                }
                removedValue = node.value;
            }
        }
        size--;
        return removedValue;
    }

    private int index(final int hash) {
        return hash & (buckets.length - 1);
    }
}
