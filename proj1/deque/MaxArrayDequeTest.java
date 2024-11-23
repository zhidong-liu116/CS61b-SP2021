package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {

    /* 整数比较器 */
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    /* 字符串长度比较器 */
    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void testIntegerMax() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());

        // 测试空deque
        assertNull("Empty deque should return null", mad.max());

        // 添加元素并测试
        mad.addLast(5);
        assertEquals(5, (int)mad.max());

        mad.addLast(10);
        mad.addLast(3);
        assertEquals(10, (int)mad.max());

        mad.addFirst(15);
        assertEquals(15, (int)mad.max());
    }

    @Test
    public void testStringMax() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringLengthComparator());

        mad.addLast("a");
        mad.addLast("abc");
        mad.addLast("ab");

        // 使用默认比较器（字符串长度）
        assertEquals("abc", mad.max());

        // 使用不同的比较器（字母顺序）
        Comparator<String> alphabeticalOrder = String::compareTo;
        assertEquals("abc", mad.max(alphabeticalOrder));
    }

    @Test
    public void testDifferentComparators() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());

        mad.addLast(1);
        mad.addLast(-5);
        mad.addLast(10);

        // 使用构造函数中的比较器
        assertEquals(10, (int)mad.max());

        // 使用新的比较器（找最小值）
        Comparator<Integer> reverseOrder = (a, b) -> b - a;
        assertEquals(-5, (int)mad.max(reverseOrder));
    }
}
