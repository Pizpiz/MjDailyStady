package dequeTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {
	    public static void main(String args[]) {
	        Deque<Integer> q = new ArrayDeque<>();
//	        Deque<Integer> q = new LinkedList<>();
	        long begin = System.nanoTime();
	        test(q);
	        long end = System.nanoTime();
	        System.out.println("took " + (end - begin) + "ns");
	    }

	    public static void test(Deque<Integer> q) {
	        for (int i = 0; i < 100; i++) {
	            for (int j = 0; j < 10_000; j++) {
	                q.addLast(j);
	            }

	            for (int j = 0; j < 10_000; j++) {
	                q.removeFirst();
	            }
	        }
	    }
	    
}
