import java.util.Random;

/**
 * First Choice Hill Climbing local search algorithm
 * First-choice hill climbing implements stochastic hill climbing by generating successors
 * randomly until one is generated that is better than the current state.
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SearchFirstChoiceHillClimbing extends Search {
    public SearchFirstChoiceHillClimbing(Problem problem) {
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
        Random random = new Random();
        State previousState = new MapColorState(new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
        State current = problem.getInitialState();
        while (f.size() > 0) {
            current = f.remove();
            if (problem.h(current) == problem.h(previousState)) {
                break;
            }

            f.clear();

            State nextState = problem.nextState(current,
                    problem.actions(current).get(random.nextInt(problem.actions(current).size())));
            nodeSeen++;
            count = 0;
            while (problem.h(nextState) < problem.h(current) && count < problem.actions(current).size()) {
                nextState = problem.nextState(current,
                        problem.actions(current).get(random.nextInt(problem.actions(current).size())));
                nodeSeen++;
                count++;
            }
            f.add(nextState);
            nodeExpand++;

            previousState = current;
            ;
        }

        answer = current;
        State temp = current;
        while (temp != null) {
            path.add(temp.act);
            temp = temp.parent;
        }
    }

}

