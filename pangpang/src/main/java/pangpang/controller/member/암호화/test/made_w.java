package pangpang.controller.member.암호화.test;

import java.util.Arrays;

public class made_w {
	public static void main(String[] args) {
		int[] arr = {
				1633837921, 959983925, 808923492, 878917478, 
				878863408, 825450802, 962672690, 862151777, 
				1681207601, 1717645363, 1630744576, 0, 
				0, 0, 0, 344
		};
		
		int[] w = new int[64];
		
		for(int i=0;i<=15;i++) {
			w[i]=arr[i];
		}
		for(int i=16 ; i<=63 ; i++) {
			//s0 = (w[i-15] rightrotate 7) xor (w[i-15] rightrotate 18) xor (w[i-15] rightshift 3)
			//s1 = (w[i- 2] rightrotate 17) xor (w[i- 2] rightrotate 19) xor (w[i- 2] rightshift 10)
			//w[i] = w[i-16] + s0 + w[i-7] + s1
			int s0 = (rightRotate(w[i-15], 7)) ^ (rightRotate(w[i-15], 18)) ^ (w[i-15]>>3);
			int s1 = (rightRotate(w[i-2], 17)) ^ (rightRotate(w[i-2], 19)) ^ (w[i-2]>>10);
			w[i]= w[i-16] + s0 + w[i-7] + s1;
		}
		System.out.println(Arrays.toString(w));
		
	}
	
	static final int INT_BITS = 32;
	 
	static int leftRotate(int n, int d) {
	    return (n << d) | (n >> (INT_BITS - d));
	}
	 
	/*Function to right rotate n by d bits*/
	static int rightRotate(int n, int d) {
	    return (n >> d) | (n << (INT_BITS - d));
	}
}
