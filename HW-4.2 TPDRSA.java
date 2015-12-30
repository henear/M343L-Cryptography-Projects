//Assume that you have ciphertexts which correspond to the same message
//encrypted using e = 3 and three distinct moduli N1, N2, and N3.
//Decrypt the ciphertext.
import java.util.Scanner;
public class TPDRSA {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please input N1,N2,N3");
		long N1=sc.nextLong();
		long N2=sc.nextLong();
		long N3=sc.nextLong();
		System.out.println("Please input c1,c2,c3");
		long c1=sc.nextLong();
		long c2=sc.nextLong();
		long c3=sc.nextLong();
		long GCDN12=findGCD(N1,N2);
		long GCDN13=findGCD(N1,N3);
		long GCDN23=findGCD(N2,N3);
		if(GCDN12==GCDN23&&GCDN12==GCDN13&&GCDN12==1){
		long partial=getResult(N1,N2,N3,c1,c2,c3);	
        long m=findCube(partial,N1,N2,N3);
        System.out.println("m= "+ m);
		}else {
			if(GCDN12!=1){
				long common=GCDN12;
				long q1=N1/common;
				long d=getd(common,q1);
			
				long m=findm(c1,d,N1);
				System.out.println("m= " +m);
			}
			else if(GCDN13!=1){
				long common=GCDN13;
				long q1=N1/common;
				
				long d=getd(common,q1);
				long m=findm(c1,d,N1);
				System.out.println("m= " +m);
			}	
			else if(GCDN23!=1){
				long common=GCDN23;
				long q1=N1/common;
				
				long d=getd(common,q1);
				long m=findm(c1,d,N2);
				System.out.println("m= " +m);
			}
		}
	}
    public static long findGCD(long a,long b){
       long mi=Math.min(a, b);
       long ma=Math.max(a, b);
       while(ma%mi!=0){
    	   long temp=ma%mi;
    	   ma=mi;
    	   mi=temp;
       }
       return mi;
    }
    
	public static long getResult(long N1,long N2,long N3, long c1, long c2, long c3){
		long coef=0;
		boolean validN1N2=false;
		boolean validN1N2N3=false;
		long resultN1N2=0;		
		while(!validN1N2){
			resultN1N2=coef*N1+c1;
			if(resultN1N2%N2==c2){				
				validN1N2=true;
			}else{
				coef++;
			}
		}
		long resultN1N2N3=resultN1N2;
		while(!validN1N2N3){
		    if(resultN1N2N3%N3==c3){
		    	validN1N2N3=true;
		    }
		    else{
		    	resultN1N2N3+=N1*N2;
		    }
		}
		return resultN1N2N3;
	}
	
	public static long findCube(long partial,long N1, long N2, long N3){
		double a=(Math.pow(partial, 1.0/3));
		System.out.println("first a" + a);
		while(!(Math.abs(a-Math.ceil(a))<0.00001||Math.abs(a-Math.floor(a))<0.00001)){
			partial+=N1*N2*N3;
			a=(Math.pow(partial, 1.0/3));
			System.out.println("a= "+a);
		}
		return (long)(a);
	}


   public static long getd(long common,long q1){
	   long d=0;
	   long nb=(common-1)*((q1)-1);
	  while(((3*d)%(nb))!=1){
	   d++;
   }
	  return d;
   }
   
   public static long findm(long base,long expo,long divisor){
		long prod=1;
		for(int i=0;i<expo;i++){
			prod*=base;
			if(prod>divisor){
				prod=prod%divisor;
			}
		}
		return prod;
	}	 
   }
