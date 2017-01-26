// Write a program that solve the discrete log problem using Shanks' algorithm.  
// The program should take g, p, and h as input and output an x such that g^x = h in Z/p.

import java.util.Arrays;
import java.util.Scanner;
public class Problemset32 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        System.out.println("Input 3 integers, the first one is g, second is p and third is h");
        int g=sc.nextInt();
        int p=sc.nextInt();
        int h=sc.nextInt();
        int n=(int)Math.ceil(Math.sqrt(p-1));
        int gnmodp=findGnmodp(g, n, p);
        int u=findInverse(gnmodp,p);
        int[] bStep=babyStep(g,n,p);
        int[] gStep=giantStep(u,n,p,h);
        int[] c=findSame(bStep,gStep,n);
        int x=c[0]+n*c[1];
        
        System.out.println("x= "+ x);
        sc.close();
	}

	public static int findGnmodp(int g,int order,int p){
		return findRemainder(g, order, p);
	}
	
	public static int findRemainder(int base,int expo,int divisor){
		int prod=1;
		for(int i=0;i<expo;i++){
			prod*=base;
			if(prod>divisor){
				prod=prod%divisor;
			}
		}
		return prod;
	}
	
	public static int findInverse(int gnmodp,int p){
		int n=0;
		for(int i=1;i<p;i++){
			if((gnmodp*i)%p==1){
				n=i;
			}
		}
		return n;
	}
	
	public static int[] babyStep(int g,int n,int p){
		int[] bStep=new int[n];
		for(int i=0;i<n;i++){
			bStep[i]=findRemainder(g,i,p);
		}
		return bStep;
	}
	
	public static int[] giantStep(int u,int n,int p,int h){
		int[] gStep=new int[n];
		for(int i=0;i<n;i++){
			gStep[i]=findRemainder(u,i,p);
			gStep[i]=(gStep[i]*h)%p;
		}
		return gStep;
	}
	
	public static int[] findSame(int[] b,int [] g,int n){
		int[] result=new int[2];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(b[i]==g[j]){
					result[0]=i;
					result[1]=j;
					break;
				}
			}
		}
		return result;
	}
}
