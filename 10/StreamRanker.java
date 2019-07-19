import java.util.List;
import java.util.ArrayList;

public class StreamRanker {
    Node root;

    void track(int x) {
        if (root == null) root = new Node(x);
        else root.insert(x);
    }

    int getRankOfNumber(int number) {
        return root.getRank(number);
    }

    static class Node {
        Node left, right;
        int leftSize;   // size of left subtree
        int number;

        Node(int n) {number = n;}

        void insert(int x) {
            if (x <= number) {
                this.leftSize++;
                if (left == null) left = new Node(x);
                else left.insert(x);
            } else {
                if (right == null) right = new Node(x);
                else right.insert(x);
            }
        }

        int getRank(int x) {
            if (x < number) {   // x in left subtree
                return left != null? left.getRank(x) : -1;
            } else if (x == number) {
                return this.leftSize;
            } else {            // x in right subtree
                int rightRank = right != null? right.getRank(x) : -1;
                return rightRank == -1? -1 : leftSize + 1 + rightRank;
            }
        }
    }

    public static void main(String[] args) {
        StreamRanker stream = new StreamRanker();
        List<Integer> numbers = new ArrayList<>();
        for (String s : args) {
            int i = Integer.parseInt(s);
            numbers.add(i);
            stream.track(i);
        }
        for (int i : numbers) {
            System.out.format("getRankOfNumber(%d) = %d%n", i, stream.getRankOfNumber(i));
        }
    }
}