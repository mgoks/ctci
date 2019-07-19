public class PairwiseSwap {
    
    static int swapOddEven(int i) {
        return (i & 0x55555555)<<1 | (i & 0xaaaaaaaa)>>>1;
    }

    public static void main(String[] args) {
        int i = Integer.parseInt(args[0]);
        int swapped = swapOddEven(i);
        System.out.println(String.format("input : %32s", Integer.toBinaryString(i)));
        System.out.println(String.format("output: %32s", Integer.toBinaryString(swapped)));
    }

}