import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FarVerticle {
	
	public static void main(String[] args){
		//get input
		Scanner in = new Scanner(System.in);
		int n=in.nextInt(); // vertices
		int k=in.nextInt(); // limit k
		int mark = 0;
		int count = 0;
		ArrayList<Node> leaves = new ArrayList<Node>();
		Map<Integer,Node> dic = new HashMap<Integer,Node>();
		
		//represent the tree as adjacent list
		ArrayList<Integer>[] adjList = new ArrayList[n];
		for(int i=0;i<n-1;i++){
			int u = in.nextInt()-1;
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
		//randomly get a root
		Node root=null;
		for(int i =0; i<adjList.length;i++){
			if(adjList[i].size()>1){ // non-leaf
				root = new Node(i,0,null);
				dic.put(i, root);
				break;
			}
		}
		//build tree
		ArrayList<Node> temp = new ArrayList<Node>();
		temp.add(root);
		while(temp.size()>0){
			Node head = temp.get(0);
			if(adjList[head.id].size()>1){
				for(Integer id:adjList[head.id]){
					if(head.parent==null || head.parent.id!=id){
						Node newNode = new Node(id,head.level+1,head);
						newNode.toMark = new HashMap<Integer,Integer>();
						dic.put(newNode.id, newNode);
						System.out.println("Create node:"+(id+1)+", parent:"+(head.id+1)+",level"+(head.level+1));
						temp.add(newNode);
					}
				}
			}else{//leaf node
				leaves.add(head);
			}
			temp.remove(head);
		}
		//System.out.println("Leaves:"+leaves.size());
		//mark
		//System.out.println("Leaves:"+getDistance(leaves.get(1),leaves.get(2)));
		for(int i=0;i<leaves.size();i++){
			for(int j=i+1;j<leaves.size();j++){
				int dis = getDistance(leaves.get(i),leaves.get(j));
				if(dis>k){
					leaves.get(i).toMark.put(leaves.get(j).id, dis);
					leaves.get(j).toMark.put(leaves.get(i).id, dis);
					count++;
					System.out.println("count add:"+count);
				}
			}
		}
		
		while(true){
			Node markNode = leaves.get(0);
			int max = getTotal(markNode.toMark);
			for(Node node:leaves){
//				if (node.toMark.size()>markNode.toMark.size()){
//					markNode = node;
//					max=getTotal(node.toMark);
//				}else if(node.toMark.size()==markNode.toMark.size()&&getTotal(node.toMark)>max){
//					markNode = node;
//					max = getTotal(node.toMark);
//				}
//				
				if (getTotal(node.toMark)>max){
					markNode = node;
					max=getTotal(node.toMark);
				}else if(getTotal(node.toMark)==max && node.toMark.size()>markNode.toMark.size()){
					markNode = node;
					max = getTotal(node.toMark);
				}
			}
			if(markNode.toMark.size()==0){
				break;
			}
			mark++; // mark node
			leaves.remove(markNode);
			System.out.println("remove node"+(markNode.id+1));
			//adjList[markNode.parent.id].remove(new Integer(markNode.id));
			Integer next = adjList[markNode.id].get(0);
			Node nextNode = dic.get(next);
			adjList[next].remove(new Integer(markNode.id));
			if(adjList[next].size()==1){// new leaf
				nextNode.toMark= new HashMap<Integer,Integer>();
			}
			//update relation with other nodes
			for(Node node:leaves){
				Integer afterMark = node.toMark.get(markNode.id);
				if(afterMark!=null){
					afterMark = afterMark-1;
					node.toMark.remove(markNode.id);
					if(afterMark <= k){
						count--;
						System.out.println("count --:"+count);
					}else{
						if(adjList[next].size()==1){
							node.toMark.put(next, afterMark);
							nextNode.toMark.put(node.id, afterMark);
						}
					}
				}
			}
			if(adjList[next].size()==1){// 
				leaves.add(nextNode);
			}
		}
		
		System.out.println(mark);
		
		
	}

	
	/**
	 * get distance of two leaves
	 * @param a
	 * @param b
	 * @return
	 */
	static int getDistance(Node a, Node b){
		Node base = a.level < b.level ? a : b;
		Node other = base == a ? b:a;
		int sigle = other.level-base.level;
		int doub = 0;
		Node samelevel =other;
		for(int i=0;i<sigle;i++){
			samelevel=samelevel.parent;
		}
		if(samelevel.id==base.id){ //base is ancestor of other
			return sigle;
		}else{ // find common ancestor
			while(base.id!=samelevel.id){
				doub+=2;
				base=base.parent;
				samelevel=samelevel.parent;
			}
			return sigle+doub;
		}
		
	}
	
	static int getTotal(HashMap<Integer,Integer> map){
		int total=0;
		for(Map.Entry<Integer, Integer> item : map.entrySet()){
			total +=item.getValue();
		}
		return total;
	}
}

//tree node
class Node{
	public Node(){}
	public Node(int id,int level,Node parent){
		this.id=id;
		this.level=level;
		this.parent=parent;
	}
	int id;
	int level;
	Node parent;
	HashMap<Integer,Integer> toMark;
}
	