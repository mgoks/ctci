import java.util.HashMap;

// int to int LRUCache
// designed to pass https://leetcode.com/problems/lru-cache
// all operations take constant time
// O(n) space in capacity of the cache
class LRUCache {
    HashMap<Integer, LinkedListNode> nodes = new HashMap<>();    // key to node mappings
    LinkedListNode dummyHead = new LinkedListNode(0, 0);
    LinkedListNode dummyTail = new LinkedListNode(0, 0);
    int maxSize;
    
    public LRUCache(int capacity) {
        maxSize = capacity;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    public int get(int key) {
        if (!nodes.containsKey(key))
            return -1;
        LinkedListNode node = nodes.get(key);
        removeNode(node);
        makeHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (nodes.containsKey(key)) {
            LinkedListNode node = nodes.get(key);
            node.value = value; // value might not equal node.value
            removeNode(node);
            makeHead(node);
            return;
        }
        if (nodes.size() == maxSize) {
            LinkedListNode tail = getTail();
            nodes.remove(tail.key);
            removeNode(tail);
        }
        LinkedListNode node = new LinkedListNode(key, value);
        makeHead(node);
        nodes.put(key, node);
    }
    
    private void makeHead(LinkedListNode head) {
        if (head == null)
            return;
        LinkedListNode node = dummyHead.next;  // the node next to head
        dummyHead.next = head;
        head.next = node;
        node.prev = head;
        head.prev = dummyHead;
    }

    // the node least recently used key
    private LinkedListNode getTail() {
        if (dummyTail.prev == null || dummyTail.prev == dummyHead)
            return null;
        return dummyTail.prev;
    }

    private void removeNode(LinkedListNode node) {
        if (node == null || node == dummyHead || node == dummyTail)
            return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    
    class LinkedListNode {
        int key;
        int value;
        LinkedListNode next = null;
        LinkedListNode prev = null;

        public LinkedListNode(int key, int val) {
            this.key = key;
            value = val;
        }
    }
}

/**
 * The LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */