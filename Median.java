import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Median{
	public static List<Integer> list;
	
	public static Map<Integer,Integer> dic;
	
	public static boolean remove(int element){
		if(dic.get(element)==null||dic.get(element).intValue()==0){
			System.out.println("Wrong!");
			return false;
		}
		int index = -1;
		for(int i=0;i<list.size();i++){
			if (list.get(i).intValue()==element){
				index=i;
			}
		}
		if(index!=-1){
			list.remove(index);
			dic.put(element, dic.get(element)-1);
			return true;
		}else{
			System.out.println("Wrong!");
			return false;
		}
		
	}
	
	public static void main( String args[] ){
		list = new ArrayList<Integer>();
		dic = new HashMap<Integer,Integer>();
		//get input
		Scanner in = new Scanner(System.in);
		int num;
		num = in.nextInt();
		String s[] = new String[num];
		int x[] = new int[num];
		for(int i=0; i<num; i++){
			s[i] = in.next();
			x[i] = in.nextInt();
		}
	
		//parameters
		int elements = 0;
		double median=0;
		for (int i=0;i<num;i++){
			if(s[i].equals("a")){//add
				int pos=0;
				for(int j=list.size()-1;j>=0;j--){
					if(x[i]>=list.get(j).intValue()){
						pos=j+1;
						break;
					}
				}
				list.add(pos,x[i]);
				elements++;
				if(dic.get(x[i])==null){
					dic.put(x[i], 1);
				}else{
					dic.put(x[i], dic.get(x[i])+1);
				}
				
				if(elements%2==0){ //even elements
					median=(list.get(elements/2).doubleValue()+list.get(elements/2-1).doubleValue())/2.0;
					String temp = median != (int)median ? String.valueOf(new BigDecimal(median)):String.valueOf((int)median);
					System.out.println(temp);
				}else{//odd
					System.out.println(list.get((elements-1)/2));
				}
			}else{//remove
				if(remove(x[i])){
					elements--;
					if (elements==0){
						System.out.println("Wrong!");
					}else if(elements%2==0){ //even elements
						median=(list.get(elements/2).doubleValue()+list.get(elements/2-1).doubleValue())/2.0;
						String temp = median != (int)median ? String.valueOf(new BigDecimal(median)):String.valueOf((int)median);
						System.out.println(temp);
					}else{//odd
						System.out.println(list.get((elements-1)/2));
					}
				}
			}
		}
		
	}
}