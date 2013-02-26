import java.util.Scanner;


public class MeetingPoint {
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long[][] points = new long[n][3];
		for(int i=0; i<n;i++){
			points[i][0]= in.nextLong();
			points[i][1]= in.nextLong();
		}
		long min = Long.MAX_VALUE;
		for(int i=0; i<n; i++){
			long sum = 0;
			inner:for(int j=0; j<n; j++){
				sum += getDistance(points[i][0],points[i][1],points[j][0],points[j][1]);
				if(sum>min){
					break inner;
				}
			}
			if (sum < min){
				min = sum;
			}
		}
		System.out.println(min);
		
	}
	
	static long getDistance(long x1, long y1, long x2, long y2){
		long x = x1 - x2 >0 ? x1-x2 : x2-x1;
		long y = y1 - y2 >0 ? y1-y2 : y2-y1;
		return x-y > 0 ? x : y;
	}

}
