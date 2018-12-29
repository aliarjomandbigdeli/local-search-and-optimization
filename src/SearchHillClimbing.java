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
        search();
    }

    @Override
    public void search() {
        int count = 0;
        State current = problem.getInitialState();
        State previousState = current;
        f.add(problem.getInitialState());
        nodeSeen++;
        while (!f.isEmpty()) {
            current = f.remove();
            f.clear();
            if (problem.h(current) >= problem.h(previousState) && count > 0) {
                break;
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
            maxNodeKeptInMemory = Integer.max(maxNodeKeptInMemory, f.size());
        }

        answer = current;
        createSolutionPath(current);
    }

}
