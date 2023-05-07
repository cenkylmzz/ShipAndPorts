package containers;

public class HeavyContainer extends Container{
    public HeavyContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double consumption() {
        return 3.0*getWeight();
    }

    @Override
    boolean equals(Container other) {
        return other.getClass().getSimpleName().equals("HeavyContainer") &&
                getID() == other.getID() && getWeight() == other.getWeight();
    }
}
