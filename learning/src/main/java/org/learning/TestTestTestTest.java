package org.learning;

import java.util.Scanner;

public class TestTestTestTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int i = in.nextInt(); //获得输入的整数
			String s = String.valueOf(i); //整数转String类型
			StringBuffer str = new StringBuffer(s); //String转StringBuffer类型
			System.out.println(str.reverse().toString()); //利用StringBuffer进行逆序，然后输出
		}
	}
}

/**
public class TestTestTestTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.nextLine();
			//String str  = "abcDceddFdeee";
			char[] c = str.toCharArray();
			
			for(int i=0; i<c.length-2; i++){
				if(c[i]==c[i+2]){
					c[i+1] = c[i];
				}
			}
			String s = String.valueOf(c);
			System.out.println(s);
		}
	}
}
**/

/*


public class TestTestTestTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String str = in.nextLine();
			// String str = "1992212";
			char[] c = str.toCharArray();
			Set<Integer> s = new TreeSet();
			for (int i = 0; i < c.length; i++) {
				s.add(Integer.parseInt(String.valueOf(c[i])));
			}
			Iterator<Integer> it = s.iterator();
			int i = s.size();
			int sum = 0;
			for (Integer temp : s) {
				sum += temp * Math.pow(10, i - 1);
				i--;
			}
			System.out.println(sum);
		}
	}
}
 */
