import java.util.Deque;
import java.util.ArrayDeque;

public class TowerOfHanoi {
    static void moveDisksToLastTower(Deque<Disk> first, Deque<Disk> mid, Deque<Disk> last) {
        moveDisks(first.size(), first, last, mid);
    }

    // Moves n disks from origin to target using temp as temporary holding tower in O(2^n) time
    static void moveDisks(int n, Deque<Disk> origin, Deque<Disk> target, Deque<Disk> temp) {
        if (n <= 0) return;
        if (n == 1) target.push(origin.pop());
        else {
            moveDisks(n-1, origin, temp, target);
            moveDisks(1, origin, target, temp);
            moveDisks(n-1, temp, target, origin);
        }
    }

    public static void main(String[] args) {
        int nDisks = Integer.parseInt(args[0]);
        Deque<Disk> first = new ArrayDeque<>(nDisks),
                      mid = new ArrayDeque<>(nDisks),
                     last = new ArrayDeque<>(nDisks);
        while (nDisks-- > 0) first.push(new Disk(nDisks));
        System.out.println(first);
        System.out.println(mid);
        System.out.println(last);
        System.out.println("");
        moveDisksToLastTower(first, mid, last);
        System.out.println(first);
        System.out.println(mid);
        System.out.println(last);
    }

    static class Disk {
        int size;
        
        public Disk(int size) {
            this.size = size;
        }
        
        @Override 
        public String toString() {
            return String.valueOf(size);
        }
    }
}