import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Flowers {
	public static void main( String args[] ){
		
		// helpers for input/output		

				Scanner in = new Scanner(System.in);
				
				int n, k;
				n = in.nextInt();
				k = in.nextInt();
				int min=0;
				
				List<Integer> list = new ArrayList<Integer>(n);
				
				for(int i=0; i<n; i++){
					list.add(in.nextInt());
				}
				
				Collections.sort(list);
				int cycle = n/k;
				for(int i=cycle;i>0;i--){
					for(int j=n-(cycle-i)*k-1;j>n-(cycle-i+1)*k-1;j--){
						min += list.get(j)*(cycle-i+1);
					}
				}
				for(int j=n-cycle*k-1;j>=0;j--){
					min+=list.get(j)*(cycle+1);
				}
				
				System.out.println( min );
				
	}

}
