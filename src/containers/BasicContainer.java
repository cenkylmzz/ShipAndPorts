package containers;

public class BasicContainer extends  Container{
    public BasicContainer(int ID, int weight) {
        super(ID, weight);
    }

    @Override
    public double consumption() {
        return 2.5*getWeight();
    }

    @Override
    boolean equals(Container other) {
        return other.getClass().getSimpleName().equals("BasicContainer") &&
                getID() == other.getID() && getWeight() == other.getWeight();
    }
}
