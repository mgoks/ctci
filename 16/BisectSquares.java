import java.lang.IllegalArgumentException;
import java.util.concurrent.ThreadLocalRandom;

public class BisectSquares {
    public static void main(String[] args) {
        double[] c = new double[8]; // coordinates
        for (int i = 0; i < args.length; i++) {
            c[i] = Double.parseDouble(args[i]);
        }
        double min = Double.parseDouble(args[0]);
        double max = Double.parseDouble(args[1]);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Square square1 = new Square(new Point(random.nextDouble(min, max), random.nextDouble(min, max)), 
                                    new Point(random.nextDouble(min, max), random.nextDouble(min, max)));
        Square square2 = new Square(new Point(random.nextDouble(min, max), random.nextDouble(min, max)), 
                                    new Point(random.nextDouble(min, max), random.nextDouble(min, max)));
        Line halvingLine = calculateHalvingLine(square1, square2);
        System.out.format("Line %s cuts the squares %s and %s in half.%n", halvingLine, square1, square2);
    }

    // Returns Line that cuts both squares in half
    static Line calculateHalvingLine(Square square1, Square square2) {
        if (square1 == null || square2 == null) {
            System.err.println("Either both or one of the squares is null. Can't calculate halving line.");
            return null;
        }
        Point center1 = square1.getCenterPoint();
        Point center2 = square2.getCenterPoint();
        Square leftSquare;
        Square rightSquare;
        
        return new Line(square1.getCenterPoint(), square2.getCenterPoint());
    }

    static class Square {
        Point bottomLeft;
        Point topRight;

        public Square(Point bottomLeft, Point topRight) {
            if (bottomLeft.equals(topRight)) {
                throw new IllegalArgumentException("Points must be different to constitute a square.");
            }
            this.bottomLeft = bottomLeft;
            this.topRight = topRight;
        }

        Point getCenterPoint() {
            return new Point((bottomLeft.x + topRight.x) / 2.0,
                             (bottomLeft.y + topRight.y) / 2.0);
        }

        @Override
        public String toString() {
            return String.format("[%s %s]", bottomLeft, topRight);
        }
    }

    static class Line {
        Point point1;
        Point point2;

        public Line(Point p1, Point p2) {
            point1 = p1;
            if (p1.equals(p2)) {
                // There are infinitely many line passing through this point.
                // Let's pick one.
                point2 = new Point(0, 0);
            } else {
                point2 = p2;
            }
        }

        @Override
        public String toString() {
            return String.format("%s--%s", point1, point2);
        }
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point other) {
            return x == other.x && y == other.y;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }
    }
}