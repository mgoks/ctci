public class DrawLine {
    
    static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int height  = screen.length / width;
        int row     = height - y - 1;
        int s       = row * width + x1/8; // start index
        int e       = row * width + x2/8; // end index
        byte start  = (byte)  (0xff >>> x1%8);
        byte end    = (byte) ~(0xff >>> (x2%8 + 1)); // inclusive
        if (y >= height)
            System.err.println("error: y-coordinate is larger than height of screen");
        else if (s == e)
            screen[s] |= (byte) (start & end);
        else {
            screen[s] |= start;
            for (int i = s+1; i < e; i++) screen[i] = (byte) 0xff;
            screen[e] |= end;
        }
    }

    private static String toBinaryString(byte b) {
        char[] bits = new char[Byte.SIZE];
        for (int i = bits.length - 1; i >= 0; i--, b >>>= 1)
            bits[i] = (b & 1) == 1? '1' : '0';
        return new String(bits);
    }

    private static void print(byte[] screen, int width) {
        for (int i = 0; i < screen.length; i++) {
            if (i % width == 0) System.out.println("");
            System.out.print(toBinaryString(screen[i]) + "|");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int length, width, x1, x2, y;
        length = Integer.parseInt(args[0]);
        width = Integer.parseInt(args[1]);
        x1 = Integer.parseInt(args[2]);
        x2 = Integer.parseInt(args[3]);
        y = Integer.parseInt(args[4]);
        byte[] screen = new byte[length];
        drawLine(screen, width, x1, x2, y);
        print(screen, width);
    }

}