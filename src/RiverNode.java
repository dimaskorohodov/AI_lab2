import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RiverNode {

    private RiverNode parent;

    private Unit knight1 = new Unit(Side.LEFT);
    private Unit knight2 = new Unit(Side.LEFT);
    private Unit knight3 = new Unit(Side.LEFT);

    private Unit armed1 = new Unit(Side.LEFT);
    private Unit armed2 = new Unit(Side.LEFT);
    private Unit armed3 = new Unit(Side.LEFT);

    private Unit boat = new Unit(Side.LEFT);

    public RiverNode(RiverNode parent, Unit knight1, Unit knight2, Unit knight3, Unit armed1, Unit armed2, Unit armed3, Unit boat) {
        this.parent = parent;
        this.knight1 = knight1;
        this.knight2 = knight2;
        this.knight3 = knight3;
        this.armed1 = armed1;
        this.armed2 = armed2;
        this.armed3 = armed3;
        this.boat = boat;
    }

    public RiverNode() {

    }

    public Unit getKnight1() {
        return knight1;
    }

    public void setKnight1(Unit knight1) {
        this.knight1 = knight1;
    }

    public Unit getKnight2() {
        return knight2;
    }

    public void setKnight2(Unit knight2) {
        this.knight2 = knight2;
    }

    public Unit getKnight3() {
        return knight3;
    }

    public void setKnight3(Unit knight3) {
        this.knight3 = knight3;
    }

    public Unit getArmed1() {
        return armed1;
    }

    public void setArmed1(Unit armed1) {
        this.armed1 = armed1;
    }

    public Unit getArmed2() {
        return armed2;
    }

    public void setArmed2(Unit armed2) {
        this.armed2 = armed2;
    }

    public Unit getArmed3() {
        return armed3;
    }

    public void setArmed3(Unit armed3) {
        this.armed3 = armed3;
    }

    public Unit getBoat() {
        return boat;
    }

    public void setBoat(Unit boat) {
        this.boat = boat;
    }

    private void crossRiver(Unit unit1, Unit unit2) {
        boat.setSide(getCrossSide(boat.getSide()));
        unit1.setSide(getCrossSide(unit1.getSide()));
        unit2.setSide(getCrossSide(unit2.getSide()));
    }


    private Side getCrossSide(Side side) {
        if (side.equals(side.LEFT)) {
            return Side.RIGHT;
        }
        return Side.LEFT;
    }

    private Boolean couldCross(Unit unit1, Unit unit2) {
        if (unit1.getSide().equals(unit2.getSide()) && unit1.getSide().equals(boat.getSide())) {
            return true;
        }
        return false;
    }

    private Boolean couldCross(Unit unit) {
        if (unit.getSide().equals(boat.getSide())) {
            return true;
        }
        return false;
    }

    private Unit crossRiver(Unit unit) {
        return new Unit(getCrossSide(unit.getSide()));
    }

    public List<RiverNode> getPossibleMoves() {

        List<RiverNode> riverNodes = new ArrayList<>();

        RiverNode riverNode = new RiverNode();

        if (couldCross(knight1, armed1)) {
            RiverNode trialNode = new RiverNode(this, new Unit(crossRiver(knight1).getSide()), new Unit(knight2.getSide()), new Unit(knight3.getSide()), new Unit(crossRiver(armed1).getSide()), new Unit(armed2.getSide()), new Unit(armed3.getSide()), new Unit(crossRiver(boat).getSide()));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(knight2, armed2)) {
            RiverNode trialNode = new RiverNode(this, new Unit(knight1.getSide()), new Unit(crossRiver(knight2).getSide()), new Unit(knight3.getSide()), new Unit(armed1.getSide()), new Unit(crossRiver(armed2).getSide()), new Unit(armed3.getSide()), new Unit(crossRiver(boat).getSide()));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(knight3, armed3)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, crossRiver(knight3), armed1, armed2, crossRiver(armed3), crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }

        if (couldCross(knight1, knight2)) {
            RiverNode trialNode = new RiverNode(this, crossRiver(knight1), crossRiver(knight2), knight3, armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(knight1, knight3)) {
            RiverNode trialNode = new RiverNode(this, crossRiver(knight1), knight2, crossRiver(knight3), armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(knight2, knight3)) {
            RiverNode trialNode = new RiverNode(this, knight1, crossRiver(knight2), crossRiver(knight3), armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }


        if (couldCross(armed1, armed2)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, crossRiver(armed1), crossRiver(armed2), armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(armed1, armed3)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, crossRiver(armed1), armed2, crossRiver(armed3), crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(armed2, armed3)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, armed1, crossRiver(armed2), crossRiver(armed3), crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }

        if (couldCross(knight1)) {
            RiverNode trialNode = new RiverNode(this, crossRiver(knight1), knight2, knight3, armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        // Add cross side for boat
        if (couldCross(knight2)) {
            RiverNode trialNode = new RiverNode(this, knight1, crossRiver(knight2), knight3, armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(knight3)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, crossRiver(knight3), armed1, armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }


        if (couldCross(armed1)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, crossRiver(armed1), armed2, armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                System.out.println("Adding armed1");
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(armed2)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, armed1, crossRiver(armed2), armed3, crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }
        if (couldCross(armed3)) {
            RiverNode trialNode = new RiverNode(this, knight1, knight2, knight3, armed1, armed2, crossRiver(armed3), crossRiver(boat));
            if (isPossibleState(trialNode)) {
                riverNodes.add(trialNode);
            }
        }

        return riverNodes;
    }


    private Boolean isPossibleState(RiverNode node) {
        if (!node.getArmed1().getSide().equals(node.getKnight1().getSide())) {
            if (node.getArmed1().getSide().equals(node.getKnight2().getSide()) || node.getArmed1().getSide().equals(node.getKnight3().getSide())) {
                return false;
            }
        }

        if (!node.getArmed2().getSide().equals(node.getKnight2().getSide())) {
            if (node.getArmed2().getSide().equals(node.getKnight1().getSide()) || node.getArmed2().getSide().equals(node.getKnight3().getSide())) {
                return false;
            }
        }

        if (!node.getArmed3().getSide().equals(node.getKnight3().getSide())) {
            if (node.getArmed3().getSide().equals(node.getKnight1().getSide()) || node.getArmed3().getSide().equals(node.getKnight2().getSide())) {
                return false;
            }
        }
        if (checkIsOneCrosser(node) == false) {
            return false;
        }
        return true;
    }

    private Boolean checkIsOneCrosser(RiverNode possibleState) {

        if (this.getKnight1().getSide().equals(Side.LEFT) && this.getKnight2().getSide().equals(Side.LEFT) && this.getKnight3().getSide().equals(Side.LEFT) && this.getArmed1().getSide().equals(Side.LEFT) && this.getArmed2().getSide().equals(Side.LEFT) && this.getArmed3().getSide().equals(Side.LEFT)) {
            if (possibleState.getArmed1().getSide().equals(Side.RIGHT) && possibleState.getKnight1().getSide().equals(Side.LEFT) && possibleState.getArmed2().getSide().equals(Side.LEFT) && possibleState.getArmed3().getSide().equals(Side.LEFT)) {
                return false;
            }
            if (possibleState.getArmed2().getSide().equals(Side.RIGHT) && possibleState.getKnight2().getSide().equals(Side.LEFT) && possibleState.getArmed1().getSide().equals(Side.LEFT) && possibleState.getArmed3().getSide().equals(Side.LEFT)) {
                return false;
            }
            if (possibleState.getArmed3().getSide().equals(Side.RIGHT) && possibleState.getKnight3().getSide().equals(Side.LEFT) && possibleState.getArmed1().getSide().equals(Side.LEFT) && possibleState.getArmed2().getSide().equals(Side.LEFT)) {
                return false;
            }
        }
        return true;
    }


    public Boolean isTarget() {
        if (getArmed1().getSide().equals(Side.RIGHT) && getArmed2().getSide().equals(Side.RIGHT) && getArmed3().getSide().equals(Side.RIGHT) && getKnight1().getSide().equals(Side.RIGHT) && getKnight2().getSide().equals(Side.RIGHT) && getKnight3().getSide().equals(Side.RIGHT)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {

        RiverNode riverNode = (RiverNode) o;
        if (this.getKnight1().getSide().equals(riverNode.getKnight1().getSide()) && this.getKnight2().getSide().equals(riverNode.getKnight2().getSide()) && this.getKnight3().getSide().equals(riverNode.getKnight3().getSide())) {
            if (this.getArmed1().getSide().equals(riverNode.getArmed1().getSide()) && this.getArmed2().getSide().equals(riverNode.getArmed2().getSide()) && this.getArmed3().getSide().equals(riverNode.getArmed3().getSide())) {
                if (this.getBoat().getSide().equals(riverNode.getBoat().getSide())) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public int hashCode() {
        return Objects.hash(parent, knight1, knight2, knight3, armed1, armed2, armed3, boat);
    }

    public RiverNode getParent() {
        return parent;
    }

    public void setParent(RiverNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "RiverNode{" +
                "knight1=" + knight1.getSide() +
                ", knight2=" + knight2.getSide() +
                ", knight3=" + knight3.getSide() +
                ", armed1=" + armed1.getSide() +
                ", armed2=" + armed2.getSide() +
                ", armed3=" + armed3.getSide() +
                ", boat=" + boat.getSide() +
                '}';
    }
}
