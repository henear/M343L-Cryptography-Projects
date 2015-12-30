// Write a program that implements the Weil 
import java.util.Scanner;


public class WeilPairing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        //prompt user to input ellipticCurve
        long[] ellipCurve=ecCoef(sc);
        System.out.println("Please input coordinate for p");
        long[] ppoint=getPoint(sc);
        System.out.println("Please input coordinate for q");
        long[] qpoint=getPoint(sc);
        System.out.println("Please input the value of the prime ");
        long prime=sc.nextLong();
        long lambda=findLambda(ppoint,qpoint,prime,ellipCurve[0]);
        Long m=getM(sc);
        String mstring=Long.toBinaryString(m);
        long sy=findsy(ellipCurve,0,prime);
        long[] spoint={0,sy};
        long[] defaul={0,0};
        long[] nspoint={0,-sy};
        long fpqs=computeF(ppoint,mstring,prime,lambda,qpoint,spoint);
        long fps=computeF(ppoint,mstring,prime,lambda,qpoint,defaul);
        long fqps=computeF(qpoint,mstring,prime,lambda,ppoint,nspoint);
        long fqs=computeF(qpoint,mstring,prime,lambda,nspoint,defaul);
        long top=fpqs*findInverse(fps,prime);
        long bottom=fqps*findInverse(fqs,prime);
        long result=top*findInverse(bottom,prime);
        System.out.println("result is"+result);
	}

	public static long[] ecCoef(Scanner sc){
		System.out.println("The elliptic curve has the form y^2=x^3+ax+b");
		System.out.println("Please input two numbers");
		System.out.println("The first number is coefficient a");
		System.out.println("The second number is coefficient b");
		long[] ret=new long[2];
		ret[0]=sc.nextLong();
		ret[1]=sc.nextLong();
		return ret;
	}
	
	public static long[] getPoint(Scanner sc){
		long[] point=new long[2];
		point[0]=sc.nextLong();
		point[1]=sc.nextLong();
		return point;
	}
	
	public static long findLambda(long[] p,long[] q,long prime,long a){
		if(p[0]==q[0]){
			if(p[1]==q[1]){
				return (3*p[0]*p[0]+a)*findInverse(2*p[1],prime);
			}else{
			return Long.MAX_VALUE;
			}
		}else{
			return (p[1]-q[1])*findInverse((p[0]-q[0]),prime);
		}
	}
	
	public static long findInverse(long test,long p){
		long result=0;
		boolean b=true;
		for(int i=0;i<p&&b;i++){
			if(i*test%p==1){
				result=i;
				b=false;
			}
		}
		return result;
	}
		
	public static long getM(Scanner sc){
		System.out.println("Please input number for m");
		return sc.nextLong();
	}

	public static long computeF(long[] r,String mstring,long prime,long lambda,long[] qpoint,long[] spoint){
		long[] T=r;
		long f=1;
		int mlength=mstring.length();
		for(int i=mlength-2;i>=0;i--){
			f=f*f*gt(T,T,lambda,qpoint,spoint,prime);
			T[0]=(T[0]+T[0])%prime;
			T[1]=(T[1]+T[1])%prime;
			if(mstring.charAt(i)==1){
				f=f*gt(T,r,lambda,qpoint,spoint,prime);
				T[0]+=r[0];
				T[1]+=r[1];
			}
		}
		return f;
	}

    public static long gt(long[] a,long[] b,long lambda,long[] qpoint,long[] spoint,long prime){
        long x=qpoint[0]+spoint[0];
        long y=qpoint[1]+spoint[1];
        long top=y-a[1]-lambda*(x-a[0]);
        long bot=x+a[0]+qpoint[0]-lambda*lambda;
        long result=top*findInverse(bot,prime);
        return result;
    }
    
    public static long findsy(long[] ecurve,long x,long prime){
    	long remainder=(x*x*x+ecurve[0]*x+ecurve[1])%prime;
    	long y=0;
    	while(y*y%prime!=remainder){
    		y++;
    	}
    	return y;
    }
}


