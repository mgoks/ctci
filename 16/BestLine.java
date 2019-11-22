import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class BestLine {
    static Line findBestLine(Point[] points) {
        if (points == null || points.length == 0) return null;

        // assuming duplicate points will be ignored, remove duplicates 
        Set<Point> pointSet = new HashSet<>(Arrays.asList(points));

        if (pointSet.size() == 1) return new Line(points[0]);

        /* Create all lines that can be drawn for any two points,
         * put them in a map. Increment integer value if the line
         * is already created. */
        Map<Line, Set<Point>> pointCount = new HashMap<>();
        for (Point p : pointSet) {
            for (Point q : pointSet) {
                if (p.equals(q)) {
                    continue;
                }
                Line line = new Line(points[i]. points[j]);
                if (!pointCount.containsKey(line)) 
                    pointCount.put(line, new HashSet<>());
                pointCount.get(line).add(p);
                pointCount.get(line).add(q);
            }
        }

        Line bestLine = null;
        int bestLineCount = 0;
        for (Map.Entry<Line, Set<Point>> line_set : pointCount.entrySet()) {
            if (line_set.getValue().size() > bestLineCount) {
                bestLine = line_set.getKey();
                bestLineCount = line_set.getValue().size();
            }
        }
        return bestLineCount;
    }
}