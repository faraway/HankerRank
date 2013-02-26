import java.util.ArrayList;
import java.util.Scanner;
/**
 * https://www.interviewstreet.com/challenges/dashboard/#problem/4fccb786c980c
 * use adjacent list to represent the tree
 * @author patrick
 *
 */

public class FarVerticleReverse {
	static ArrayList<Integer>[] adjList=null;
	public static void main(String[] args){
		//get input
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(); // vertices
		int k=in.nextInt(); // limit k
		int remain = 0;
		
		//represent the tree as adjacent list
		adjList = new ArrayList[n];
		//build adjcent list
		for(int i=0;i<n-1;i++){
			int u = in.nextInt()-1; //to match the tree node number as array index
			int v = in.nextInt()-1;
			if(adjList[u]==null){
				adjList[u] = new ArrayList<Integer>();
			}
			adjList[u].add(v);
			if(adjList[v]==null){
				adjList[v] = new ArrayList<Integer>();
			}
			adjList[v].add(u);
		}
		//special cases
		if(k==0) {
			System.out.println(n-1);
			System.exit(0);
		}
		if(k==1) {
			if(n>1){
				System.out.println(n-2);
			}else{
				System.out.println("0");
			}
			System.exit(0);
		}
		//normal cases
		int step = 0;
		if(k%2==0){// k is even
			step =k/2;
			for(int i =0;i<adjList.length;i++){
				int temp = getRemainNodes(i,step,-1);
				if(temp>remain){
					remain = temp;
				}
			}
		}else{
			step = (k-1)/2;
			for(int i =0;i<adjList.length;i++){
				int pair1=0;
				int pair2=0;
				for(Integer pair:adjList[i]){
					pair1=getRemainNodes(i,step,pair);
					pair2=getRemainNodes(pair,step,i);
				}
				if(pair1+pair2>remain){
					remain = pair1+pair2;
				}
			}
		}
		System.out.println(n-remain);
		
	}

	/**
	 * expand the vertices based on center
	 * for even step, parent is -1(not exist)
	 * for odd  step, parent is its pair ( so that it will not expand to the pair part)
	 * @param center
	 * @param decStep
	 * @param parent
	 * @return
	 */
	static int getRemainNodes(int center,int decStep,int parent){
		int total = 0;
		if(decStep == 0){
			return 1;
		}
		decStep-- ;
		for(Integer node:adjList[center]){
			if(node != parent){
				total += getRemainNodes(node,decStep,center);
			}		
		}
		//System.out.println("return "+(total+1)+",center:"+(center+1));
		return total+1;
	}
}