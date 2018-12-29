import java.util.Random;

/**
 * Simulated Annealing algorithm
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SimulatedAnnealing extends Search {
    int maxIteration = 50000;

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
                if (problem.fitness(next) > problem.fitness(current))
                    current = next;
                else {
                    double deltaE = problem.fitness(next) - problem.fitness(current);
                    deltaE *= maxIteration;
                    double probability = Math.exp(deltaE / T);
                    int intProb = (int) Math.floor(probability * 1000);
                    int rand = random.nextInt(1000);
                    if (rand < intProb)
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
