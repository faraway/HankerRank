import java.io.BufferedReader;
import java.io.FileReader;

/**
 * https://www.interviewstreet.com/challenges/dashboard/#problem/4fe12e4cbb829
 * @author patrick
 *
 */
public class Candies {
	static int read(){
		try {
			java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			String line = stdin.readLine();
			return Integer.parseInt(line);
		}catch (java.io.IOException e) {
			System.out.println(e);
		}catch (NumberFormatException e) { 
			System.out.println(e); 
		}
	  return 0;
	}
	//for reading test case file
	static int read(BufferedReader br){
		try{
			return Integer.parseInt(br.readLine());
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * solution
	 * @param args
	 */
	public static void main(String[] args){
		//file input , for test
		BufferedReader br=null;
		try {
			FileReader reader = new FileReader("test/input02.txt");
			br = new BufferedReader(reader);
		}catch (java.io.IOException e) {
			System.out.println(e);
		}
		
		
		//get input, find lowest score
		int num = read(br);
		int[] scores = new int[num];
		for(int i = 0;i<num;i++){
			scores[i]= read(br);		
			//System.out.println(scores[i]);
		}
		//init paramater
		int total=0;
		int start = 0;
		int start_num = 1;
		int trend = 0;
		int max = 0;
		
		for(int i=0;i<scores.length;i++){
			if(i+1<scores.length){
				int next = scores[i+1];
				if(next==scores[i]){
					if(trend==0){
						total++;
						System.out.println("add1:"+"1");
					}else if(trend==1){
					    for(int j =start;j<=i;j++){
					    	total+=start_num;
					    	System.out.println("add2:"+start_num);
					    	start_num++;
					    }
					    start_num=1;//reset
					}else{//trend = -1
						int dif = i-start+1;
						for(int j=1;j<dif;j++){
							total+=j;
							System.out.println("add3:"+j);
						}
						if(dif>max){
							total+=dif;
							System.out.println("add4:"+dif);
						}else{
							total+=max;
							System.out.println("add5:"+max);
						}
					}
					//acc_sum=1;
					trend=0;
					start=i+1;
					start_num=1;
					max=0;
				}else if(next > scores[i]){
					if(trend==1){
						continue;
					}else if(trend==0){
						trend =1 ;
						continue;
					}else{//trend =-1
						int dif = i-start+1;
						for(int j=1;j<dif;j++){
							total+=j;
							System.out.println("add6:"+j);
						}
						if(dif>max){
							total+=dif;
							System.out.println("start:"+start+",dif"+dif);
							System.out.println("add7:"+dif);
						}else{
							total+=max;
							System.out.println("add8:"+max);
						}
						start=i+1;
						start_num=2;
						
					}
					trend = 1;
				}else{//next < scores[i]
					if(trend ==-1){
						continue;
					}else if(trend ==0){
						trend = -1;
						continue;
					}else{//trend =1
						int dif=i-start+1;
						max = dif+start_num-1;
						for(int j=start_num;j<max;j++){
							System.out.println("add9:"+j);
							total+=j;
						}
						start=i;
					}
					trend = -1;
				}
			}else{//coming to end
				if(trend == 0){
					total++;
					System.out.println("add:"+"1");
				}else if(trend==1){
					int dif=i-start+1;
					max = dif+start_num-1;
					for(int j=start_num;j<=max;j++){
						total+=j;
						System.out.println("add:"+j);
					}
				}else{
					int dif = i-start+1;
					for(int j=1;j<dif;j++){
						total+=j;
						System.out.println("add:"+j);
					}
					if(dif>max){
						total+=dif;
						System.out.println("add:"+dif);
					}else{
						total+=max;
						System.out.println("add:"+max);
					}
				}
			}
		}
		System.out.println("resut:"+total+"---size:"+scores.length);
		
	}
	

}
