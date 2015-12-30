/*
5.44. This exercise describes Pollard’s ρ factorization algorithm. It is particularly
good at factoring numbers N that have a prime factor p with the property that p
is considerably smaller than N/p. Later we will study an even faster, albeit more
complicated, factorization algorithm with this property that is based on the theory
of elliptic curves; see Sect. 6.6.
Let N be an integer that is not prime, and let
f : Z/NZ −→ Z/NZ
be a mixing function, for example f(x) = x2 +1 mod N. As in the abstract version
of Pollard’s ρ method (Theorem 5.48), let x0 = y0 be an initial value, and generate
sequences by setting xi+1 = f(xi) and yi+1 = f(f(yi)). At each step, also compute
the greatest common divisor
gi = gcd|xi − yi|,N	.
(b) Program Pollard’s ρ algorithm with f(x) = x2 +1 and x0 = y0 = 0, and use it
to factor the following numbers. In each case, give the smallest value of k such
that gk is a nontrivial factor of N and print the ratio k/
√
N.
(i) N = 2201. (ii) N = 9409613. (iii) N = 1782886219.
*/

import java.util.ArrayList;


public class Pollar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        factorN(2201);
        factorN(9409613);
        factorN(1782886219);
//		factorN(77);

	}
    public static long fx(long x,long n){
     	return (x*x+1)%n;
//    	return (x*x+2)%n;
//    	return (x*x)%n;
//    	return (x*x-2)%n;
    }
    
    public static long fy(long y,long n){
    	return ((y*y+1)*(y*y+1)+1)%n;
//    	return ((y*y+2)*(y*y+2)+2)%n;
//    	return (y*y*y*y)%n;
//    	return  ((y*y-2)*(y*y-2)-2)%n;
    }
	public static void factorN(long n){
		long x=0;
		long y=0;
		long g=gcd(fy(y,n)-fx(x,n),n);
		long count=1;
		while(g<=1){
			x=fx(x,n);
			y=fy(y,n);
			g=gcd(fy(y,n)-fx(x,n),n);
			count++;
		}
		System.out.println("count "+count);
		System.out.println("g "+g);
		System.out.println("ratio"+ count/Math.sqrt(n));
	}
	
	public static long gcd(long a,long b){
		long ma=(long)Math.max(a, b);
		long mi=(long)Math.min(a, b);
		while(ma%mi!=0){
			long temp=mi;
			mi=ma%mi;
			ma=temp;
		}
		return mi;
	}
}
