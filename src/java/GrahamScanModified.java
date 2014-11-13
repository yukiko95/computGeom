import java.util.*;

public class GrahamScanModified {

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
        //Ответ в данном случае состоит из двух половинок: оболочка, соединяющая "левую нижнюю" и "правую верхнюю" точки
        //и в обратном порядке от "правой верхней" к "левой нижней" точек
        List<Pair<Integer, Integer>> upper = findConvexHull(points);
        Collections.reverse(points);
        List<Pair<Integer, Integer>> lower = findConvexHull(points);
        for (int i = 1; i < lower.size() - 1; i++) {
            upper.add(lower.get(i));
        }
        return upper;
    }

    private List<Pair<Integer, Integer>> findConvexHull(final List<Pair<Integer, Integer>> points) {
        //Формируем list с ответом, путем просмотра 3-х "последних" точек
        List<Pair<Integer, Integer>> hull = new ArrayList<Pair<Integer, Integer>>();
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
