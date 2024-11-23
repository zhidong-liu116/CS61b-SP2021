package deque;
import java.util.Iterator;

/** deque implementation ==> ### Linked List based ### */

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    /* Need two Nodes to help create a deque **/
    private final Node sentinel;
    private int size;


    /** Creates an empty deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        // this make sure, new node is appearing between sentinel
        // node and the oldlast Node(if there is one)
        Node p = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = p; // changing the pointer of sentinel node to new node
        sentinel.next = p;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node p = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }


    /** Return the size of the linked-list deque */
    @Override
    public int size() {
        return size;
    }


    /** Print method that used to print the elements of this Linked-List deque */
    @Override
    public void printDeque() {
        System.out.println("Below is the elements in this linked-list-deque");
        if (isEmpty()) {
            System.out.println("This Linked-list-deque is empty!");
            return;
        }
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            p = p.next;
            if (p != sentinel) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removedItem;
    }

    @Override
    public T get(int index) {
        int counter = 0;
        Node p = sentinel.next;
        if (index < 0 || index >= size) {
            return null;
        }
        while (p != sentinel) {
            if (counter == index) {
                return p.item;
            }
            counter += 1;
            p = p.next;
        }
        return null;
    }


    /** This helper method help you traverse the deque which the public method didn't have
     * Reminder: Private method should only focus on how recursion works here, boundary check should
     * be done by public method! */

    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    /** The get method that returns the element in deque
     * Use helper method above solve the issue, traverse the deque
     * */
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

    /** Return an iterator of a LinkedListDeque */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node current = sentinel;

        /** Constructor for LinkedListDequeIterator */
        private LinkedListDequeIterator() {
            // iterator begins from the first valid node, which is the node after sentinel node.
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel && current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T currentItem = current.item;
            current = current.next;
            return currentItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        // step 1: make sure object o is the type LinkedListDeque
        // if o is null or not LinkedListDeque type, return false;
        if (!(o instanceof Deque)) {
            return false;
        }

        // Step2, check the size
        if (this.size() != ((Deque<T>) o).size()) {
            return false;
        }

        // Step3 compare elements for each deque
        // reminder: we can use iterator we just created
        // make iterator for each object
        // one for "this" obj, and the other one for object o
        Iterator<T> thisIterator = this.iterator();
        Iterator<?> objectIterator;

        // return exact iterator based on its type
        if (o instanceof ArrayDeque) {
            objectIterator = ((ArrayDeque<?>) o).iterator();
        } else if (o instanceof LinkedListDeque) {
            objectIterator = ((LinkedListDeque<?>) o).iterator();
        } else {
            return false;
        }

        while (thisIterator.hasNext()) {
            T thisItem = thisIterator.next();
            Object objectItem = objectIterator.next();
            // this approach is as same as the one below, this clarifies better logic.
            // this also make sure that "null.equals" happen
            // bc null value cannot call equals, it will throw NullPointer Exceptions.s
            if (thisItem == null) {
                if (objectItem != null) {
                    return false;
                }
            } else if (!thisItem.equals(objectItem)) {
                return false;
            }
            /** simplified version to compare two objects(recommend in futures) */
            //            if (!Objects.equals(this_iterator.next(), object_iterator.next())) {
            //                return false;
            //            }
        }
        return true;
    }

    private static void main(String[] args) {

    }
}
