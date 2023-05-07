package containers;

public class LiquidContainer extends HeavyContainer{
    public LiquidContainer(int ID, int weight) {
        super(ID, weight);
    }
    @Override
    public double consumption() {
        return 4.0*getWeight();
    }
    @Override
    boolean equals(Container other) {
        return other.getClass().getSimpleName().equals("LiquidContainer") &&
                getID() == other.getID() && getWeight() == other.getWeight();
    }
}
