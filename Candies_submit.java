import java.io.*;
/**
 * https://www.interviewstreet.com/challenges/dashboard/#problem/4fe12e4cbb829
 * @author patrick
 *
 */
public class Candies_submit {
	/**
	 * solution
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String line = br.readLine();
                int num = Integer.parseInt(line);
               
		int[] scores = new int[num];
		for(int i = 0;i<num;i++){
                    scores[i]= Integer.parseInt(br.readLine());;		
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
							}else if(trend==1){
							    for(int j =start;j<=i;j++){
							    	total+=start_num;
							    	start_num++;
							    }
							    start_num=1;//reset
							}else{//trend = -1
								int dif = i-start+1;
								for(int j=1;j<dif;j++){
									total+=j;
								}
								if(dif>max){
									total+=dif;
								}else{
									total+=max;
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
								}
								if(dif>max){
									total+=dif;
								}else{
									total+=max;
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
									total+=j;
								}
								start=i;
							}
							trend = -1;
						}
					}else{//coming to end
						if(trend == 0){
							total++;
						}else if(trend==1){
							int dif=i-start+1;
							max = dif+start_num-1;
							for(int j=start_num;j<=max;j++){
								total+=j;
							}
						}else{
							int dif = i-start+1;
							for(int j=1;j<dif;j++){
								total+=j;
							}
							if(dif>max){
								total+=dif;
							}else{
								total+=max;
							}
						}
					}
				}
				System.out.println(total);
				
			}

}

