import java.util.ArrayList;
import java.util.Collections;

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
            ArrayList<Integer> actions = problem.actions(current);
            Collections.shuffle(actions);
            for (Integer action : actions) {
                nodeSeen++;
                State nextState = problem.nextState(current, action);
                if (problem.h(nextState) < problem.h(current)) {
                    f.add(nextState);
                    break;
                }
            }
            nodeExpand++;

            previousState = current;
            count++;
            maxNodeKeptInMemory = Integer.max(maxNodeKeptInMemory, f.size());
        }

        answer = current;
        createSolutionPath(current);
    }


}

