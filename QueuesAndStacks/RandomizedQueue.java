import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int index = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 5; ++i) {
            queue.enqueue(i);
        }

        for (Integer it : queue) {
            StdOut.print(it);
        }
        StdOut.println();

        StdOut.println(queue.sample());

        while (!queue.isEmpty()) {
            StdOut.print(queue.dequeue());
        }
        StdOut.println();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return index == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return index;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < index; i++)
            copy[i] = items[i];
        items = copy;
    }

    private int randomIndex() {
        return StdRandom.uniform(0, index);
    }

    private void autoShrink() {
        if (index > 0 && index == items.length / 4) resize(items.length / 2);
    }

    private void remove(int pos) {
        items[pos] = items[index - 1];
        items[index - 1] = null;
        index--;
        autoShrink();
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item");

        if (index == items.length) resize(2 * items.length);
        items[index++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("deque is empty!");

        int rnd = randomIndex();
        Item item = items[rnd];
        remove(rnd);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("deque is empty!");

        return items[randomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;
        private Item[] iteratorItems;

        public RandomizedQueueIterator() {
            iteratorItems = (Item[]) new Object[index];
            for (int i = 0; i < index; ++i) {
                iteratorItems[i] = items[i];
            }
            StdRandom.shuffle(iteratorItems);
        }

        public boolean hasNext() {
            return current != index;
        }

        public void remove() {
            throw new UnsupportedOperationException("No such operation!");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("end!");
            return iteratorItems[current++];
        }
    }
}
