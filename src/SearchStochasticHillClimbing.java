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
        search();
    }

    @Override
    public void search() {
        int count = 0;
        Random random = new Random();
        State current = problem.getInitialState();
        State previousState = current;
        f.add(problem.getInitialState());
        nodeSeen++;
        while (!f.isEmpty()) {
            current = f.get(random.nextInt(f.size()));
            f.clear();
            if (problem.h(current) >= problem.h(previousState) && count > 0) {
                break;
            }
            for (Integer action : problem.actions(current)) {
                nodeSeen++;
                State nextState = problem.nextState(current, action);
                if (problem.h(nextState) < problem.h(current))
                    f.add(problem.nextState(current, action));

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
