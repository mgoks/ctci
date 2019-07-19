import java.util.Deque;
import java.util.ArrayDeque;
import java.util.concurrent.ThreadLocalRandom;

public class SortStack {
    
    public static void sort(Deque<Integer> stack) {
        Deque<Integer> T = new ArrayDeque<Integer>(), S = stack;    // temporary stack, input stack
        while (!S.isEmpty()) {
            int i = S.pop();
            while (!T.isEmpty() && i < T.peek()) S.push(T.pop());
            T.push(i);
        }
        while (!T.isEmpty()) S.push(T.pop());
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) 
            stack.push(ThreadLocalRandom.current().nextInt(20));
        System.out.println("ORIGINAL: " + stack);
        sort(stack);
        System.out.println("SORTED  : " + stack);
    }
    
}