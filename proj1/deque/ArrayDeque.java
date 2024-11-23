package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int front;      // front pointer
    private int back;       // back pointer
    final int resizingLength = 16;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        back = 0;
        size = 0;
    }

    /** Inserts X into the back of the list. */
    @Override
    public void addLast(T x) {
        if (isEmpty()) {
            items[back] = x;
        } else {
            if (size == items.length) {
                resize(size * 2);
            }
            back = (back + 1 + items.length) % items.length;
            items[back] = x;
        }
        size += 1;
    }

    /** Inserts X into the front of the list. */
    // [0,0,0] -> [1,0,0]
    // f/b         b   f
    // [1,0,0] -> [1,0,2]
    // b    f      b f

    //    public void addFirst(T x){
    //        if(isEmpty()) {
    //            // initialize f/b pointer
    //            items[front] = x;
    //            size += 1;
    //        } else
    //            if(front == 0 && size != items.length) {
    //            front = (front - 1 + items.length) % items.length;
    //            items[front] = x;
    //            size += 1;
    //
    //        } else if(size == items.length){
    //            resize(size * 2);
    ////            front = 0; // after resizing, initialize the front pointer to 0;
    //            front = (front - 1 + items.length) % items.length;
    //            items[front] = x;
    //            size += 1;
    //        } else {
    //            front -= 1;
    //            items[front] = x;
    //            size += 1;
    //        }
    //    }

    @Override
    public void addFirst(T x) {
        if (isEmpty()) {
            items[front] = x;
        } else {
            if (size == items.length) {
                resize(size * 2);
            }
            front = ((front - 1) + items.length) % items.length;
            items[front] = x;
        }
        size += 1;
    }



    /** Deletes item from front of the list and
     * returns deleted item.  You can somehow resize array to have
     * less space consumption. But remember, resizing array to decrease its size
     * will also cause O(n) time complexity!! */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            // two approaches are the same mathematical logic
            // "(size - 1) / items.length < 0.25" is equivalent to "size < items.length / 4"
            //            if (items.length >= 16 && (double) (size - 1) / items.length < 0.25)
            //                resize(items.length / 2);
            //            }
            // this approach is recommended use.
            if (size < items.length / 4 && items.length >= resizingLength) {
                resize(items.length / 2);
            }
            T removedItem = items[front];
            items[front] = null;
            front = (front + 1 + items.length) % items.length;
            size--;
            return removedItem;
        }
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            if (size < items.length / 4 && items.length > 8) {
                resize(items.length / 2);
            }

            if (size == 1) {
                T removedItem = items[back];
                items[back] = null;
                size--;
                return removedItem;
            }

            T removedItem = items[back];
            items[back] = null;
            back = (back - 1 + items.length) % items.length;
            size--;
            return removedItem;
        }
    }

    /** Returns the item from the back of the list. */
    private T getLast() {
        return items[back];
    }

    /** Gets the ith item in the list (0 is the front). */
    @Override
    public T get(int i) {
        //        if (i < 0 || i >= size) {
        //            return null;
        //        }else {
        //            return items[(front + i + items.length) % items.length];
        //        }
        if (isEmpty() || i < 0 || i >= size) {  // 保留isEmpty()检查
            return null;
        }
        return items[(front + i + items.length) % items.length];
    }

    /**
     * Returns the number of items in the list.
     */
    @Override
    public int size() {
        return size;
    }


    /** Return an iterator of a LinkedListDeque */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos; // pos which iterator start
        private int count; // counter for the iterator

        /** Constructor */
        public ArrayDequeIterator() {
            pos = front;
            count = 0;
        }

        /** Check if there are remaining elements that haven't been visited
         * When count is not equal to size, it means there are still remaining */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T returnedItem = null;
            if (hasNext()) {
                returnedItem = items[pos];
                pos = (pos + 1 + items.length) % items.length;
                count += 1;
                return returnedItem;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        // step 1: make sure object o is the type ArrayDeque
        // if o is null or not LinkedListDeque type, return false;
        if (!(o instanceof Deque)) {
            return false;
        }

        // Step2, check the size
        if (this.size() != ((ArrayDeque<?>) o).size()) {
            return false;
        }

        // Step3 compare elements for each deque
        // reminder: we can use iterator we just created
        // make iterator for each object
        // one for "this" obj, and the other one for object o
        Iterator<T> thisObj = this.iterator();
        Iterator<T> objArrayDeque = ((ArrayDeque<T>) o).iterator();

        while (thisObj.hasNext()) {
            T thisobjItems = thisObj.next();
            Object objArrayDequeItem = objArrayDeque.next();
            // remember, do not compare each iterator, compare each element, e.g, this_obj.next();
            if (thisobjItems == null) {
                if (objArrayDequeItem != null) {
                    return false;
                }
            } else if (!thisobjItems.equals(objArrayDequeItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(front + i) % items.length] + " ");
        }
        System.out.println();
    }

    /** The method used to resizing the arrayDeque */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        // copy element from the start of the front pointer
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(front + i) % items.length];
        }
        // update array and f/b pointer
        items = newItems;
        front = 0;                // front -> start of the new array
        back = size - 1;          // back point to the last position that has valued element - (指向最后一个有效数据的位置)
    }

    private static void main(String[] args) {
        ArrayDeque<Object> A1 = new ArrayDeque<>();
        //A1.addFirst(1);
        //        A1.addFirst(1);
        //        A1.removeLast();
        //        A1.addFirst(3);
        //        A1.addFirst(4);
        //        A1.addFirst(5);
        //        A1.addFirst(6);
        //        System.out.println(A1.removeLast());

        //        ArrayDeque<Object> A2 = new ArrayDeque<>();
        //        A2.addFirst(1);
        //        A2.addFirst(2);
        //        A2.addFirst(3);
        //        A2.addFirst(3);



        //        A1.printDeque();
        //        System.out.println(A1.get(6));
        //        A1.printDeque();
        //        System.out.println("Below is size arrayDeque");
        //        System.out.println(A1.size());

        //        System.out.println(A1.equals(A2));
    }
}
