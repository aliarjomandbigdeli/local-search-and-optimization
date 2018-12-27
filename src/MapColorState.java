public class MapColorState extends State {
    private int[] colors;

    //in this problem act is a number that which node should change its color
    public MapColorState(int[] colors) {
        this.colors = colors;
    }

    public int[] getColors() {
        return colors;
    }
}
