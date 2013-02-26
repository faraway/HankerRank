package string;
/**
 * String Similarity (25 Points)

For two strings A and B, we define the similarity of the strings to be the length of the longest prefix common to both strings. For example, the similarity of strings "abc" and "abd" is 2, while the similarity of strings "aaa" and "aaab" is 3.

Calculate the sum of similarities of a string S with each of it's suffixes.

Input:
The first line contains the number of test cases T. Each of the next T lines contains a string each.

Output:
Output T lines containing the answer for the corresponding test case.

Constraints:
1 <= T <= 10
The length of each string is at most 100000 and contains only lower case characters.

Sample Input:
2
ababaa
aa

Sample Output:
11
3

Explanation:
For the first case, the suffixes of the string are "ababaa", "babaa", "abaa", "baa", "aa" and "a". The similarities of each of these strings with the string "ababaa" are 6,0,3,0,1,1 respectively. Thus the answer is 6 + 0 + 3 + 0 + 1 + 1 = 11.

For the second case, the answer is 2 + 1 = 3.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class StringSimilarity {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
		
		int n = Integer.parseInt(line);
		for (int i=0;i<n;i++){
			String s = br.readLine();
			char[] c = s.toCharArray();
			int sum = c.length;
			int stub = 0;
			ArrayList<Integer> points = new ArrayList<Integer>();
			for(int j=1 ;j<c.length;j++){
				if(c[stub]==c[j]){
					points.add(j);
				}
			}
			sum+=points.size();
			stub++;
			while(points.size()>0){
				Iterator<Integer> it = points.iterator();
				while(it.hasNext()){
					Integer integer = it.next();
					if(integer+stub>c.length-1 || c[stub]!=c[integer+stub]){
						it.remove();
					}
				}
				sum+=points.size();
				stub++;
			}
			System.out.println(sum);
		}
	}

}

