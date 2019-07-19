import java.util.Set;
import java.util.HashSet;

public class RemoveDuplicates {

	static class Node {
		Object data;
		Node   next;
		Node (Object data) {
			this.data = data;
			this.next = null;
		}
	}
	
	static void removeDups(Node head) {

		Set<Object> dataSet = new HashSet<>();
		dataSet.add(head.data);
		Node n = head;
		while (n.next != null) {
			if (dataSet.contains(n.next.data)) 
				n.next = n.next.next;	// deleta next node
			else {
				dataSet.add(n.next.data);
				n = n.next;
			}
		}

		// without using temporary buffer
		// for (Node i = head; i != null; i = i.next)
		// 	for (Node j = i; j != null && j.next != null; ) {
		// 		if (i.data.equals(j.next.data)) j.next = j.next.next;
		// 		else j = j.next;
		// 	}
	}

	public static void main(String[] args) {
		Node dummyHead = new Node(null);
		Node n = dummyHead;
		for (char c : args[0].toCharArray()) {
			n.next = new Node(c);
			n = n.next;
		}
		Node head = dummyHead.next;
		removeDups(head);
		while (head != null) {
			System.out.print(head.data);
			head = head.next;
		}
	}
}