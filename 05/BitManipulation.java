public class BitManipulation {
    
    static int getBit(int N, int p) {
        return (1 << p & N) > 0? 1 : 0;
    }

    static int clearBit(int N, int p) {
        return N & ~(1 << p);
    }

    static String bin(int integer) {
        String bin = "";
        for (int i = 31; i >= 0; i--)
            bin += getBit(integer, i);
        return bin;
    }

    public static void main(String[] args) {
        // for (int integer = 0; integer <= 32; integer++) {
        //     System.out.print(String.valueOf(integer) + " is ");
        //     for (int i = 7; i >= 0; i--)
        //         System.out.print(getBit(integer, i));
        //     System.out.println(" in binary.");
        // }
        int integer = Integer.parseInt(args[0]);
        System.out.println(String.format(" bin(%d) = %s", integer, bin(integer)));
        System.out.println(String.format("bin(%d) = %s", integer, bin(integer)));
    }

}