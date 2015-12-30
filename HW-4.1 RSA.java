//Write code to carry out the following attacks on RSA:
//(a) Assume that N = pq is known to be the product of two primes which
//are very close together. Use the work above to write a program that
//decrypts an RSA ciphertext. (Assume you are given N, e, and the
// ciphertext c.)

import java.util.Scanner;


public class RSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Scanner sc=new Scanner(System.in);
     System.out.println("Input 3 integers in order");
     System.out.println("The first one is N");
     System.out.println("The second one is e");
     System.out.println("The thrid one is the integer represent ciphertext c");
     long N=sc.nextLong();
     long e=sc.nextLong();
     long c=sc.nextLong();
     long q=findQ(N);
     long p=findP(N);
     long parprod=(p-1)*(q-1);     
     long d=findd(e,parprod);
     long x=findResult(c,d,N);
     System.out.println("x= "+ x);
	}

	public static long findQ(long N){

		long a=(long)(Math.ceil(Math.sqrt(N)));
	
		while(N%a!=0){
			a++;			
		}
		return a;
	}
	
	public static long findP(long N){

		long a=(int)(Math.ceil(Math.sqrt(N)));
		while(N%a!=0){
			a--;			
		}
		return a;
	}
	
	public static long findd(long e,long parprod){
		long d=1;
		while((e*d)%parprod!=1){
			d++;
		}
		return d;
	}
	
	public static long findResult(long c,long d,long N){
		long result=1;
		for(int i=0;i<d;i++){
			result*=c;
			if(result>N){
				result=result%N;
			}
		}
		return result;
	}	
}
