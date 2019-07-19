import java.lang.StringBuilder;

public class StringCompressor {
	static String compress(String s) {
		int k = 0;
		StringBuilder compressed = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			k++;
			if (i+1 == s.length() || s.charAt(i) != s.charAt(i+1)) {
				compressed.append(s.charAt(i));
				compressed.append(k);
				k = 0;
			}
		}
		return compressed.length() < s.length()? compressed.toString() : s;
	}

	public static void main(String[] args) {
		System.out.println(compress(args[0]));
	}
}