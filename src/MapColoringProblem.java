import java.util.ArrayList;
import java.util.List;

public class MapColoringProblem extends Problem {
    private int[][] map;

    public MapColoringProblem() {
        initializeProblem();
    }

    @Override
    public ArrayList<Integer> actions(State state) {
        ArrayList<Integer> actions = new ArrayList<>();
        return actions;
    }

    @Override
    public State nextState(State state, int action) {
        if (action <= 10) {
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
    public double h(State state) {
        return 0;
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
    }
}
