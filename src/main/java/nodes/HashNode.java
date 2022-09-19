package nodes;

public class HashNode<K, V> {
    public int hashcode;
    public K key;
    public V value;
    public HashNode<K,V> next;

    public HashNode(int hashcode, K key, V value, HashNode<K, V> next) {
        this.hashcode = hashcode;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
