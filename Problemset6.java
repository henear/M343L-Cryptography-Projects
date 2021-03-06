/*
Please write a program to evaluate the group operation on an elliptic curve.
Take the coefficients of the curve as input as well as the points to add.
(Please check to be sure that the input points are actually on the curve!)
*/

import java.util.Scanner;
public class Problemset6 {
	public static void main(String[] args) {
		greetings();
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long p=sc.nextLong();
		System.out.println("Please input the coordinate for one point");
		System.out.println("The first as the x-coordinate and the second as the y-coordinate");
		long x1=sc.nextLong();
		long y1=sc.nextLong();
		while(!checkValid(x1,y1,a,b,p)){
			System.out.println("Your entry is invalid!");
			System.out.println("Please reenter the coordinates");
			x1=sc.nextLong();
			y1=sc.nextLong();
		}
		System.out.println("Please input the coordinate for the other point");
		System.out.println("The first as the x-coordinate and the second as the y-coordinate");
		long x2=sc.nextLong();
		long y2=sc.nextLong();
		while(!checkValid(x2,y2,a,b,p)){
			System.out.println("Your entry is invalid!");
			System.out.println("Please reenter the coordinates");
			x2=sc.nextLong();
			y2=sc.nextLong();
		}
		long[] result=addition(x1,x2,y1,y2,a,b,p);
		System.out.println("The addition result is (" + result[0] +"," +result[1]+").");
		sc.close();
	}
	
	public static void greetings(){
		System.out.println("The elliptic curve is of the form Y^2=X^3+aX+b");
		System.out.println("Please input three numbers, the first is the coefficient a");
		System.out.println("The second is the coefficient b");
		System.out.println("The third is the prime p");
	}
	
	public static boolean checkValid(long x,long y,long a,long b,long p){
		long m=findXRemainder(x,a,b,p);
		long n=findYRemainder(y,p);
		return m==n;
	}
	
	public static long findXRemainder(long x,long a,long b,long p){
		long c=x%p;
		long c3=(c*c*c)%p;
		long c1=(a*c)%p;
		long c0=b%p;
		return (c1+c3+c0)%p;
	}
	
	public static long findYRemainder(long y,long p){
		long a=y%p;
		return (a*a)%p;
	}
	
	public static long[] addition(long x1,long x2,long y1,long y2,long a,long b,long p){
		long[] fin=new long[2];
		long l=0;
		if(x1==x2&&y1==-y2){
			fin[0]=0;
			fin[1]=0;
		}else{
			if(x1==x2&&y1==y2){
				 l=equall(x1,y1,a,b,p);				
			}else{
				l=nequall(x1,x2,y1,y2,a,b,p);
			}
			fin[0]=(l*l-x1-x2)%p;
			
			if(fin[0]<0){
				fin[0]+=p;
			}
			fin[1]=(l*(x1-fin[0])-y1)%p;
			if(fin[1]<0){
				fin[1]+=p;
			}
		}
		return fin;
	}
	
	public static long equall(long x1,long y1,long a,long b,long p){
	     long t=((x1%p)*(x1%p))%p;
	     long top=(3*t+a)%p;
	    
	     long bot=(2*y1)%p;
	    long inv=findInverse(bot,p);
	    return (inv*top+p)%p;
	}
	
	public static long nequall(long x1,long x2,long y1,long y2,long a,long b,long p){
		long top=((y1%p-y2%p)+p)%p;
		long bot=((x1%p-x2%p)+p)%p;
		long inv=findInverse(bot,p);
		return (inv*top+p)%p;
	}
	
	public static long findInverse(long bot,long p){
		long l=0;
		while((l*bot)%p!=1){
			l++;
		}
		return l;
	}
}
