//The third program will take as input a text file in the format
//p a
//c1 c2
//and output
//m
//where m is the decryption of c1 and c2.


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class  Problemset23{
	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner (System.in);
		Scanner inFileReader = new Scanner(new File (getInFile(keyboard)));
	    int[] data=getNumber(inFileReader);
	    System.out.println(Arrays.toString(data));
	    int p=data[0];
	    int a=data[1];
	    int c1=data[2];
	    int c2=data[3];
	    int c1Expo=findexponent(c1,a,p);
	    long c1ExpInver=findInverse(c1Expo,p);
	    long m=findm(c1ExpInver,c2,p);
	    System.out.println("The message in number form is" + m);
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
		inFileReader.nextLine();
		result[2]=inFileReader.nextInt();
		result[3]=inFileReader.nextInt();
		return result;
	}
	
	public static int findexponent(int c1,int a,int p){
		return fea(c1,a,p);
	}
	
	public static long findInverse(int c1Expo,int p){
		long a=fea(c1Expo,p-2,p);
		System.out.println(a);
		return a;
	}
	
	public static long findm(long c1ExpInver,int c2,int p){
		return (c1ExpInver*c2)%p;
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
