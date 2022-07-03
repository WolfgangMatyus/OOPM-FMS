package FMS.util;

import FMS.entities.Flight;
import FMS.provided.Matcher;

public class OriginMatcher extends Object implements Matcher<Flight> {

    private String origin;

    public OriginMatcher(String o) {
        this.origin = o;
    }

    @Override
    public boolean match(Flight flight) {
        return origin.equals(flight.getOrigin());
    }

}
