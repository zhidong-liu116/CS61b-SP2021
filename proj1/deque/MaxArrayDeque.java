package deque;

//import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super(); // invoke super class constructor，initialize ArrayDeque
        this.comparator = c;
    }


    /** Return the maximum element in the deque. */
    public T max() {
        if (isEmpty()) {
            return null;
        }

        Iterator<T> iterator = iterator();
        T maxElement = iterator.next();
        while (iterator.hasNext()) {
            T current = iterator.next();

            if (comparator.compare(current, maxElement) > 0) {
                maxElement = current;
            }
        }
        return maxElement;
    }

    public T max(Comparator<T> c){
        if(isEmpty()) {
            return null;
        }

        Iterator<T> iterator = iterator();
        T max_element = iterator.next(); // representing the first element of this arraydeque
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (c.compare(current, max_element) > 0) {
                max_element = current;
            }
        }
        return max_element;
    }
}