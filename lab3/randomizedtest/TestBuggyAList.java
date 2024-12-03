package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniformInt(0, 4);

            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniformInt(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correctSize = correct.size();
                int brokenSize = broken.size();
                assertEquals(correctSize, brokenSize);
                //System.out.println("size: " + correctSize);
            } else if (operationNumber == 2 && correct.size() > 0) {
                // getLast
                int correctLast = correct.getLast();
                int brokenLast = broken.getLast();
                assertEquals(correctLast, brokenLast);
                //System.out.println("getLast: " + correctLast);
            } else if (operationNumber == 3 && correct.size() > 0) {
                // removeLast
                int correctRemoved = correct.removeLast();
                int brokenRemoved = broken.removeLast();
                assertEquals(correctRemoved, brokenRemoved);
                //System.out.println("removeLast: " + correctRemoved);
            }
        }
    }
}
