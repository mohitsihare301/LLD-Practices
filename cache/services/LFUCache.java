package cache.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import cache.internal.DoublyLinkedList;
import cache.models.Node;

public class LFUCache<K,V> implements Cache<K,V> {
    private int capacity;
    private int minFreq;
    private Map<K, Node<K,V>>keyNodeMap;
    private Map<Integer,DoublyLinkedList<K,V>> frequencyMap;

    private ReentrantLock lock = new ReentrantLock(true);

    public LFUCache(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.keyNodeMap=new HashMap<>();
        this.frequencyMap= new HashMap<>();
        this.minFreq=0;
    }

    @Override
    public V get(K key){
        lock.lock();
        try{
            if(!keyNodeMap.containsKey(key)){
                return null;
            }
            Node<K,V> node = keyNodeMap.get(key);
            upadateFrequency(node);
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
                upadateFrequency(node);
            } else {
                if(keyNodeMap.size()>=capacity){
                    evictLFU();
                }

                Node<K,V> newNode = new Node<>(key, value);
                keyNodeMap.put(key, newNode);
                addToFrequencyList(newNode, 1);
                minFreq=1;
            
            }             
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key){
          if(!keyNodeMap.containsKey(key)) return;
          Node<K,V> node = keyNodeMap.remove(key);
          DoublyLinkedList<K,V> currentList = frequencyMap.get(node.freq);
          currentList.removeNode(node);
          if(currentList.isEmpty()){
            frequencyMap.remove(node.freq);
          }

    }

    @Override
    public int size(){
        return keyNodeMap.size();
    }

    private void upadateFrequency(Node<K,V> node){
        DoublyLinkedList<K,V> currentList = frequencyMap.get(node.freq);
        currentList.removeNode(node);
        if(currentList.isEmpty()){
            frequencyMap.remove(node.freq);
            if(node.freq == minFreq){
                minFreq++;
            }
        }
        node.freq++;
        addToFrequencyList(node, node.freq);
    }

    private void addToFrequencyList(Node<K,V> node, int freq){
        frequencyMap.computeIfAbsent(freq, k -> new DoublyLinkedList<>()).addAtHead(node);
    }

    private void evictLFU(){
        DoublyLinkedList<K,V> currentList = frequencyMap.get(minFreq);
        Node<K,V> lru = currentList.removeTail();
        if(lru!=null){
            keyNodeMap.remove(lru.key);
            System.out.println("[LFU] Evicting LFU Key: " + lru.key);
        }
        if(currentList.isEmpty()){
            frequencyMap.remove(minFreq);
        }
    }


}
