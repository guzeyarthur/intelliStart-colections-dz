package mymap;

import nodes.HashNode;

public class MyHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private int capacity;
    private int size;
    private int arrayFullness;
    private HashNode<K,V>[] table;

    public MyHashMap() {
        size = 0;
        arrayFullness = 0;
        capacity = DEFAULT_INITIAL_CAPACITY;
        table = new HashNode[DEFAULT_INITIAL_CAPACITY];
    }

    public boolean put(K key, V value) {
        if (!uniqueKey(key)) {
            return false;
        }
        table = resizeTable();
        int indexOfTable = findBucket(hash(key));
        if (table[indexOfTable] == null) {
            arrayFullness++;
            table[indexOfTable] = newNode(key, value);
        } else {
            HashNode<K, V> node = table[indexOfTable];
            while (true) {
                if (node.next == null) {
                    node.next = newNode(key, value);
                    break;
                } else {
                    node = node.next;
                }
            }
        }
        size++;
        return true;
    }

    public V remove(K key) {//
        V deleted = null;
        if (uniqueKey(key)) {
            return null;
        }
        int indexOfTable = findBucket(hash(key));
        HashNode<K, V> node = table[indexOfTable];
        if (node.key.equals(key)) {
            size--;
            deleted = node.value;
            table[indexOfTable] = node.next;
        } else {
            while (true) {
                if (node.next.key.equals(key)) {
                    size--;
                    deleted = node.next.value;
                    node.next = node.next.next;
                    break;
                }
                node = node.next;
            }
        }
        return deleted;
    }

    public void clear() {
        size=0;
        arrayFullness=0;
        table=null;
    }

    public int size(){
        return size;
    }

    public V get(K key) {
        if (uniqueKey(key)) {
            return null;
        }
        int indexOfTable = findBucket(hash(key));
        HashNode<K, V> node = table[indexOfTable];
        while (true) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
    }

    private HashNode<K,V> newNode(K key, V value) {
        return new HashNode<K, V>(hash(key), key, value, null);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode();
    }

    private int findBucket(int hash) {
        return hash & (capacity-1);
    }

    private boolean uniqueKey(K key) {
        int indexOfTable = findBucket(hash(key));
        if (table[indexOfTable] == null) {
            return true;
        } else {
            HashNode<K, V> node = table[indexOfTable];
            while (true) {
                if (node.key.equals(key)) {
                    return false;
                }
                if (node.next == null) {
                    break;
                } else {
                    node = node.next;
                }
            }
        }
        return true;
    }

    private HashNode<K,V>[] resizeTable() {
        if ((double)arrayFullness/(double)capacity >= 0.75) {
            capacity = (capacity*3)/2+1;
            HashNode<K,V>[] newTable = new HashNode[capacity];
            HashNode<K,V>[] buffer = table;
            table = newTable;
            for (int i=0; i< buffer.length; i++) {
                if (buffer[i]!=null) {
                    HashNode<K,V> node = buffer[i];
                    while (true) {
                        if (node != null) {
                            put(node.key, node.value);
                            size--;
                        } else {
                            break;
                        }
                        node = node.next;
                    }
                }
            }
            return newTable;
        }
        return table;
    }
}
