import java.util.ArrayList;
import java.util.Queue;



public class Test {
	public static void main(String[] args){
		
		String abc = "abc";
		String aaa = "aaa";
		
		System.out.println(aaa.compareToIgnoreCase(abc));
	}

}















/**
import java.math.BigInteger;
import java.util.Scanner;


public class BinomialCoefficients {
	public static BigInteger NONE = new BigInteger("-1");
	public static BigInteger ONE = new BigInteger("1");
	public static BigInteger TWO = new BigInteger("2");
	public static BigInteger ZERO = new BigInteger("0");
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		int N=in.nextInt(); // vertices
		for(int i=0;i<N;i++){
			BigInteger n = in.nextBigInteger();
			BigInteger p = in.nextBigInteger();
			if(p.compareTo(n)>0){
				System.out.println(0);
				continue;
			}
			if(n.mod(TWO).compareTo(ZERO)==0){ // n is odd
				BigInteger half =n.subtract(ONE).divide(TWO);  //int half = (n-1)/2;
				BigInteger min  = n.subtract(half).add(ONE);//int min  = n-half+1; // the min number half of the {n,k} will calculate.
				BigInteger xprimary = NONE;
				odd:for(int x=n;x>=min;x--){
					if (x%p ==0) {
						xprimary = x;
						break odd;
					}
				}
				if(xprimary==-1){
					System.out.println(0);
				}else{
					System.out.println((xprimary-min+1)*2);
				}
			}else{// n is even
				int half = n/2-1;
				int min = n-half+1; // the min number half of the {n,k} will calculate.
				int xprimary = -1;
				even:for(int x=n;x>=min;x--){
					if (x%p ==0) {
						xprimary = x;
						break even;
					}
				}
				if((min-1)%p == 0){
					xprimary = min -1;
				}
				if(xprimary==-1){
					System.out.println(0);
				}else{
					System.out.println((xprimary-min+1)*2+1);
				}
			}
		}
	}

}
**/
