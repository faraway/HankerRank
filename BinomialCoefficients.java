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
			BigInteger count = n.divide(p);
			BigInteger left  = n.mod(p);
			if(left.compareTo(ZERO)!=0){
				BigInteger off = p.subtract(left);
				BigInteger unit = p.subtract(off).subtract(ONE);
				
				System.out.println(unit.multiply(count));
			}else{
				System.out.println(n.subtract(ONE));
			}

		}
	}

}
