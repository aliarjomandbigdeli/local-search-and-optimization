import java.util.Comparator;

/**
 * Hill Climbing local search algorithm
 *
 * @author Ali ArjomandBigdeli
 * @since 12.27.2018
 */
public class SearchHillClimbing extends Search {
    public SearchHillClimbing(boolean isGraph) {
        super(isGraph);
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
            State s = f.remove();
            f.clear();
            if (problem.h(s) == problem.h(previousState)) {
                answer = s;
                State temp = s;
                while (temp != null) {
                    path.add(temp.act);
                    temp = temp.parent;
                }
                return;
            }
            for (Integer action : problem.actions(s)) {
                nodeSeen++;

                f.add(problem.nextState(s, action));

            }

            f.sort(new Comparator<State>() {
                @Override
                public int compare(State s1, State s2) {
                    return ((Double) problem.h(s1)).compareTo(problem.h(s2));
                }
            });
            previousState = s;
            count++;
        }
    }

}
