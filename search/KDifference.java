package search;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KDifference {
	public static void main(String[] args){
		//get input
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(); // numbers
		int k=in.nextInt(); // difference k
		int[] numbers = new int[n];
		for(int i =0;i<n;i++ ){
			numbers[i]=in.nextInt();
		}
		
		int pairs = 0;
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0 ; i<n ; i++){
			int num = numbers[i];
			if(map.get(num)!=null){
				pairs+=map.get(num);
			}
			if(map.get(num+k)!=null){
				int temp = map.get(num+k);
				map.put(num+k, temp+1);
			}else{
				map.put(num+k, 1);
			}
			if(map.get(num-k)!=null){
				int temp = map.get(num-k);
				map.put(num-k, temp+1);
			}else{
				map.put(num-k, 1);
			}
			
		}
		System.out.println(pairs);
	}

}
