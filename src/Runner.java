import java.util.List;

public class Runner {
    public static void main(String[] args) {

        RiverNode riverNode = new RiverNode();

        StarSearch starSearch = new StarSearch(riverNode);

        starSearch.solve();

    }
}
