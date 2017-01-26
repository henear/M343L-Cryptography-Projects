/*Good news, everyone ---

        For the second problem set, there is a single exercise.

You need to write three programs that implement ElGamal encryption.

The first program will take as input a text file in which the first line has two entries:
p g
and will select a private key for Alice and output
a A
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Problemset21 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner keyboard = new Scanner (System.in);
		Scanner inFileReader = new Scanner(new File (getInFile(keyboard)));
		System.out.println("Input two integers, the first one is p and the second one serves as g");
		int p=inFileReader.nextInt();
		int g=inFileReader.nextInt();
		System.out.println(p+" "+g);
		Random r=new Random();
		System.out.println("Please in put the integer that you want to serve as the maximal bound of the random number");
		int rMax=keyboard.nextInt();
		int a=r.nextInt(rMax);
		System.out.println("Your random number is "+ a);
		int A=fea2(g,153,p);
		System.out.println("The random number is "+ 153 +" "+" and the encrypted result is "+ A);
	}
	
	public static String getInFile(Scanner keyboard) {
		System.out.print("Enter the name of the file with the personality data: ");
		String fileName=keyboard.nextLine();
		System.out.println();
		return fileName;
	}
	
	//the "main method" for whole
	public static int fea2(int base,int expo,int divisor){
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

