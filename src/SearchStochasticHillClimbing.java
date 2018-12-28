import java.util.Comparator;
import java.util.Random;

/**
 * Stochastic Hill Climbing local search algorithm
 * Stochastic hill climbing chooses at random from among the uphill moves;
 * the probability of selection can vary with the steepness of the uphill move.
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SearchStochasticHillClimbing extends Search {
    public SearchStochasticHillClimbing(Problem problem) {
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
            if (f.size() <= 1)
                current = f.remove();
            else
                current = f.get(random.nextInt(f.size()));

            if ( problem.h(current) == problem.h(previousState)) {
                break;
            }

            f.clear();
            for (Integer action : problem.actions(current)) {
                nodeSeen++;
                State nextState = problem.nextState(current, action);
                if (problem.h(nextState) < problem.h(current))
                    f.add(problem.nextState(current, action));

            }
            nodeExpand++;

            previousState = current;
            count++;
        }

        answer = current;
        State temp = current;
        while (temp != null) {
            path.add(temp.act);
            temp = temp.parent;
        }
    }

}
