import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last = null;
    private int size = 0;

    // construct an empty deque
    public Deque(){
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        boolean isEmpty = deque.isEmpty();

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);

        for (Integer it : deque) {
            StdOut.print(it);
        }
        StdOut.println();

        Integer size = deque.size();
        isEmpty = deque.isEmpty();

        Integer first = deque.removeFirst();
        Integer last = deque.removeLast();

        for (Integer it : deque) {
            StdOut.print(it);
        }
        StdOut.println();
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("item");

        Node prevFirst = first;
        first = new Node();
        first.item = item;
        first.next = prevFirst;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        }
        else {
            prevFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null)
            throw new IllegalArgumentException("item");

        Node prevLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = prevLast;

        if (isEmpty()) {
            first = last;
        }
        else {
            prevLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty())
            throw new NoSuchElementException("deque is empty!");

        Node nextFirst = first.next;
        Item item = first.item;
        first = nextFirst;
        if (first != null) {
            first.prev = null;
        }
        size--;
        if (isEmpty())
            first = last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty())
            throw new NoSuchElementException("deque is empty!");

        Node prevLast = last.prev;
        Item item = last.item;
        last = prevLast;
        if (last != null) {
            last.next = null;
        }
        size--;
        if (isEmpty())
            first = last = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException("No such operation!");
        }
        public Item next() {
            if (current == null)
                throw new NoSuchElementException("end!");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
