import java.util.Set;
import java.util.HashSet;

public class Parens {
	static void printParens(int n) {
		if (n < 0) return;
		else System.out.println(parens(n));
	}
	static Set<String> parens(int n) {
		Set<String> parenSet = new HashSet<>();
		if (n == 0) parenSet.add("");
		else {
			for (String paren : parens(n-1)) {
				parenSet.add(paren + "()");
				parenSet.add("()" + paren);
				parenSet.add("(" + paren + ")");
			}
		}
		return parenSet;
	}

	public static void main(String[] args) {
		printParens(Integer.parseInt(args[0]));
	}
}