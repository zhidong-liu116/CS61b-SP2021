package deque;

import java.util.ArrayDeque;

public class deque_prac<T> {

    public void main(String[] args) {

        // Create a Deque of Strings
        ArrayDeque<Integer> test_deque = new ArrayDeque<Integer>();

        // here, we can write all the operations in deque
        test_deque.addFirst(1);
        test_deque.addFirst(2);
        test_deque.addFirst(3);
        test_deque.addFirst(4);
        test_deque.addFirst(5);
        test_deque.addFirst(6);
        test_deque.addFirst(7);

        // Displaying the Deque
        System.out.println(STR."Deque elements: \{test_deque} \{test_deque.size()}");
    }
}