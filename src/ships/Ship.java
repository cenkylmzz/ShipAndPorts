package ships;

import containers.Container;
import interfaces.IShip;
import ports.Port;

import java.util.ArrayList;

public class Ship implements IShip {

    private final int ID;
    private double fuel;
    private int totalWeightCapacity,maxNumberOfAllContainers,maxNumberOfHeavyContainers,
            maxNumberOfRefrigeratedContainers,maxNumberOfLiquidContainers;
    private final double fuelConsumptionPerKM;
    private Port currentPort;
    private int currentWeight = 0;
    private int currAllCont = 0;
    private int currHeavyCont = 0;
    private int currRefCont = 0;
    private int currLiqCont =0;
    private ArrayList<Container> containers = new ArrayList<>();

    public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers,
                int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
                int maxNumberOfLiquidContainers, double fuelConsumptionPerKM){
        this.ID = ID;
        this.currentPort = p;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
        fuel = 0;

    }
    public static void bubbleSortWithIndexes(int[] arr, int[] indexes) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    int tmp = indexes[j];
                    arr[j] = arr[j + 1];
                    indexes[j] = indexes[j+1];
                    arr[j + 1] = temp;
                    indexes[j+1] = tmp;
                }
    }
    public ArrayList<Container> getContainers() {
        int[] ids = new int[containers.size()];
        int[] containerIndexes = new int[containers.size()];
        for (int i = 0; i<containers.size(); i++){
            ids[i] = containers.get(i).getID();
            containerIndexes[i] = i;
        }
        bubbleSortWithIndexes(ids,containerIndexes);
        ArrayList<Container> newContainers = new ArrayList<>();
        for(int con : containerIndexes){
            newContainers.add(containers.get(con));
        }
        containers.clear();
        containers.addAll(newContainers);
    return containers;
    }
    @Override
    public boolean sailTo(Port p) {
        double base = currentPort.getDistance(p);
        double var = fuelConsumptionPerKM;
        for (Container container : containers){
            var += container.consumption();
        }
        return base*var <= fuel;
    }
    public void sailToHelper(Port p){
        if (sailTo(p)){
            double base = currentPort.getDistance(p);
            double var = fuelConsumptionPerKM;
            for (Container container : containers) {
                var += container.consumption();
            }
            fuel -= (base*var);
            currentPort.getCurrent().remove(this);
            currentPort.outgoingShip(this);
            p.incomingShip(this);
            currentPort = p;
        }
    }

    @Override
    public void reFuel(double newFuel) {
        if (newFuel>=0){
            fuel += newFuel;
        }
    }

    @Override
    public boolean load(Container cont) {
        if(currentPort.getContainers().contains(cont)){
            if(totalWeightCapacity >= currentWeight + cont.getWeight()){
                if (maxNumberOfAllContainers > currAllCont){
                      String containerType = cont.getClass().getSimpleName();
                      if (containerType.equals("BasicContainer")){
                          return true;
                      }
                      if (maxNumberOfHeavyContainers > currHeavyCont){
                          if (containerType.equals("HeavyContainer")){
                              currHeavyCont++;
                              return true;
                          }
                          if (containerType.equals("LiquidContainer")){
                              if (maxNumberOfLiquidContainers > currLiqCont){
                                  currHeavyCont++;
                                  currLiqCont++;
                                  return true;
                              }
                          }
                          if (containerType.equals("RefrigeratedContainer")){
                              if (maxNumberOfRefrigeratedContainers > currRefCont){
                                  currHeavyCont++;
                                  currRefCont++;
                                  return true;
                              }
                          }
                      }
                }
            }
        }
    return false;
    }
    public void loader(Container cont){
        if(load(cont)){
            containers.add(cont);
            currentPort.getContainers().remove(cont);
            currentWeight += cont.getWeight();
            currAllCont++;
        }
    }
    @Override
    public boolean unLoad(Container cont) {
        return containers.contains(cont);
    }
    public void unLoader(Container cont) {
        if (unLoad(cont)) {
            containers.remove(cont);
            currentPort.getContainers().add(cont);
            currentWeight -= cont.getWeight();
            currAllCont--;
            String containerType = cont.getClass().getSimpleName();
            if (containerType.equals("HeavyContainer")){
                currHeavyCont--;
            }
            if (containerType.equals("LiquidContainer")){
                currHeavyCont--;
                currLiqCont--;
            }
            if (containerType.equals("RefrigeratedContainer")){
                currHeavyCont--;
                currRefCont--;
            }
        }
    }

    public int getID() {
        return ID;
    }

    public double getFuel() {
        return fuel;
    }

}
