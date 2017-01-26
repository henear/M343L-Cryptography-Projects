/*
Write a program that solves congruences as guaranteed by the Chinese remainder theorem.  The program should read a file in the following format:
first line is k
each line thereafter contains a_i and m_i separated by a space
Output z such that z = a_i mod m_i for all 1 \leq i \leq k.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Problemset31 {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner keyboard = new Scanner (System.in);
		Scanner inFileReader = new Scanner(new File (getInFile(keyboard)));
		int k=inFileReader.nextInt();
		int[] ai=new int[k];
		int[] mi=new int[k];
		int prod=1;
		for(int i=0;i<k;i++){
			ai[i]=inFileReader.nextInt();
			mi[i]=inFileReader.nextInt();
			inFileReader.nextLine();
			prod*=ai[i];
		}
		int[] res=everyTerm(ai,prod);
		int a=findResult(res,mi,k,prod);
		System.out.println(a);
	}
	public static String getInFile(Scanner keyboard) {
		System.out.print("Enter the name of the file with the ChineseRemainderTheorem data: ");
		String fileName=keyboard.nextLine();
		System.out.println();
		return fileName;
	}
	
	public static int[] everyTerm(int[] ai,int prod){
		int[] result=new int[ai.length];
		for(int i=0;i<ai.length;i++){
			result[i]=prod/ai[i];
			int temp=result[i];
			while(result[i]%ai[i]!=1){
				result[i]+=temp;
			}
		}
		return result;
	}
	
	public static int findResult(int[] res,int[] mi,int k,int prod){
		int sum=0;
		for(int i=0;i<k;i++){
			sum+=res[i]*mi[i];
		}
		int result=sum-(sum/prod)*prod;
		return result;
	}
}
