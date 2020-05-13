import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPriorityQueu {


    private List<Pair> elements = new ArrayList();

    public void enequeu(RiverNode item, double priority) {

        elements.add(new Pair(item, priority));

    }

    public RiverNode dequeu() {
        int bestIndex = 0;

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getPriority() < elements.get(bestIndex).getPriority()) {
                bestIndex = i;
            }
        }

        RiverNode bestNode = elements.get(bestIndex).getRiverNode();
        Iterator<Pair> iterator = elements.iterator();

        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getRiverNode().equals(bestNode)) {
                iterator.remove();
            }
        }

        return bestNode;
    }

    public List<Pair> getElements() {
        return elements;
    }

    public void setElements(List<Pair> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "MyPriorityQueu{" +
                "elements=" + elements +
                '}';
    }
}
