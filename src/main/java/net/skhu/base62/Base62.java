package net.skhu.base62;
//기본적으로 64진법을 사용하는게 2진법변환도 쉽고 합리적이라 생각했으나,A-Z, a-z , 0 -9 를 합한 갯수가 62 개이고, 특수문자를 사용하는 url 을 만드는것이 바람직해 보이지 않아서 62진법 변환을 사용

//공부해보니 이미 다른shortening 서비스도 62진법을 사용하는 것으로 보이고 이것이 대중적인 방법같아 채택함.

public class Base62 {
	static final char[] BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public static String encode(long value) {// 62진법으로 변환하는 코드, 각각의 나머지 값이
											// 62진법변환숫자의 첫번째 값이 된다.
		final StringBuilder sb = new StringBuilder();
		do {
			long i = value % 62;
			sb.append(BASE62[(int) i]);
			value /= 62;
		} while (value > 0);
//		String s = sb.toString();// 만약 urlencrypt의 빈공간을 0 으로 채우고 싶다면 이 조건문을 사용한다 
//		if(s.length()<8){
//			for(int i =0; i<8-s.length();i++)
//				sb.insert(0, "0");
//		}else{
//			return "________";
//		}
		if(sb.toString().length()>8){
			return "________";
		}
		return sb.toString();
	}

//	public static long decode(String value) {// 각각의 자릿수에 숫자에 차수만큼 62를 곱하고 더하는 과정
//		long result = 0;
//		long pcounter = 1;
//		for (int i = 0; i < value.length(); i++) {
//			int digit = new String(BASE62).indexOf(value.charAt(i));
//			result += digit * pcounter;
//			pcounter *= 62;
//
//		}
//		return result;
//	}
}