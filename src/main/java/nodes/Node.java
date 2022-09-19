package nodes;

public class Node<T> {
    public T object;
    public Node<T> previous;
    public Node<T> next;

    public Node(T object, Node<T> previous, Node<T> next) {
        this.object = object;
        this.previous = previous;
        this.next = next;
    }
}
