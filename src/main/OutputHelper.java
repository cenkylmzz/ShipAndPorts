package main;

import containers.*;
import ports.Port;
import ships.Ship;

import java.io.PrintStream;
import java.util.ArrayList;

public class OutputHelper {
    public String str(double doubleNumber){
        return String.format("%.2f", doubleNumber).replace(',','.');
    }
    public void srt(ArrayList<Container> containers, String indent, PrintStream out) {
        ArrayList<Integer> basicContainers = new ArrayList<>();
        ArrayList<Integer> heavyContainers = new ArrayList<>();
        ArrayList<Integer> refrigeratedContainers = new ArrayList<>();
        ArrayList<Integer> liquidContainers = new ArrayList<>();
        for (Container container : containers) {
            if (container instanceof BasicContainer) {
                basicContainers.add(container.getID());
            }
            if (container instanceof RefrigeratedContainer) {
                refrigeratedContainers.add(container.getID());
            } else if (container instanceof LiquidContainer) {
                liquidContainers.add(container.getID());
            } else if (container instanceof HeavyContainer) {
                heavyContainers.add(container.getID());
            }
        }
        basicContainers.sort(null);
        heavyContainers.sort(null);
        refrigeratedContainers.sort(null);
        liquidContainers.sort(null);
        if (!basicContainers.isEmpty()) {
            out.print(indent + "  BasicContainer:");
            for (Integer basicContainer : basicContainers) {
                out.print(" " + basicContainer);
            }
            out.println();
        }
        if (!heavyContainers.isEmpty()) {
            out.print(indent + "  HeavyContainer:");
            for (Integer heavyContainer : heavyContainers) {
                out.print(" " + heavyContainer);
            }
            out.println();
        }
        if (!refrigeratedContainers.isEmpty()) {
            out.print(indent + "  RefrigeratedContainer:");
            for (Integer refrigeratedContainer : refrigeratedContainers) {
                out.print(" " + refrigeratedContainer);
            }
            out.println();
        }
        if (!liquidContainers.isEmpty()) {
            out.print(indent + "  LiquidContainer:");
            for (Integer liquidContainer : liquidContainers) {
                out.print(" " + liquidContainer);
            }
            out.println();
        }
    }
    public void finalize(PrintStream out , InputHelper iH){
        for (Port port : iH.portArrayList) {
            out.println("Port " + port.getID() + ":" + " (" + str(port.getX()) + ", " + str(port.getY()) + ")");
            srt(port.getContainers(), "", out);
            for (Ship ship : port.getCurrent()) {
                out.println("  " + "Ship " + ship.getID() + ": " + str(ship.getFuel()));
                srt(ship.getContainers(), "  ", out);
            }
        }
    }
}
