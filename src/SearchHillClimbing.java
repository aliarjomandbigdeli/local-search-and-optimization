import java.util.Comparator;

/**
 * Hill Climbing local search algorithm
 *
 * @author Ali ArjomandBigdeli
 * @since 12.27.2018
 */
public class SearchHillClimbing extends Search {
    public SearchHillClimbing(Problem problem) {
        super(problem);
    }

    @Override
    public void execute() {
        f.add(problem.getInitialState());
        search();
        maxMemoryUse = (nodeSeen - nodeExpand) * nodeSize;
    }

    @Override
    public void search() {
        int count = 0;
        State previousState = new MapColorState(new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
        while (true) {
            State current = f.remove();
            f.clear();
            if (problem.h(current) == problem.h(previousState)) {
                answer = current;
                State temp = current;
                while (temp != null) {
                    path.add(temp.act);
                    temp = temp.parent;
                }
                return;
            }
            for (Integer action : problem.actions(current)) {
                nodeSeen++;

                f.add(problem.nextState(current, action));

            }
            nodeExpand++;

            f.sort(new Comparator<State>() {
                @Override
                public int compare(State s1, State s2) {
                    return ((Double) problem.h(s1)).compareTo(problem.h(s2));
                }
            });
            previousState = current;
            count++;
        }
    }

}
