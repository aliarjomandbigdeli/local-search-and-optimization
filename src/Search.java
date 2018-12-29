import java.util.LinkedList;

/**
 * this class is the parent of all searches you want to define with this interface
 *
 * @author Ali ArjomandBigdeli
 * @since 12.27.2018
 */
public abstract class Search {
    protected int nodeSeen;
    protected int nodeExpand;
    protected LinkedList<Integer> path; //action sequence
    protected int maxNodeKeptInMemory;  //maximum node kept in memory at a time
    protected State answer;

    protected Problem problem;
    protected LinkedList<State> f;   //frontier list


    public Search(Problem problem) {
        this.problem = problem;
        nodeSeen = 0;
        nodeExpand = 0;
        path = new LinkedList<>();
        maxNodeKeptInMemory = 0;
        f = new LinkedList<>();
    }

    public int getNodeSeen() {
        return nodeSeen;
    }

    public int getNodeExpand() {
        return nodeExpand;
    }

    public int getMaxNodeKeptInMemory() {
        return maxNodeKeptInMemory;
    }

    public LinkedList<Integer> getPath() {
        return path;
    }

    public Problem getProblem() {
        return problem;
    }

    abstract public void execute();

    abstract public void search();

    protected void createSolutionPath(State state){
        State temp = state;
        while (temp != null) {
            path.add(temp.act);
            temp = temp.parent;
        }
    }

}
