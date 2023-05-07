package containers;

public class RefrigeratedContainer extends HeavyContainer{
    public RefrigeratedContainer(int ID, int weight) {
        super(ID, weight);
    }
    @Override
    public double consumption() {
        return 5.0 * getWeight();
    }
    @Override
    boolean equals(Container other) {
        return other.getClass().getSimpleName().equals("RefrigeratedContainer") &&
                getID() == other.getID() && getWeight() == other.getWeight();
    }
}
