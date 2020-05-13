import java.util.HashMap;
import java.util.Map;

public class StarSearch {

    private RiverNode startNode;
    private Map<RiverNode, Integer> costSoFar = new HashMap<>();
    private RiverNode target;

    public StarSearch(RiverNode startNode) {
        this.startNode = startNode;
    }


    private int heuristic(RiverNode riverNode) {
        int leftUnits = 0;

        if (riverNode.getKnight1().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }
        if (riverNode.getKnight2().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }
        if (riverNode.getKnight3().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }

        if (riverNode.getArmed1().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }
        if (riverNode.getArmed2().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }
        if (riverNode.getArmed3().getSide().equals(Side.LEFT)) {
            leftUnits++;
        }

        return leftUnits;
    }

    public void print() {

        RiverNode end = target;

        while (end != null) {
            System.out.println(end);
            end = end.getParent();
        }
    }


    public void solve() {

        var frontier = new MyPriorityQueu();

        frontier.enequeu(startNode, 0);

        costSoFar.put(startNode, heuristic(startNode));


        while (frontier.getElements().size() > 0) {

            var current = frontier.dequeu();

            if (current.isTarget()) {
                target = current;
                print();
                break;
            }

            for (var next : current.getPossibleMoves()) {
                int newCost = heuristic(next);
                if (!costSoFar.containsKey(next)) {
                    costSoFar.put(next, newCost);
                    frontier.enequeu(next, newCost);
                }
            }
        }
    }


}
