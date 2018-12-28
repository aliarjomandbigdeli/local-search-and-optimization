public class MapColorState extends State {
    private int[] colors;   //chromosome

    //in this problem act is a number that which node should change its color
    public MapColorState(int[] colors) {
        this.colors = colors;
    }

    public int[] getColors() {
        return colors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MapColorState))
            return false;
        MapColorState other = (MapColorState) obj;
        for (int i = 0; i < this.colors.length; i++) {
            if (this.colors[i] != other.getColors()[i])
                return false;
        }
        return true;
    }
}
