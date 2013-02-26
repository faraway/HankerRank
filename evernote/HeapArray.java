package evernote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * use the array to construct the heap.
 * and solve top K problems.
 * Compared to brute force algorithm in TopFour class, this is much better.
 * @author patrick
 *
 */
public class HeapArray {
	
	int k;
	
	int len;
	
	int[] heap;
	
	ArrayList<Integer> unsorted;
	/**
	 * 
	 * @param top k
	 * @param the unsorted list preparing to be processed
	 */
	HeapArray(int k, ArrayList<Integer> list){
		this.k=k;
		len=k+1;
		heap = new int[len];
		unsorted = list;
	}
	
	/**
	 * sort algorithm
	 * @return
	 */
	public ArrayList<Integer> sort(){
		if(unsorted.size()<=k){
			Collections.sort(unsorted);
			return unsorted;
		}else{
			for(int i =1;i<len;i++){
				heap[i]=unsorted.get(i-1);
			}
			buildHeap();
			for(int i=k;i<unsorted.size();i++){
				if(unsorted.get(i)>heap[1]){
					heap[1] = unsorted.get(i);
					minHeap(1);
				}
			}
			
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=1;i<len;i++){
			result.add(heap[i]);
		}
		
		Collections.sort(result);
		return  result;
	}
    /**
     * heap ajust function
     * @param index
     */
	private void minHeap(int index){
		int smallerIndex = index;
		int left=index*2;
		int right=index*2+1;
		
		if(left<len && heap[left]<heap[index]){
			smallerIndex = left;
		}
		if(right<len && heap[right]<heap[smallerIndex]){
			smallerIndex = right;
		}
		
		if(smallerIndex !=index){
			int temp = heap[index];
			heap[index] = heap[smallerIndex];
			heap[smallerIndex] = temp;
			minHeap(smallerIndex);
		}
	}
	/**
	 * heap building function
	 */
	private void buildHeap(){
		for(int i = k/2;i>0;i--){
			minHeap(i);
		}
	}
	
	/**
	 * test method
	 * @param args
	 */
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
		int n = Integer.parseInt(line);
		
		ArrayList<Integer> array = new ArrayList<Integer>(n);
		for(int i=0 ; i<n ; i++){
			array.add(Integer.parseInt(br.readLine()));
		}
		HeapArray heap = new HeapArray(4,array);
		ArrayList<Integer> result = heap.sort();
		for(int i=3;i>=0;i--){
			System.out.println(result.get(i));
		}
	}
}
