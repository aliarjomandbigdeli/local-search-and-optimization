import java.util.Random;

/**
 * Simulated Annealing algorithm
 * The innermost loop(while loop) of the simulated-annealing algorithm is quite similar to hill climbing.
 * Instead of picking the best move, however, it picks a random move. If the move improves the situation,
 * it is always accepted. Otherwise, the algorithm accepts the move with some probability less than 1.
 * The probability decreases exponentially with the “badness” of the move—the amount ΔE by which the
 * evaluation is worsened. The probability also decreases as the “temperature” T goes down: “bad” moves
 * are more likely to be allowed at the start when T is high, and they become more unlikely as T decreases
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
    public void search() {
        int count = 0;  //acts like time
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

    /**
     * determines the value of the temperature T as a function of time.
     *
     * @param time that is number of iteration in this version
     * @return
     */
    private int schedule(int time) {
        return maxIteration - time;
    }

}
