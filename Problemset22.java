/*The second program will take as input a text file in the format
g p A
m

where m is a string of English text (including spaces and punctuation), and output
c1 c2
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Problemset22{
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner (System.in);
		Scanner inFileReader = new Scanner(new File (getInFile(keyboard)));
		Random r=new Random();
		int[] data=getNumber(inFileReader);
		int g=data[0];
		int p=data[1];
		int A=data[2];
		System.out.println("Please in put the integer that you want to serve as the maximal bound of the random number");
		int rMax=keyboard.nextInt();
		int k=r.nextInt(rMax);
		int m=0;
		int c2=0;
		int c1=computeC1(g,k,p);
		if(inFileReader.hasNextInt()){
			m=inFileReader.nextInt();
			c2=computeC2(m,A,k,p);
			System.out.println("The pair of number c1 and c2 are ("+ c1+","+ c2 +") respectively");
		}else {
			String s=inFileReader.next();
			String total="";
			char[] sc=s.toCharArray();
			int[] si=new int[sc.length];
			for(int i=0;i<sc.length;i++){
				si[i]=(int)(sc[i]); 
				c2=computeC2(si[i],A,k,p);
				
				String temp="";

				if(c2<10){
					temp="00"+c2;
				}else if(c2<100){
					temp="0"+c2;
				}else temp=""+c2;
				total=temp+total;
			}
			System.out.println("The pair of number c1 and c2 are ("+ c1+","+ total +") respectively, with each character represented by 3 digits long number");
		}
	}
	public static String getInFile(Scanner keyboard) {
		System.out.print("Enter the name of the file with the cryptography data: ");
		String fileName=keyboard.nextLine();
		System.out.println();
		return fileName;
	}

	public static int[] getNumber(Scanner inFileReader){
		int[] result=new int[4];
		result[0]=inFileReader.nextInt();
		result[1]=inFileReader.nextInt();
		result[2]=inFileReader.nextInt();
		return result;
	}

	public static int computeC1(int g,int k,int p){
		return fea(g,k,p);
	}

	public static int computeC2(int m,int A,int k,int p){
		int par=fea(A,k,p);
		int parm=m%p;
		return (par*parm)%p;
	}

	public static int fea(int base,int expo,int divisor){
		int prod=1; 
		for(int i=0;i<expo;i++){
			prod*=base;
			if(prod>divisor){
				prod=prod%divisor;
			}
		}
		return prod;
	}
}
