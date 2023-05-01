package pangpang.controller.member.암호화.test;

public class 압축 {
	// 초기값 H 7개 랜덤값
	private static final int[] ChainVar = {
			0x7a6e4943, 
			0x49f5d96a, 
			0xa9e54be4, 
			0xe9ee22de, 
			0xe11bcaee, 
			0x92b1f31f, 
			0x86071c3e, 
			0x2523b2c6
	};
	
	// 초기값 K 63개 랜덤값
	private static final int[] k = {
			0xcc8f300d, 0xc3cab38c, 0xef75efc3, 0x72d6e20f, 0x7e634ede, 0x42a2c2ba, 
			0x303ac401, 0x9d2d9d90, 0x1f5a2fb4, 0x7551ea72, 0x23fc1625, 0xba4e1973, 
			0xb0365253, 0xf7da41b4, 0xbbf63c01, 0xd5c82f9d, 0x869264db, 0xa2ec9b52, 
			0xdd330ff5, 0x1dd71423, 0x6cff24f2, 0x720dfd0b, 0x6053fbe3, 0x1e9a19a1, 
			0x89c546c6, 0x468501f3, 0x830c5780, 0xfd68a533, 0x29cb2916, 0x677798f3, 
			0x687376a3, 0xd5137f39, 0x2d5aa859, 0x8c136ed1, 0x0c063f9c, 0xe0fd677f, 
			0x3a80adb5, 0xcd582b9b, 0xe9378360, 0xcd6aa0e6, 0x264027cf, 0x0770e717, 
			0xf24b9392, 0x9dada3eb, 0xe68cc640, 0x02926207, 0x80b8c24d, 0xe9a22665, 
			0x237a7ea4, 0xd144d6aa, 0x37e86165, 0x2ec35cf1, 0x77d941c3, 0xdf542c31, 
			0x46cc22f0, 0x41d8daf7, 0x4b41f5d8, 0xd9f89b8a, 0xa3642b7b, 0x27378089, 
			0x724cd749, 0x0d03ca03, 0xdde63401, 0x1d26f9e9
	};
	
	public static void main(String[] args) {
		int[] w = {
				1633837921, 959983925, 808923492, 878917478, 878863408, 825450802, 962672690, 862151777, 
				1681207601, 1717645363, 1630744576, 0, 0, 0, 0, 344, 
				685493150, -1657674376, -735361406, 875769347, -295697127, 1364376756, 1450160931, -2011513996, 
				1465102013, -78462335, 939733931, -295773300, 1359067265, 1449873525, 599096939, -450719725, 
				679632699, -805229813, -227949980, -2095565825, 309188946, 361967515, -2063166623, 1053044413, 
				647581776, 501071016, 992982726, -785019079, -926490001, 1762742673, 1593690197, 1932280794, 
				-1644713235, -1290684922, -1272878806, 470410744, 2062276217, 858408439, 2769491, 932159511, 
				2142829681, 1554377774, -1285666426, -1860315609, 2003989393, 1016538849, -1196818834, 1577310672
		};
		
		 Compression(w);
		
	
	}
	
	public static void Compression(int[] w) {
		int a = ChainVar[0];
		int b = ChainVar[1];
		int c = ChainVar[2];
		int d = ChainVar[3];
		int e = ChainVar[4];
		int f = ChainVar[5];
		int g = ChainVar[6];
		int h = ChainVar[7];
		System.out.println("a :"+a);	
		System.out.println("b :"+b);		
		System.out.println("c :"+c);		
		System.out.println("d :"+d);		
		System.out.println("e :"+e);		
		System.out.println("f :"+f);		
		System.out.println("g :"+g);
		System.out.println("h :"+h);
		
		for(int i = 0; i<=63 ; i++) {
			int sl = rightRotate(e, 6)^rightRotate(e, 11)^rightRotate(e, 25);
			int ch = (e&f)^((~e)&g);
			int temp1 = h+sl+ch+k[i]+w[i];
			int s0 = rightRotate(a, 2)^rightRotate(a, 13)^rightRotate(a, 22);
			int maj = (a&b)^(a&c)^(b&c);
			int temp2 = s0+maj;
			
			h=g;
			g=f;
			f=e;
			e=d+temp1;
			d=c;
			c=b;
			b=a;
			a=temp1+temp2;	
		}
		
		System.out.println("a :"+a);	
		System.out.println("b :"+b);		
		System.out.println("c :"+c);		
		System.out.println("d :"+d);		
		System.out.println("e :"+e);		
		System.out.println("f :"+f);		
		System.out.println("g :"+g);
		System.out.println("h :"+h);
	}
	//비트의 길이
	static final int INT_BITS = 32;
	
	//왼쪽 회전
	static int leftRotate(int n, int d) {
	    return (n << d) | (n >> (INT_BITS - d));
	}
	
	//오른쪽 회전
	static int rightRotate(int n, int d) {
	    return (n >> d) | (n << (INT_BITS - d));
	}
}
