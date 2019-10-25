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
        if (end1.x < start1.x) swap(end1, start1);
        if (end2.x < start2.x) swap(end2, start2);
        if (start2.x < start1.x) {
            swap(start2, start1);
            swap(end2, end1);
        }

        Line line1 = new Line(start1, end1), 
             line2 = new Line(start2, end2);
        if (line1.slope == line2.slope) {   // if lines are parallel
            // they only intercept only if they are the same line and segments overlap
            if (line1.yIntercept == line2.yIntercept && start2.isOnLineSegment(start1, end1)) {
                return start2;
            }
            return null; 
        }

        // if we get here, we know that lines are not parallel so they must intersect
        Point p = line1.getIntersection(line2);
        if (p.isOnLineSegment(start1, end1) && p.isOnLineSegment(start2, end2)) {
            return p;
        }
        return null;
    }

    private static void swap(Point point1, Point point2) {
        Point temp = new Point(point1.x, point1.y); // temp = point1
        point1.x = point2.x;
        point1.y = point2.y;
        point2.x = temp.x;
        point2.y = temp.y;
    }


    static class Line {
        double slope, yIntercept;

        public Line(Point start, Point end) {
            double deltaY = end.y - start.y, 
                   deltaX = end.x - start.x;
            slope = deltaY / deltaX;
            yIntercept = start.y - slope * start.x; // y - mx = b
        }

        Point getIntersection(Line other) {
            /* mx + b = nx + c
               mx - nx = c - b
               x(m - n) = c - b
               x = (c - b)/(m - n) */
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
            return isBetween(start.x, x, end.x) && isBetween(start.y, y, end.y);
        }

        private boolean isBetween(double start, double mid, double end) {
            if (end < start) {
                return end <= mid && mid <= start;
            }
            return start <= mid && mid <= end;
        }
    }
}