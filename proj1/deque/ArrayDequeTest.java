package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addFirstAndLastTest() {
        // 创建一个 ArrayDeque 对象
        A1<Integer> A2 = new A1<>();

        // 添加元素到前端，并检查大小
        A2.addFirst(1);
        assertEquals("Size should be 1 after adding one element to the front", 1, A2.size());
        assertFalse("A2 should now contain 1 item", A2.isEmpty());

        // 添加元素到末尾，并检查大小
        A2.addLast(2);
        assertEquals("Size should be 2 after adding an element to the back", 2, A2.size());

        // 添加另一个元素到前端
        A2.addFirst(3);
        assertEquals("Size should be 3 after adding another element to the front", 3, A2.size());

        // 打印当前的 ArrayDeque 状态
        System.out.println("Printing out deque after adding to front and back: ");
        A2.printDeque();
    }


    @Test
    public void addIsEmptySizeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        A1<String> A1 = new A1<String>();

        assertTrue("A newly initialized LLDeque should be empty", A1.isEmpty());
        A1.addLast("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, A1.size());
        assertFalse("lld1 should now contain 1 item", A1.isEmpty());

        A1.addLast("middle");
        assertEquals(2, A1.size());

        A1.addLast("back");
        assertEquals(3, A1.size());

        System.out.println("Printing out deque: ");
        A1.printDeque();

    }

}