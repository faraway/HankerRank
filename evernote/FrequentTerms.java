package evernote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * Use heap to solve most frequent items problem
 * Top K paradigm .
 * @author patrick
 * @date 2012.10.8
 *
 */
public class FrequentTerms {
	
	static int len;
    static String[] heap;
    static Map<String,Integer> map;
    
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        map = new HashMap<String,Integer>();
        
        for(int i=0;i<n;i++){
        	String s = br.readLine();
        	if(map.get(s)==null){
        		map.put(s, 1);
        	}else{
        		int count = map.get(s);
        		count++;
        		map.put(s, count);
        	}
        }
        
        int k = Integer.parseInt(br.readLine());

        len = map.size()+1;
        heap = new String[len];
        
        int index=1;
        for(String key:map.keySet()){
        	heap[index]=key;
        	buildHeap(index);
        	index++;
        }
        
        int last=len-1;
        for(int i=0;i<k;i++){
        	System.out.println(heap[1]);
        	heap[1] = heap[last];
        	ajustHeap(1,last);
        	last--;     	
        }
        
	}
	
	public static void buildHeap(int index){
		int p = index/2;
		if(p>0){
			String current = heap[index];
			String parent = heap[p];
			if(map.get(current)>map.get(parent)||(map.get(current)==map.get(parent) && current.compareToIgnoreCase(parent)<0)){
				heap[p]=current;
				heap[index]=parent;
				buildHeap(p);
			}
		}
	}
	
	public static void ajustHeap(int index,int len){
		int largerIndex = index;
		int left=index*2;
		int right=index*2+1;
		
		if(left<len && (map.get(heap[left])>map.get(heap[index])||(map.get(heap[left])==map.get(heap[index]) && heap[left].compareToIgnoreCase(heap[index])<0))){
			largerIndex = left;
		}
		if(right<len && (map.get(heap[right])>map.get(heap[largerIndex])||(map.get(heap[right])==map.get(heap[largerIndex]) && heap[right].compareToIgnoreCase(heap[largerIndex])<0))){
			largerIndex = right;
		}
		
		if(largerIndex !=index){
			String temp = heap[index];
			heap[index] = heap[largerIndex];
			heap[largerIndex] = temp;
			ajustHeap(largerIndex,len);
		}
	}

}

