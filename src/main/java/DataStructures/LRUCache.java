package DataStructures;

import java.util.HashMap;

class LRUCache {
    HashMap<Integer, LRUNode> map = null;
    int cap;
    LRUNode head = null;
    LRUNode tail = null;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cap = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }

        LRUNode t = map.get(key);

        remove(t);
        setHead(t);

        return t.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            LRUNode t = map.get(key);
            t.value = value;

            remove(t);
            setHead(t);
        }else{
            if(map.size()>=cap){
                map.remove(tail.key);
                remove(tail);
            }

            LRUNode t = new LRUNode(key, value);
            setHead(t);
            map.put(key, t);
        }
    }

    //remove a node
    private void remove(LRUNode t){
        if(t.prev!=null){
            t.prev.next = t.next;
        }else{
            head = t.next;
        }

        if(t.next!=null){
            t.next.prev = t.prev;
        }else{
            tail = t.prev;
        }
    }

    //set a node to be head
    private void setHead(LRUNode t){
        if(head!=null){
            head.prev = t;
        }

        t.next = head;
        t.prev = null;
        head = t;

        if(tail==null){
            tail = head;
        }
    }
}

class LRUNode{
    int key;
    int value;
    LRUNode prev;
    LRUNode next;

    public LRUNode(int key, int value){
        this.key=key;
        this.value=value;
    }
}
