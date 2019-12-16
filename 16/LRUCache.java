import java.util.HashMap;

// int to int LRUCache
// designed to pass https://leetcode.com/problems/lru-cache
// all operations take constant time
// O(n) space in capacity of the cache
public class LRUCache {
    private HashMap<Integer, LinkedListNode> nodes = new HashMap<>();    // key to node mappings
    private LinkedListNode dummyHead = new LinkedListNode(0, 0);
    private LinkedListNode dummyTail = new LinkedListNode(0, 0);
    private int maxSize;
    
    public LRUCache(int capacity) {
        maxSize = capacity;
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    
    // returns the value associated with key
    public int get(int key) {
        if (!nodes.containsKey(key))
            return -1;
        LinkedListNode node = nodes.get(key);
        removeNode(node);
        makeHead(node);
        return node.value;
    }
    
    // inserts key value pair to the cache
    public void put(int key, int value) {
        if (nodes.containsKey(key)) 
            removeKey(key);
        if (nodes.size() == maxSize)   // remove tail.key from cache
            removeKey(getTail().key);
        LinkedListNode node = new LinkedListNode(key, value);
        makeHead(node);
        nodes.put(key, node);
    }

    // removes key and its associated value from cache
    private void removeKey(int key) {
        LinkedListNode node = nodes.get(key);
        removeNode(node);
        nodes.remove(key);
    }
    
    // makes head the first element in the internal linked list
    private void makeHead(LinkedListNode head) {
        if (head == null)
            return;
        LinkedListNode node = dummyHead.next;  // the node next to head
        dummyHead.next = head;
        head.next = node;
        node.prev = head;
        head.prev = dummyHead;
    }

    // returns the last element in the internal linked list,
    // i.e. the least recently used element
    private LinkedListNode getTail() {
        if (dummyTail.prev == null || dummyTail.prev == dummyHead)
            return null;
        return dummyTail.prev;
    }

    // removes node from the internal linked list
    private void removeNode(LinkedListNode node) {
        if (node == null || node == dummyHead || node == dummyTail)
            return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    
    private class LinkedListNode {
        int key;
        int value;
        private LinkedListNode next = null;
        private LinkedListNode prev = null;

        public LinkedListNode(int key, int val) {
            this.key = key;
            value = val;
        }
    }
}