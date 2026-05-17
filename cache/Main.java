package cache;

import cache.services.Cache;
import cache.services.LRUCache;

public class Main {
    public static void main(String[] args) throws Exception{
        Cache<String,Integer> cache = new LRUCache<>(3);
        cache.put("A",5);
        System.out.println("get(A) = " + cache.get("A"));
        cache.put("B",10);
        System.out.println("get(B) = " + cache.get("B"));
        cache.put("C",6);
        System.out.println("get(C) = " + cache.get("C"));
    
        cache.put("D",8);
        System.out.println("get(B) = "+ cache.get("B"));


        Thread t1 = new Thread(() -> {
            for(int i=0;i<10;i++){
                String key = "K" + i;
                cache.put(key,i);
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0;i<10;i++){
                String key = "K" + i;
                System.out.println("get(i) = " + cache.get(key));
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        System.out.println("Cache Size: " + cache.size());


    }
    
};
