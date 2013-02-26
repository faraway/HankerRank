package evernote;

import java.util.Scanner;

public class CircularBuffer{
    private int size;
    
    private int count;
    
    private int head,tail;
    
    private String[] array;
    
    CircularBuffer(int size){
        this.size=size;
        array = new String[size];
        count = 0;
        head = 0;
        tail = 0;
    }
    
    public void append(String s){
    	if(count<size){ //not full, just add
    		array[tail] = s;
    		count++;
    	}else{//override earliest elements
    		head=(head+1)%size;
    		array[tail] = s;
    	}
    	tail=(tail+1)%size;
    }
    
    public void remove(int n){
        head=(head+n)%size;
        count-=n;
    }
    
    public void list(){
        if(tail>head && count<size){
        	for(int i=head;i<tail;i++){
        		System.out.println(array[i]);
        	}
        }else{
        	for(int i=head;i<size;i++){
        		System.out.println(array[i]);
        	}
        	for(int i=0;i<tail;i++){
        		System.out.println(array[i]);
        	}
        }
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		in.nextLine();
		CircularBuffer cb = new CircularBuffer(n);
		String command = in.nextLine();
		while(command!=null){
			switch(command.charAt(0)){
			case 'A':
			{
				int num = Integer.parseInt(command.split(" ")[1]);
				for(int i=0;i<num;i++){
					String temp = in.nextLine();
					System.out.println(temp);
					cb.append(temp);
				}
				break;
			}
			case 'R':
			{
				int num = Integer.parseInt(command.split(" ")[1]);
				cb.remove(num);
				break;
			}
			case 'L':
				cb.list();
				break;
			case 'Q':
				System.exit(0);
			}
			command = in.nextLine();
		}
    }
    
}