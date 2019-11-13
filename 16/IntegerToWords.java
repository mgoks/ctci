public class IntegerToWords {

    private static final String[] FIRST_TWENTY_NUMBERS = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] TENS = {
        "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] LARGE_NUMBERS = {"", "Thousand,", "Million,", "Billion,"};

    public static void main(String[] args) {
        System.out.println(intToWords(Integer.parseInt(args[0])));
    }

    static String intToWords(int i) {
        if (i == 0) return "Zero";

        String negative = i < 0? "Negative " : "";
        i = Math.abs(i);

        String words = "";
        for (int j = 0; i != 0; j++) {
            // get words representation of last three digits of i
            words = hundredToWords(i % 1000) + " " + LARGE_NUMBERS[j] + " " + words;
            i /= 1000;
        }
        return negative + words;
    }

    static String hundredToWords(int i) {
        if (i >= 1000) {
            System.err.println("hundredToWords(int) does not take argument >= 1000.");
        }

        String words = "";
        if (i >= 100) {
            words += FIRST_TWENTY_NUMBERS[i/100] + " " + "Hundred";
            i %= 100;
        }
        if (i < 20) return words + " " + FIRST_TWENTY_NUMBERS[i];
        else return words + " " + TENS[i/10] + " " + FIRST_TWENTY_NUMBERS[i%10];
    }
}