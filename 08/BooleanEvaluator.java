public class BooleanEvaluator {
	static int countEval(String exp, boolean result) {
		if (exp == null || exp.isEmpty())
			return Integer.MIN_VALUE;
		else if (exp.length() == 1) {
			boolean value = exp.equals("0")? false : true;
			return value == result? 1 : 0;
		} else {
			int n = 0;
			for (int i = 1; i < exp.length(); i += 2) {
				String left = exp.substring(0,i);
				char operator = exp.charAt(i);
				String right = exp.substring(i+1);
				int leftTrue = countEval(left, true),
					rightTrue = countEval(right, true),
					leftFalse = countEval(left, false),
					rightFalse = countEval(right, false);

				if (result == true) {
					if (operator == '&') 
						n += leftTrue * rightTrue;
					else if (operator == '|') 
						n += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
					else 
						n += leftTrue * rightFalse + leftFalse * rightTrue;
				} else {	// result == false
					if (operator == '&') 
						n += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
					else if (operator == '|') 
						n += leftFalse * rightFalse;
					else 
						n += leftFalse * rightFalse + leftTrue * rightTrue;
				}
			}
			return n;
		}
	}

	public static void main(String[] args) {
		System.out.println(countEval(args[0], Boolean.parseBoolean(args[1])));
	}
}