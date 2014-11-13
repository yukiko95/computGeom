import java.util.*;

public class GrahamScan {
    protected List<Pair<Integer, Integer>> solve(final List<Pair<Integer, Integer>> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("n должно быть >=3");
        }
        //Сортируем точки по y координате
        Collections.sort(points, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if (o1.getSecond() - o2.getSecond() != 0) {
                    return o1.getSecond() - o2.getSecond();
                } else {
                    if (o1.getFirst() - o2.getFirst() == 0) {
                        throw new IllegalArgumentException("Точки с одинаковыми координатами");
                    }
                    return o1.getFirst() - o2.getFirst();
                }
            }
        });
        final Pair<Integer, Integer> start = new Pair<Integer, Integer>(points.get(0).getFirst(), points.get(0).getSecond());
        points.remove(0);
        //Сортируем точки в порядке возрастания полярного угла (относительно "левой нижней" точки)
        Collections.sort(points, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return turn(start, o1, o2);
            }
        });
        List<Pair<Integer, Integer>> hull = new ArrayList<Pair<Integer, Integer>>();
        hull.add(start);
        //Формируем list с ответом, путем просмотра 3-х "последних" точек
        for (Pair<Integer, Integer> point : points) {
            int len = hull.size();
            while (len > 1 && turn(hull.get(len - 2), hull.get(len - 1), point) >= 0) {
                hull.remove(len - 1);
                --len;
            }
            if (hull.size() == 0 || !hull.get(hull.size() - 1).equals(point)) {
                hull.add(point);
            }
        }
        return hull;
    }

    //Определяет вид поворота
    private int turn(final Pair<Integer, Integer> start, final Pair<Integer, Integer> o1, final Pair<Integer, Integer> o2) {
        return -((o1.getFirst() - start.getFirst()) * (o2.getSecond() - start.getSecond()) -
                (o1.getSecond() - start.getSecond()) * (o2.getFirst() - start.getFirst()));
    }
}
