package FMS.util;

import FMS.entities.Flight;

public class DepartureComparator implements java.util.Comparator<Flight>{


    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getDeparture().compareTo(o2.getDeparture());
    }
}
