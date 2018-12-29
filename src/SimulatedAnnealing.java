import java.util.Random;

/**
 * Simulated Annealing algorithm
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SimulatedAnnealing extends Search {
    int maxIteration = 2000;

    public SimulatedAnnealing(Problem problem) {
        super(problem);
    }

    @Override
    public void execute() {
        search();
    }

    @Override
    public void search() {
        int count = 0;  //act like time
        int T = 0;  //temperature
        State current = problem.getInitialState();
        while (true) {
            T = schedule(count);
            if (T == 0) {
                break;
            }
            if (count > 0) {
                Random random = new Random();
                int choice = random.nextInt(f.size());
                State next = f.get(choice);
                if (problem.h(next) < problem.h(current))
                    current = next;
                else {
                    int rand = random.nextInt(maxIteration);
                    if (rand < (maxIteration - count))
                        current = next;

                }
            }
            f.clear();

            for (Integer action : problem.actions(current)) {
                nodeSeen++;
                f.add(problem.nextState(current, action));

            }
            nodeExpand++;


            count++;
            maxNodeKeptInMemory = Integer.max(maxNodeKeptInMemory, f.size());
        }

        answer = current;
        createSolutionPath(current);
    }

    private int schedule(int time) {
        return maxIteration - time;
    }

}
