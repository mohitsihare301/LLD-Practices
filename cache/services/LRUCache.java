package cache.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import cache.internal.DoublyLinkedList;
import cache.models.Node;

public class LRUCache<K,V> implements Cache<K,V> {
    private int capacity;
    private Map<K, Node<K,V>> cache;
    private DoublyLinkedList<K,V> list;

    private ReentrantLock lock = new ReentrantLock(true);

    public LRUCache(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity=capacity;
        this.cache=new HashMap<>();
        this.list=new DoublyLinkedList<>();
    }

    @Override
    public V get(K key){
        lock.lock();
        try{
            if(!cache.containsKey(key)){
                return null;
            }
            Node<K,V> node = cache.get(key);
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
            if(cache.containsKey(key)){
                Node<K,V> node = cache.get(key);
                node.value=value;
                list.moveToHead(node);
            }
            else{
                Node<K,V> newNode = new Node<>(key, value);
                cache.put(key, newNode);
                list.addAtHead(newNode);
                if(cache.size()>capacity){
                    evictLRU();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void evictLRU(){
        Node<K,V> lru = list.removeTail();
        if(lru!=null){
            cache.remove(lru.key);
        }
        System.out.println("[LRU] Evicting LRU Key: " + lru.key);
    }

    @Override
    public void remove(K key){
        lock.lock();
        try{
            if(!cache.containsKey(key)){
                return;
            }
            Node<K,V> node = cache.remove(key);
            list.removeNode(node);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size(){
        return cache.size();
    }

}
