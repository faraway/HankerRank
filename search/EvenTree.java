package search;


/**
 * https://www.interviewstreet.com/challenges/dashboard/#problem/4fffc24df25cd
 * @note One Time succeed!
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class EvenTree {
	/**
	 * main  function
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String line = br.readLine();
	    int edges = Integer.parseInt(line.split(" ")[1]);
	    HashMap<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
	    //construct tree
	    for (int i=0;i<edges;i++){
	    	String[] vs = br.readLine().split(" ");
	    	int vj = Integer.parseInt(vs[0]);
	    	int vi = Integer.parseInt(vs[1]);
	    	if(map.get(vi)==null){
	    		ArrayList<Integer> nodes = new ArrayList<Integer>();
	    		nodes.add(vj);
	    		map.put(vi, nodes);
	    	}else{
	    		map.get(vi).add(vj);
	    	}
	    }
	    //get Result
	    Result result = cutTree(1,map);
	    System.out.println(result.totalCut);
			
	}
	
	private static Result cutTree(int root,HashMap<Integer,ArrayList<Integer>> map){
		Result r = new Result(0,1,false);
		if(map.get(root)==null){ // leaf
			return r;
		}else{
			for(Integer i:map.get(root)){
				Result temp = cutTree(i,map);
				if(temp.cut)
					r.totalCut+=1;
				else{
					r.totalNodes+=temp.totalNodes;
				}
				r.totalCut+=temp.totalCut;
			}
			if(r.totalNodes%2==0){
				r.cut=true;
			}
			return r;
		}
	}
}

class Result{
	int totalCut;
	int totalNodes;
	boolean cut;
	Result(int totalCut,int totalNodes,boolean cut){
		this.cut=cut;
		this.totalCut=totalCut;
		this.totalNodes=totalNodes;
	}
}