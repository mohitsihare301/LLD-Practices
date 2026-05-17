package cache.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import cache.internal.DoublyLinkedList;
import cache.models.Node;

public class LRUCache<K,V> implements Cache<K,V> {
    private int capacity;
    private Map<K, Node<K,V>> keyNodeMap;
    private DoublyLinkedList<K,V> list;

    private ReentrantLock lock = new ReentrantLock(true);

    public LRUCache(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity=capacity;
        this.keyNodeMap=new HashMap<>();
        this.list=new DoublyLinkedList<>();
    }

    @Override
    public V get(K key){
        lock.lock();
        try{
            if(!keyNodeMap.containsKey(key)){
                return null;
            }
            Node<K,V> node = keyNodeMap.get(key);
            list.moveToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(K key, V value){
        lock.lock();
        try{
            if(keyNodeMap.containsKey(key)){
                Node<K,V> node = keyNodeMap.get(key);
                node.value=value;
                list.moveToHead(node);
            }
            else{
                if(keyNodeMap.size()>=capacity){
                    evictLRU();
                }

                Node<K,V> newNode = new Node<>(key, value);
                keyNodeMap.put(key, newNode);
                list.addAtHead(newNode);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key){
        lock.lock();
        try{
            if(!keyNodeMap.containsKey(key)){
                return;
            }
            Node<K,V> node = keyNodeMap.remove(key);
            list.removeNode(node);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size(){
        return keyNodeMap.size();
    }

    private void evictLRU(){
        Node<K,V> lru = list.removeTail();
        if(lru!=null){
            keyNodeMap.remove(lru.key);
        }
        System.out.println("[LRU] Evicting LRU Key: " + lru.key);
    }

}
