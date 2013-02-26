package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * won't work
 * 矩阵相乘？
 * @author patrick
 *
 */
public class StringReduction {
	public static void main(String[] args) throws Exception{
		//get input
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	    int num = Integer.parseInt(br.readLine());
//	    String[] cases = new String[num];
//	    for(int i=0;i<num;i++){
//	    	cases[i] = br.readLine();
//	    }
	    
		String[] cases = new String[]{"bcacccc"};
	    for(String s : cases){
	    	if(s.length()==0||s.length()==1){
	    		System.out.println(s.length());
	    	}else{
	    		Result[] array = new Result[s.length()];
	    		array[0] = new Result(s.charAt(0),-1,0);
	    		if(s.charAt(0)==s.charAt(1)){
	    			array[1]=new Result(s.charAt(1),0,0);
	    		}else{
	    			array[1]=new Result(getThird(s.charAt(0),s.charAt(1)),-1,1);
	    		}
	    		for(int i=2;i<s.length();i++){
	    			Result r = new Result(s.charAt(i),i-1,0);
	    			reduce(r,array);
	    			if(s.charAt(i)==s.charAt(i-1)){
	    				array[i] = r;
	    			}else{
	    				Result r2 = new Result(getThird(s.charAt(i),s.charAt(i-1)),i-2,1);
	    				reduce(r2,array);
	    				array[i] = r2.totalReduced>r.totalReduced?r2:r;
	    			}
	    		}
	    		System.out.println(s.length()-array[s.length()-1].totalReduced);
	    	}
	    }
	}
	
	private static char getThird(char a,char b){
		return (char)('a'+'b'+'c'-(int)a-(int)b);
	}
	
	private static void reduce(Result r,Result[] array){
		r.totalReduced = array[r.preIndex].totalReduced;
		while(r.preIndex>=0 && r.afterReduced!=array[r.preIndex].afterReduced){
			r.afterReduced=getThird(r.afterReduced,array[r.preIndex].afterReduced);
			r.totalReduced+=+1;
			r.preIndex=array[r.preIndex].preIndex;
		}
	}
	
}

class Result{
	char afterReduced;
	int  preIndex;
	int totalReduced;
	Result(char a,int index, int total){
		afterReduced = a;
		preIndex = index;
		totalReduced = total;
	}
	Result(){}
}
