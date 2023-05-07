package ports;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

import java.util.ArrayList;

import static ships.Ship.bubbleSortWithIndexes;

public class Port implements IPort {
    private final int ID;
    private final double X;
    private final double Y;
    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<Ship> history = new ArrayList<>();
    private ArrayList<Ship> current = new ArrayList<>();

    public Port(int ID,double X,double Y){
        this.ID = ID;
        this.X = X;
        this.Y = Y;

    }
    public double getDistance(Port other){
        return Math.sqrt(Math.pow(getX() - other.getX(),2) + Math.pow(getY() - other.getY(),2));
    }
    @Override
    public void incomingShip(Ship s) {
        if(!current.contains(s)){
            current.add(s);

        }
    }
    @Override
    public void outgoingShip(Ship s) {
        if (current.contains(s) && !history.contains(s)) {
            history.add(s);
        }
    }

    public int getID() {
        return ID;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public ArrayList<Ship> getCurrent() {
        int[] ids = new int[current.size()];
        int[] containerIndexes = new int[current.size()];
        for (int i = 0; i<current.size(); i++){
            ids[i] = current.get(i).getID();
            containerIndexes[i] = i;
        }
        bubbleSortWithIndexes(ids,containerIndexes);
        ArrayList<Ship> newCurrent = new ArrayList<>();
        for(int con : containerIndexes){
            newCurrent.add(current.get(con));
        }
        current.clear();
        current.addAll(newCurrent);
        return current;
    }
}
