import java.util.*;

public class AndrewMonotoneChain {
    protected List<Pair<Integer, Integer>> solve(final List<Pair<Integer, Integer>> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("n должно быть >=3");
        }
        //Сортируем точки по x координате
        Collections.sort(points, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if (o1.getFirst() - o2.getFirst() != 0) {
                    return o1.getFirst() - o2.getFirst();
                } else {
                    if (o1.getSecond() - o2.getSecond() == 0) {
                        throw new IllegalArgumentException("Точки с одинаковыми координатами");
                    }
                    return o1.getSecond() - o2.getSecond();
                }
            }
        });
        //Строим нижнюю часть оболочки
        List<Pair<Integer, Integer>> convexHull = new ArrayList<Pair<Integer, Integer>>();
        for (Pair<Integer, Integer> point : points) {
            int len = convexHull.size();
            while (len > 1 && turn(convexHull.get(len - 2), convexHull.get(len - 1), point) > 0) {
                convexHull.remove(len - 1);
                --len;
            }
            convexHull.add(point);
        }
        convexHull.remove(convexHull.size() - 1);
        Collections.reverse(points);
        //Строим верхнюю часть оболочки
        for (Pair<Integer, Integer> point : points) {
            int len = convexHull.size();
            while (len > 1 && turn(convexHull.get(len - 2), convexHull.get(len - 1), point) > 0) {
                convexHull.remove(len - 1);
                --len;
            }
            convexHull.add(point);
        }
        convexHull.remove(convexHull.size() - 1);
        return convexHull;
    }

    //Определяет вид поворота
    private static int turn(final Pair<Integer, Integer> start, final Pair<Integer, Integer> o1, final Pair<Integer, Integer> o2) {
        return -((o1.getFirst() - start.getFirst()) * (o2.getSecond() - start.getSecond()) -
                (o1.getSecond() - start.getSecond()) * (o2.getFirst() - start.getFirst()));
    }
}
