package cache.internal;

import cache.models.Node;

public class DoublyLinkedList<K,V> {
    private Node<K,V> head;
    private Node<K,V> tail;

    public DoublyLinkedList(){
        this.head= new Node<>(null,null);
        this.tail= new Node<>(null,null);
        head.next= tail;
        tail.prev= head;
    }

    public void addAtHead(Node<K,V> node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }

    public void removeNode(Node<K,V> node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToHead(Node<K,V> node){
        removeNode(node);
        addAtHead(node);
    }

    public Node<K,V> removeTail(){
        if(isEmpty()) return null;
        Node<K,V> lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    public boolean isEmpty(){
        return head.next==tail;
    }

    

}
