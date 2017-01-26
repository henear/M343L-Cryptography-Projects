// This java code implements 4 different algorithms, each exact function
// can be found at the comment in the main function

import java.util.Scanner;

public class Problemset1 {
	public static void main(String[] args) {
        // Find the greatest common divisor
		Scanner sc=new Scanner(System.in);
		int[] gcd=ginput(sc);// ginput is the abbrebviated form of the input for the greatest common divisor problem
		System.out.println("The greatest common divisor of the two numbers you plugged in is "+ gcd(gcd[0],gcd[1]));
		// Write a program to compute the addition and multiplication in the ring Z/m
		//your program should take m as a parameter.
		int[] maring=ringinput(sc);
		System.out.println("The result of the remainder is "+ maring(maring));
		//fast exponentiation algorithm
		//The first element in the parenthesis refers to the exponents
		//The second element in the parenthesis refers to the base
		int[] fea=finput(sc); //finput is the abbrebviated form of the input for the fast exponentiation algorithm problem
		System.out.println("The result of the exponents we calculated is "+ fea(fea[0],fea[1],fea[2]));
		// A program to decode a string encrypted using a shift cipher (without
		// knowledge of the secret key).
		String s=cinput(sc);// cinput is the abbrebviated form of the input for the cipher problem
		String encrypt=decrypt(s,sc);
		System.out.println("The encrypted version of the string is "+ encrypt);
	}
	
	//let the user input the numbers they want to find the greatest common divisor
	public static int[] ginput(Scanner sc){
		int[] gcd=new int[2];
		System.out.print("Please input the two integers you want to find the greatest common divisor:");
		gcd[0]=sc.nextInt();
		gcd[1]=sc.nextInt();
		return gcd;
	}
	
	//calculate the g.c.d by using the eucludiean method.
    public static int gcd(int a,int b){
    	int c=Math.abs(a);
    	int d=Math.abs(b);
    	int e=Math.max(c, d);
    	int f=Math.min(c, d);
    	while(e%f!=0){
    		int g=e/f;
    		int temp=f;
    		f=e-g*f;
    		e=temp;
    	}
    	return f;
    }
    
    public static int[] ringinput(Scanner sc){
    System.out.println("Please input the the integers you want to be the divisor, the second and the third the elements you want to operate and the fourth element be 1 for addition or 2 for multiplication");
    int[] a=new int[4];
    for(int i=0;i<4;i++){
    	a[i]=sc.nextInt();
    }
    return a;
    }
    
    public static int  maring(int[] maring){
    	int r1=maring[2]%maring[0];
    	int r2=maring[1]%maring[0];
    	if(maring[3]==1){
    		return (r1+r2)%maring[0];
    	}else return (r1*r2)%maring[0];
    }
    //let the user input the numbers they want to find the exponents.
    public static int[] finput(Scanner sc){
    	int[] fea=new int[3];
    	System.out.print("Please input three integers, the first serves as the base the second one is the exponents, and the third one be the divisor ");
    	fea[0]=sc.nextInt();
    	fea[1]=sc.nextInt();
    	fea[2]=sc.nextInt();
    	return fea;
    }
    
    //the "main method" for whole
    public static int fea(int base,int expo,int divisor){
    	int a=Math.abs(expo);
    	String str=binary(a); // convert the exponents
    	int p=1;
    	for(int i=0;i<str.length();i++){
    		int par=partial(i,str.charAt(i),base,str.length(),divisor);
    		p*=par;
    	}    	
    	return p%divisor;    	
    }	
    
    //convert the exponents to binary system
    public static String binary(int a){
    	String s="";
    	while(a!=0){
    		int d=a%2;
    		String t=d+"";  		
    		s=t+s;
    		a=a/2;
    	}  	
    	return s;
    }
    
    //calculate the exponents given by every part
    public static int partial(int i,char j,int b,int l,int divisor){
    	int par=b;
    	if(j=='0'){
    		return 1;
    	}else {
    		for(int a=0;a<l-i-1;a++){
    		     par=par*par;
    		}
    		return par%divisor;
    	}
    }
    
    //Prompt the user to input the materials they want to decrypt.
    public static String cinput(Scanner sc){
    	System.out.println("Input the materials you want to decrypt");
    	return sc.nextLine();
    }
    
    //Decrypt the string entered by user
    public static String decrypt(String s,Scanner sc){
    System.out.println("Are you satisfied with the current decryption result?");
    System.out.println("If yes, press 'y' or 'Y', if no, press other keys.");
    String judge=sc.next();
    while(!(judge.toLowerCase().equals("y"))){
    	 String decrypt="";
    	for(int i=0;i<s.length();i++){
    		if(s.charAt(i)==' '){
    			decrypt+=' ';
    		}
    		else if(s.charAt(i)!='z'&&s.charAt(i)!='Z'){
    		char temp=(char)((int)(s.charAt(i)+1));
    		decrypt+=temp;	
    		}else if(s.charAt(i)=='z'){
    			decrypt+='a';
    		}else decrypt+='A';
    	}
    	System.out.println(decrypt);
    	s=decrypt;
    	System.out.println("Are you satisfied with the current decryption result?");
    	System.out.println("If yes, press 'y' or 'Y', if no, press other keys.");
    	judge=sc.next();
    }
    return s;
    }
}
