public class LineIntersection {
    public static void main(String[] args) {
        Point start1, end1, start2, end2;
        start1 = new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
        end1 = new Point(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
        start2 = new Point(Double.parseDouble(args[4]), Double.parseDouble(args[5]));
        end2 = new Point(Double.parseDouble(args[6]), Double.parseDouble(args[7]));
        Point p = getIntersection(start1, end1, start2, end2);
        if (p == null) {
            System.out.println("Lines do not intersect.");
        } else {            
            System.out.format("Intersection point is (%f, %f)%n", p.x, p.y);
        }
    }

    static Point getIntersection(Point start1, Point end1, Point start2, Point end2) {
        if (end1.x < start1.x) {
            swap(end1, start1);
        }
        if (end2.x < start2.x) {
            swap(end2, start2);
        }
        if (start2.x < start1.x) {
            swap(start2, start1);
            swap(end2, end1);
        }

        Line line1 = new Line(start1, end1), 
             line2 = new Line(start2, end2);
        if (line1.equals(line2)) {
            return anOverlappingPoint(start1, end1, start2, end2);
        }
        if (!line1.isParallel(line2)) {
            Point p = line1.getIntersection(line2);
            if (p.isOnLineSegment(start1, end1) && p.isOnLineSegment(start2, end2)) {
                return p;
            }
        }
        return null;
    }

    private static void swap(Point p1, Point p2) {
        Point temp = p1;
        p1 = p2;
        p2 = temp;
    }

    private static Point anOverlappingPoint(Point start1, Point end1, Point start2, Point end2) {
        // We now all 4 points are on the same line, so we can just compare the x-coordiantes.
        if (start1.x <= start2.x && start2.x <= end1.x) {
            return start2;
        } else {    // If line segments do not overlap, return null.
            return null;
        }
    }

    static class Line {
        double slope, yIntercept;

        public Line(Point start, Point end) {
            double deltaY = end.y - start.y, 
                   deltaX = end.x - start.x;
            slope = deltaY / deltaX;

            // y = mx + b
            // y - mx = b
            yIntercept = start.y - slope * start.x;
        }

        boolean isParallel(Line other) {
            return slope == other.slope;
        }

        boolean equals(Line other) {
            return isParallel(other) && yIntercept == other.yIntercept;
        }

        Point getIntersection(Line other) {
            /* mx + b = nx + c
               mx - nx = c - b
               x(m - n) = c - b
               x = (c - b)/(m - n) */

            // If slopes are the same, lines are parallel -- no intersection
            if (slope == other.slope) {
                return null;
            }
            double x = (other.yIntercept - yIntercept) / (slope - other.slope);
            double y = slope * x - yIntercept;
            return new Point(x,y);
        }
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        boolean isOnLineSegment(Point start, Point end) {
            return start.x <= x && x <= end.x && start.y <= y && y <= end.y;
        }
    }
}