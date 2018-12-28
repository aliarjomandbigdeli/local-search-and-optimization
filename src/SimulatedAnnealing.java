import java.util.Random;

/**
 * Simulated Annealing algorithm
 *
 * @author Ali ArjomandBigdeli
 * @since 12.28.2018
 */
public class SimulatedAnnealing extends Search {

    public SimulatedAnnealing(Problem problem) {
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
        int maxIteration = 1000;
        int count = 0;
        int choice = 0;
        State current = f.remove();
        while (count < maxIteration) {
            if (count > 0) {
                Random random = new Random();
                choice = random.nextInt(f.size());
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
        }

        answer = current;
        State temp = current;
        while (temp != null) {
            path.add(temp.act);
            temp = temp.parent;
        }
    }

}
