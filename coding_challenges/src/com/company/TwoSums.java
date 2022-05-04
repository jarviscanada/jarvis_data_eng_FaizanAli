package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://www.notion.so/jarvisdev/Two-Sum-4623734546204613ba8acad8795d4d6b
public class TwoSums {

  //hashmap to store numbers already found in array
  // key = number in array
  // value = index of location in array
  Map<Integer,Integer> numMap = new HashMap<>();

  public int[] twoSum(int[] nums, int target){

    for(int i =0; i< nums.length; i++){
      //calculate difference between the target and current number
      int diff = target - nums[i];

      //if the diff number exists in the map
      //meaning we have seen this number in the array previously
      //that means solution is found
      if(numMap.containsKey(diff)){

        //index of number in map and index of current number
        //ie the index of 2 numbers that add up to target
        int returnValue[] = {numMap.get(diff), i};

        return returnValue;
      }

      numMap.put(nums[i],i);
    }

    return null;
  }

  public static void main(String[] args) {
    int nums[] = {2,7,11,15};
    int target = 9;

    TwoSums sums = new TwoSums();

    int returnValue[] = sums.twoSum(nums,target);

    if(returnValue != null){
      Arrays.stream(returnValue).forEach(n->System.out.print(n+" "));
    }
    else{
      System.out.println("Target not found");
    }


  }
}
