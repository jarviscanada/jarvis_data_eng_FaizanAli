package com.company;

//https://www.notion.so/jarvisdev/Valid-Palindrome-b1b053a2c04046bda20979a3636aaec7
public class ValidPalindrome {

  public boolean isPalindrome(String s) {
    int low = 0;
    int high = s.length()-1;

    while(low < high){

      //while character at index pointed to by low currently is NOT alphanumeric
      while((!Character.isLetterOrDigit(s.charAt(low))) && low < high){
        low++;
      }

      //while character at index pointed to by high currently is NOT alphanumeric
      while((!Character.isLetterOrDigit(s.charAt(high))) && low < high){
        high--;
      }

      //if characters pointed to by low and high respectively are not the same
      // this is not a palindrome
      if(!(Character.toUpperCase(s.charAt(low)) == Character.toUpperCase(s.charAt(high)))){
        return false;
      }

      low++;
      high--;
    }

    return true;
  }

  public static void main(String[] args) {
    String s = "A man, a plan, a canal: Panama";

    ValidPalindrome validP = new ValidPalindrome();
    System.out.println(validP.isPalindrome(s));
  }
}
