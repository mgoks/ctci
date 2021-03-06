import java.lang.IllegalArgumentException;
import java.util.concurrent.ThreadLocalRandom;

public class BisectSquares {
    public static void main(String[] args) {
        // to generate squares randomly
        // double min = Double.parseDouble(args[0]);
        // double max = Double.parseDouble(args[1]);
        // Square square1 = createSquare(min, max);
        // Square square2 = createSquare(min, max);
        double[] c = new double[args.length];   // coordinates
        for (int i = 0; i < args.length; i++) {
            c[i] = Double.parseDouble(args[i]);
        }
        Square square1 = new Square(c[0], c[1], c[2], c[3]);
        Square square2 = new Square(c[4], c[5], c[6], c[7]);
        System.out.format("Square 1: %s%nSquare 2: %s%nLine %s cuts the above squares in half.%n", 
            square1, square2, calculateHalvingLine(square1, square2));
    }

    // Returns Line that cuts both squares in half
    static Line calculateHalvingLine(Square square1, Square square2) {
        if (square1 == null || square2 == null) {
            throw new IllegalArgumentException("Either both or one of the squares is null. Can't calculate halving line.");
        }
        /* get the line that connects center points of squares
         * this is the line that halves them */
        return new Line(square1.getCenterPoint(), square2.getCenterPoint());
    }

    private static Square createSquare(double min, double max) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double x1 = random.nextDouble(min, max);
        double x2 = random.nextDouble(x1, max);
        double y1 = random.nextDouble(min, max);
        double y2 = random.nextDouble(y1, max);
        return new Square(x1, x2, y1, y2);
    }


    // Helper classes

    static class Square {
        /* We assume edges of a square are parallel to y and x-axes, 
         * so we can represent a square with four lines parallel to axes. */
        double x1;
        double x2;
        double y1;
        double y2;

        public Square(double x1, double x2, double y1, double y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        Point getCenterPoint() {
            return new Point((x1 + x2)/2.0, (y1 + y2)/2.0);
        }

        @Override
        public String toString() {
            return String.format("[x1 = %f, x2 = %f, y1 = %f, y2 = %f]", x1, x2, y1, y2);
        }
    }


    static class Line {
        double slope;
        double y_intercept;
        double x_intercept;

        public Line(Point point1, Point point2) {
            double delta_x = point2.x - point1.x;
            double delta_y = point2.y - point1.y;
            if (delta_x == 0.0 && delta_y == 0.0) { // same point
                slope = 0.0;                        // any line passing through this point will do
                y_intercept = point1.y;
            } else if (delta_x == 0.0) {    // line parallel to y-axis
                slope = Double.NaN;         // slope is undefined
                x_intercept = point1.x; 
            } else {
                slope = delta_y / delta_x;
                y_intercept = point1.y - slope * point1.x;
            }
        }

        @Override
        public String toString() {
            if (Double.isNaN(slope)) {
                return "x = " + x_intercept;
            } else {
                return String.format("y = %fx + %f", slope, y_intercept);
            }
        }
    }


    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }
    }
}