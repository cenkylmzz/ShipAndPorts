package containers;

public abstract class Container {
    private final int ID;
    private final int weight;

    Container(int ID, int weight){
        this.ID = ID;
        this.weight = weight;
    }

    public abstract double consumption();

    abstract boolean equals(Container other);
    public int getID() {
        return ID;
    }

    public int getWeight() {
        return weight;
    }
}
