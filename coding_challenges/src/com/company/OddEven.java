package com.company;

public class OddEven {

    /**
     * Big-o: O(1)
     * Justification: Only arithmetic operation
     *
     */
    public String oddEvenMod(int i){
        return i % 2 == 0 ? "even" : "odd";
    }

    /**
     * all odd numbers have there last value in binary value set to 1 and even have there binary value set to 0
     * performing a bitwite and opertation on the number with 1 will result in 1 if odd else 0
     * Big-O:O(1)
     * Justification: only arithmetic operation
     */
    public String oddEvenBit(int i){
        return ((i & 1) == 0 ? "even" : "odd");
    }

    public static void main(String[] args) {

        int number = 11;

        OddEven oe = new OddEven();

        String result = oe.oddEvenBit(number);

        System.out.println(result);
    }
}

