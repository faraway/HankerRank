
/**
 * 
https://www.hackerrank.com/challenges/lego-blocks
 * 
 * 
You have 4 types of lego blocks, of sizes (1 x 1 x 1), (1 x 1 x 2), (1 x 1 x 3), and (1 x 1 x 4). Assume that you have an infinite number of blocks of each type.

Using these blocks, you want to make a wall of height N and width M. The wall should not have any holes in it. The wall you build should be one solid structure. A solid structure can be interpreted in one of the following ways: 
(1)It should not be possible to separate the wall along any vertical line without cutting any lego block used to build the wall. 
(2)You cannot make a vertical cut from top to bottom without also cutting one or more lego blocks.

The blocks can only be placed horizontally. In how many ways can the wall be built?

Input: 
The first line contains the number of test cases T. T test cases follow. Each case contains two integers N and M.

Output: 
Output T lines, one for each test case containing the number of ways to build the wall. As the numbers can be very large, output the result modulo 1000000007.

Constraints: 
1 <= T <= 100 
1 <= N,M <= 1000

Sample Input: 
4 
2 2 
3 2 
2 3 
4 4

Sample Output: 
3 
7 
9 
3375

Explanation: 
For the first case, we can have

two (1 * 1 * 2) lego blocks stacked one on top of another.
one (1 * 1 * 2) block stacked on top of two (1 * 1 * 1) blocks.
two (1 * 1 * 1) block stacked on top of one (1 * 1 * 2) block.
For the second case, each row of the wall can contain either two blocks of width 1, or one block of width 2. However, the wall where all rows contain two blocks of width 1 is not a solid one as it can be divided vertically. Thus, the number of ways is 2 * 2 * 2 - 1 = 7.
 * 
 * */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner stdin=new Scanner(System.in);
        int n=stdin.nextInt();
        for (int i=0;i<n;i++) {
            int height = stdin.nextInt();
            int width = stdin.nextInt();
            System.out.println(calculateTotal(width,height));
        }
    }
    
    private static int calculateTotal(int m, int n) {
        int[] array = null;
        switch (m) {
            case 1: 
                return 1;
            case 2:
                array = new int[]{1,2};
                break;
            case 3:
                array = new int[]{1,2,4};
                break;
            case 4:
                array = new int[]{1,2,4,8};
                break;
            default:
                break;
        }
        if (array == null) {
            array = new int[m];
            array[0] = 1;
            array[1] = 2;
            array[2] = 4;
            array[3] = 8;
            for (int i=4; i<m; i++)
                array[i] = array[i-1] + array[i-2] + array[i-3] + array[i-4];
        }
        
        int total = (int)Math.pow(array[m-1], n);
        
        //This piece can be improved, 2*3 is same as 3*2. I'm just too lazy.  
        //TODO: duplicate calculation here...need to fix..damn
        int unsolid = 0;
        for (int i=0; i<m-1; i++) {
          int leftHalf = (int)Math.pow(array[i], n);
          int rightHalf = (int)Math.pow(array[m-i-2], n);
          unsolid += leftHalf*rightHalf;
        }
        return total - unsolid;
    }
}
