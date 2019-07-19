import java.util.concurrent.ThreadLocalRandom;

public class RandomBSTNode {
    
    RandomBSTNode left, right;
    int           val , size;
    
    public RandomBSTNode(int val) {
        this.val = val;
        size = 1;
    }

    public void insert(int i) {
        // This is not necessarily a set, so it accepts duplicates.
        size++;
        if (i < val) {
            if (left == null) left = new RandomBSTNode(i);
            else left.insert(i);
        } else {
            if (right == null) right = new RandomBSTNode(i);
            else right.insert(i);
        }
    }

    public RandomBSTNode getRandomNode() {
        // calling randomInt only once because it is expensive
        return getNode(randomInt(size));
    }

    private RandomBSTNode getNode(int i) {
        int leftSize = left == null? 0 : left.size;
        if (i < leftSize) return left.getNode(i);
        if (i == leftSize) return this;
        return right.getNode(i - leftSize - 1);
    }

    private int randomInt(int upperBound) {
        return ThreadLocalRandom.current().nextInt(upperBound);
    }

    public static void main(String[] args) {
        RandomBSTNode root = new RandomBSTNode(0);
        int size = Integer.parseInt(args[0]);
        for (int i = 1; i < size; i++)
            root.insert(ThreadLocalRandom.current().nextInt(-100, 100));
        for (int i = 0; i < size; i++)
            System.out.print(root.getRandomNode().val + ", ");
        System.out.println("");
    }

}