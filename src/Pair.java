import java.util.Objects;

public class Pair {
    private RiverNode riverNode;
    private double priority;

    public Pair(RiverNode riverNode, double priority) {
        this.riverNode = riverNode;
        this.priority = priority;
    }

    public RiverNode getRiverNode() {
        return riverNode;
    }

    public void setRiverNode(RiverNode riverNode) {
        this.riverNode = riverNode;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Double.compare(pair.priority, priority) == 0 &&
                Objects.equals(riverNode, pair.riverNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(riverNode, priority);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "riverNode=" + riverNode +
                ", priority=" + priority +
                '}';
    }
}
