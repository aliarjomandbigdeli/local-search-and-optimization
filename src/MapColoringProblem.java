import java.util.ArrayList;
import java.util.List;

public class MapColoringProblem extends Problem {
    private int[][] map;
    private ArrayList<Integer> actions;

    public MapColoringProblem() {
        initializeProblem();
    }

    @Override
    public ArrayList<Integer> actions(State state) {
        return actions;
    }

    @Override
    public State nextState(State state, int action) {
        if (action <= 10 && action >= 0) {
            int[] colors = ((MapColorState) state).getColors();
            colors[action] = (colors[action]++) % 3;
            return new MapColorState(colors);
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
    public double objectiveFunction(State state) {
        int count = 0;
        int[] colors = ((MapColorState) state).getColors();
        for (int i = 0; i < 11; i++) {
            for (Integer neighbor : neighbors(i)) {
                if (colors[i] == colors[neighbor]) {
                    count++;
                }
            }
        }
        return count;
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
        actions = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            actions.add(i);
        }
    }
}
