package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://www.notion.so/jarvisdev/How-to-compare-two-maps-187f9bc323f14195949c94bed7a7eb45
public class ComparingTwoMaps {



  public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){

    return m1.equals(m2);
  }

  public static void main(String[] args) {

    Map<Integer, String> map1 = new HashMap<>();

    String temp = "A";
    temp.hashCode();
    map1.put(1, "A");
    map1.put(2, "B");
    map1.put(3, "C");

    Map<Integer, String> map2 = new HashMap<>();

    map2.put(1, "A");
    map2.put(2, "B");
    map2.put(3, "C");

    Map<Integer, String> map3 = new HashMap<>();

    map3.put(1, "z");
    map3.put(2, "s");
    map3.put(3, "d");

    ComparingTwoMaps compare = new ComparingTwoMaps();

    boolean result = compare.compareMaps(map1,map2);

    System.out.println(result);
  }
}
