package pangpang.controller.member.암호화.test;

public class 초깃값_설정 {
	public static void main(String[] args) {

		/*def get_dp_32bit(a):
		    a = math.sqrt(a)
		    a = a-int(a)
		    a = a * (2**32)
		    return hex(int(a))


		if __name__ =="__main__":
		    H_pn_list = (2,3,5,7,11,13,17,19)

		    for i in H_pn_list:
		        print(get_dp_32bit(i))*/
		
		//double[] H_list = {2,3,5,7,11,13,17,19};	
		
		//for(int i=0;i<H_list.length;i++) {
			
		//}System.out.println(get_32bit(2));
		
		//System.out.printf( "%x \n" , get_32bit(2)  );
		double a = Math.sqrt(5);
		double b = a-(int)a;
		
		String binaryString = Long.toBinaryString(Double.doubleToRawLongBits(b));
   	 	System.out.println(binaryString);
		
		
	} 
	
	public static String get_32bit(double a) {
		double a2 = Math.sqrt(a);
		System.out.println(a2);
		a2 = a2-(int)a2;
		System.out.println( a2);
		System.out.println(Double.doubleToRawLongBits(a2));
		System.out.println(Double.doubleToRawLongBits(a2)<<32);
		a2 = Double.doubleToRawLongBits(a2)<<32;
		System.out.println( a2 );
		System.out.println( Double.doubleToLongBits( a2 ) );
		
		System.out.println( "aa: " +  Double.toHexString(a2) );
		/*
		 * String a3 = Long.toBinaryString(Double.doubleToRawLongBits(a2));
		 * System.out.println(a3); String a4 = a3.substring(0,32);
		 * System.out.println(a4);
		 */
		return Integer.toHexString((int)a2);
	}
	
	public static String shiftDouble(Object o, double shift, String suffix) {
        Double d = parseDouble(o);
        d = d + shift;
        return (String.format("%.1f", d) + suffix);
    }

    /**
     * @param o A string representation of a double value.
     * @return The parsed value or 0.0 if the string couldn't be parsed.
     */
    public static Double parseDouble(Object o) {
        String s = o.toString();
        try {
            if (s.length() >= 1) {
                return (Double.parseDouble(s.substring(0, s.length() - 1)));
            } else {
                return (0.0);
            }
        } catch (NumberFormatException e) {
            return (0.0);
        }
    }
	
	
	
}
