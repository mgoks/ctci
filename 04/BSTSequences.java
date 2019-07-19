import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class BSTSequences {

    static void printArrays(TreeNode root) {
        for (LinkedList<Integer> array : getAllSeq(root)) 
            System.out.println(array);
    }

    static List<LinkedList<Integer>> getAllSeq(TreeNode n) {
        ArrayList<LinkedList<Integer>> allSeq = new ArrayList<>();
        if (n == null) 
            allSeq.add(new LinkedList<Integer>());
        else {
            LinkedList<Integer> prefix = new LinkedList<>();
            prefix.add(n.val);
            List<LinkedList<Integer>> leftSeq  = getAllSeq(n.left),
                                      rightSeq = getAllSeq(n.right);
            for (LinkedList<Integer> left : leftSeq) 
                for (LinkedList<Integer> right : rightSeq) 
                    weave(left, right, allSeq, prefix);
        }
        return allSeq;
    }

    static void weave(LinkedList<Integer> a, 
                      LinkedList<Integer> b, 
                      List<LinkedList<Integer>> weavedLists, 
                      LinkedList<Integer> prefix) {
        if (a.isEmpty() || b.isEmpty()) {
            LinkedList<Integer> newList = new LinkedList<>(prefix);
            newList.addAll(a);
            newList.addAll(b);
            weavedLists.add(newList);
        } else {
            prefix.add(a.removeFirst());
            weave(a, b, weavedLists, prefix);
            a.addFirst(prefix.removeLast());
            // same for b
            prefix.add(b.removeFirst());
            weave(a, b, weavedLists, prefix);
            b.addFirst(prefix.removeLast());
        }
    }

    static class TreeNode {
        TreeNode left, right;
        int val;
        public TreeNode(int val) {this.val = val;}
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(20);
        root.right = new TreeNode(60);
        root.left.left = new TreeNode(10);
        printArrays(root);
    }

}