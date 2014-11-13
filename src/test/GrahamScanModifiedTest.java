import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class GrahamScanModifiedTest extends TestCase {
    GrahamScanModified scan = new GrahamScanModified();
    List<Pair<Integer, Integer>> points = new ArrayList<Pair<Integer, Integer>>();
    List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
    List<Pair<Integer, Integer>> check = new ArrayList<Pair<Integer, Integer>>();

    public void testIllegalArgumentException2Points() {
        points = new ArrayList<Pair<Integer, Integer>>();
        points.add(new Pair<Integer, Integer>(0, 1));
        points.add(new Pair<Integer, Integer>(2, 1));
        try {
            scan.solve(points);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void testIllegalArgumentExceptionTheSamePoints() {
        points = new ArrayList<Pair<Integer, Integer>>();
        points.add(new Pair<Integer, Integer>(7, 0));
        points.add(new Pair<Integer, Integer>(0, 0));
        points.add(new Pair<Integer, Integer>(1, 9));
        points.add(new Pair<Integer, Integer>(0, 0));
        try {
            scan.solve(points);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void test3IntegerPoints() {
        points = new ArrayList<Pair<Integer, Integer>>();
        points.add(new Pair<Integer, Integer>(-1, -9));
        points.add(new Pair<Integer, Integer>(3, 7));
        points.add(new Pair<Integer, Integer>(7, 1));
        scan.solve(points);
    }

    public void test10IntegerPoints() {
        points = new ArrayList<Pair<Integer, Integer>>();
        check = new ArrayList<Pair<Integer, Integer>>();
        points.add(new Pair<Integer, Integer>(0, 0));
        points.add(new Pair<Integer, Integer>(5, -2));
        points.add(new Pair<Integer, Integer>(3, 1));
        points.add(new Pair<Integer, Integer>(1, 7));
        points.add(new Pair<Integer, Integer>(2, 3));
        points.add(new Pair<Integer, Integer>(10, 13));
        points.add(new Pair<Integer, Integer>(3, 13));
        points.add(new Pair<Integer, Integer>(14, 1));
        points.add(new Pair<Integer, Integer>(10, 17));
        points.add(new Pair<Integer, Integer>(12, 8));
        result = scan.solve(points);
        check.add(new Pair<Integer, Integer>(5, -2));
        check.add(new Pair<Integer, Integer>(14, 1));
        check.add(new Pair<Integer, Integer>(10, 17));
        check.add(new Pair<Integer, Integer>(3, 13));
        check.add(new Pair<Integer, Integer>(1, 7));
        check.add(new Pair<Integer, Integer>(0, 0));
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(check.get(i).getFirst(), result.get(i).getFirst());
            Assert.assertEquals(check.get(i).getSecond(), result.get(i).getSecond());
        }
    }

    public void testTriangle() {
        points = new ArrayList<Pair<Integer, Integer>>();
        check = new ArrayList<Pair<Integer, Integer>>();
        points.add(new Pair<Integer, Integer>(-2, 5));
        points.add(new Pair<Integer, Integer>(6, -1));
        points.add(new Pair<Integer, Integer>(14, 16));
        points.add(new Pair<Integer, Integer>(6, 6));
        points.add(new Pair<Integer, Integer>(5, 4));
        points.add(new Pair<Integer, Integer>(3, 7));
        points.add(new Pair<Integer, Integer>(7, 5));
        points.add(new Pair<Integer, Integer>(6, 0));
        points.add(new Pair<Integer, Integer>(9, 9));
        result = scan.solve(points);
        check.add(new Pair<Integer, Integer>(6, -1));
        check.add(new Pair<Integer, Integer>(14, 16));
        check.add(new Pair<Integer, Integer>(-2, 5));
        for (int i = 0; i < result.size(); i++) {
            Assert.assertEquals(check.get(i).getFirst(), result.get(i).getFirst());
            Assert.assertEquals(check.get(i).getSecond(), result.get(i).getSecond());
        }
    }
}
