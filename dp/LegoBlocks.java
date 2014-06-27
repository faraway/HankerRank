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
        
        //This piece can be improved, 2*3 is same as 3*2. I'm just too lazy.  TODO: duplicalculate here...damn
        int unsolid = 0;
        for (int i=0; i<m-1; i++) {
          int leftHalf = (int)Math.pow(array[i], n);
          int rightHalf = (int)Math.pow(array[m-i-2], n);
          unsolid += leftHalf*rightHalf;
        }
        return total - unsolid;
    }
}
