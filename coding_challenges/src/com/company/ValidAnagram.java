package com.company;

import java.util.Arrays;

//https://www.notion.so/jarvisdev/Valid-Anagram-b8708bd705574ecfa14ebf5cbb759c95
public class ValidAnagram {

  public boolean checkAnagram(String s, String t){
    //26 character in english
    int charList[] = new int[26];

    //go through first string character by character
    for(int i =0; i < s.length(); i++){

      //ascii value of a=97
      //our array start at index 0
      //so covert each character to their ascii value and subtract 97 from it
      // resulting in a =0, b =1, c=2 ....
      //increment by 1 for each occurrence of a character
      charList[(int)s.charAt(i)-97] += 1 ;
    }

    //go through 2nd string
    //decrement by 1 for each occurrence of a character
    for(int j =0; j < t.length(); j++){
      charList[(int)t.charAt(j)-97] -= 1 ;
    }

    //check if all counts 0
    for(int i : charList){
      if(i != 0){
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String s = "anagram";
    String t = "nagaram";

    ValidAnagram anagram = new ValidAnagram();

    System.out.println(anagram.checkAnagram(s,t));
  }
}
