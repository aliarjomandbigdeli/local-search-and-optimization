import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapColoringProblem extends Problem {
    private int[][] map;
    private int numberOfColor = 3;
    private int m;  //number of edges
    private int n;  //number of vertices

    public MapColoringProblem() {
        initializeProblem();
    }

    @Override
    public ArrayList<Integer> actions(State state) {
        ArrayList<Integer> actions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            actions.add(i);
        }
        return actions;
    }

    @Override
    public State nextState(State state, int action) {
        int[] colors = new int[n];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ((MapColorState) state).getColors()[i];
        }
        if (action < n && action >= 0) {
//            colors = ((MapColorState) state).getColors();
            colors[action] = (colors[action] + 1) % numberOfColor;
            MapColorState nextState = new MapColorState(colors);
            nextState.parent = state;
            nextState.act = action;
            return nextState;
        } else {
            System.out.println("wrong action");
            return null;
        }
    }

    @Override
    public double stepCost(State firstState, int action, State secondState) {
        return 1;
    }

    @Override
    public double pathCost(List<Integer> path) {
        return path.size();
    }

    @Override
    public double h(State state) {
        int count = 0;
        int[] colors = ((MapColorState) state).getColors();
        for (int i = 0; i < n; i++) {
            for (Integer neighbor : neighbors(i)) {
                if (colors[i] == colors[neighbor]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public double fitness(State state) {
        double count = 0;
        int[] colors = ((MapColorState) state).getColors();
        for (int i = 0; i < n; i++) {
            for (Integer neighbor : neighbors(i)) {
                if (colors[i] != colors[neighbor]) {
                    count++;
                }
            }
        }
        return count / m;
    }

    private ArrayList<Integer> neighbors(int node) {
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < map[node].length; i++) {
            if (map[node][i] == 1)
                neighbors.add(i);
        }
        return neighbors;
    }

    private void initializeProblem() {
        n = 11;
        m = 20;
        initialState = new MapColorState(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        map = new int[][]{
                {0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0}
        };
    }

    public State generateRandomState() {
        Random random = new Random();
        int[] colors = new int[n];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = random.nextInt(numberOfColor);
        }
        return new MapColorState(colors);
    }

    @Override
    public State crossover(State parent1, State parent2) {
        int len = ((MapColorState) parent1).getColors().length;
        Random random = new Random();
        int crossoverPoint = random.nextInt(len);
        int[] color = new int[len];
        for (int i = 0; i < len; i++) {
            if (i < crossoverPoint)
                color[i] = ((MapColorState) parent1).getColors()[i];
            else
                color[i] = ((MapColorState) parent2).getColors()[i];
        }
        return new MapColorState(color);
    }

    @Override
    public int getNumberOfGenomes() {
        return n;
    }

    @Override
    public State mutation(State state, int index) {
        Random random = new Random();
        ((MapColorState) state).getColors()[index] = random.nextInt(numberOfColor);
        return state;
//        Random random = new Random();
//        int choice = 0;
//        for (int i = 0; i < mutatedGenomes; i++) {
//            choice = random.nextInt(n);
//            ((MapColorState) state).getColors()[choice] = random.nextInt(numberOfColor);
//        }
//        return state;
    }

}
