package evernote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class TopFour {
	
	/**
	 * brute force algorithm
	 * @param args
	 */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
	
		int n = in.nextInt();
		in.nextLine();
	
		ArrayList<Integer> array = new ArrayList<Integer>(4);
	
		int k = n>4? 4:n;
    
		for(int i=0 ; i<k ; i++){
			array.add(Integer.parseInt(in.nextLine()));
		}
		
		Collections.sort(array);
		
		for(int i =4; i<n; i++){
			int temp = Integer.parseInt(in.nextLine());
			int index = -1;
			while((index+1) < 4 && temp>array.get(index+1) ){
				index++;
				if(index>0){
					array.set(index-1, array.get(index));
				}
			}
			if(index > -1){
				array.set(index,temp);
			}
		}
		
		for(int i=3;i>-1;i--){
			System.out.println(array.get(i));
		}
		
		in.close();
	}
}

class Node{
	int data;
	Node parent;
	Node left;
	Node right;
	Node (int data, Node parent, Node left, Node right){
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
}