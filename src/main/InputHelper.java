package main;

import containers.*;
import ports.Port;
import ships.Ship;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHelper {
    int currentContainerID = 0;
    int currentPortID = 0;
    int currentShipID = 0;
    ArrayList<Container> containerArrayList = new ArrayList<>();
    ArrayList<Port> portArrayList = new ArrayList<>();
    ArrayList<Ship> shipArrayList = new ArrayList<>();
    public void help (Scanner in){
        final int N = in.nextInt();
            for(int inputs = 0; inputs<N; inputs++){
            int sw = in.nextInt();
            switch (sw) {
                case 1:{ // Create New Container
                    int portID = in.nextInt();
                    int containerWeight = in.nextInt();
                    Container newContainer;
                    if (in.hasNextInt()){
                        if(containerWeight <= 3000){
                            newContainer = new BasicContainer(currentContainerID,
                                    containerWeight);
                        }
                        else{
                            newContainer = new HeavyContainer(currentContainerID,
                                    containerWeight);
                        }
                    }
                    else {
                        String containerType = in.next();
                        if (containerType.equals("L")){
                            newContainer = new LiquidContainer(currentContainerID,
                                    containerWeight);
                        }
                        else{
                            newContainer = new RefrigeratedContainer(currentContainerID,
                                    containerWeight);
                        }
                    }
                    containerArrayList.add(newContainer);
                    portArrayList.get(portID).getContainers().add(newContainer);
                    currentContainerID++;
                    break;
                }
                case 2:{ // Create new Ship
                    int initialPortID = in.nextInt();
                    int maxWeight = in.nextInt();
                    int maxContainers = in.nextInt();
                    int maxHeavyContainers = in.nextInt();
                    int maxRefrigeratedContainers = in.nextInt();
                    int maxLiquidContainers = in.nextInt();
                    double fuelConsPerKm = in.nextDouble();
                    Ship newShip = new Ship(currentShipID,portArrayList.get(initialPortID),
                            maxWeight,
                            maxContainers,
                            maxHeavyContainers,
                            maxRefrigeratedContainers,
                            maxLiquidContainers,
                            fuelConsPerKm);
                    shipArrayList.add(newShip);
                    portArrayList.get(initialPortID).incomingShip(newShip);
                    currentShipID++;
                    break;
                }
                case 3:{ // Create new Port
                    double XPosition = in.nextDouble();
                    double YPosition = in.nextDouble();
                    portArrayList.add(new Port(currentPortID,XPosition,YPosition));
                    currentPortID++;
                    break;
                }
                case 4:{
                    int shipID = in.nextInt();
                    int containerID = in.nextInt();
                    shipArrayList.get(shipID).loader(containerArrayList.get(containerID));
                    break;
                }
                case 5:{
                    int shipID = in.nextInt();
                    int containerID = in.nextInt();
                    shipArrayList.get(shipID).unLoader(containerArrayList.get(containerID));
                    break;
                }
                case 6:{
                    int shipID = in.nextInt();
                    int travelToPortID = in.nextInt();
                    shipArrayList.get(shipID).sailToHelper(portArrayList.get(travelToPortID));
                    break;
                }
                case 7:{
                    int shipID = in.nextInt();
                    double addFuel = in.nextDouble();
                    shipArrayList.get(shipID).reFuel(addFuel);
                    break;
                }
            }
        }
    }
}
