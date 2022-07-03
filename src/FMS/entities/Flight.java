package FMS.entities;

import java.util.Set;
import java.util.TreeSet;

import FMS.provided.Aircraft;
import FMS.provided.DateTime;

/**
 * A flight in the flight management system (FMS).<br>
 * 
 * A flight stores information about place and time of departure and arrival as
 * well as the aircraft and personnel involved. Both crew and passengers can be
 * added to a flight. A flight is ready for boarding as soon as one crew member
 * is assigned (added to the flight). Passengers can be listed (added) and only
 * listed passengers can board the flight. Passengers can only board one flight
 * at a time.
 * 
 * 
 * Flights are naturally ordered by ID.
 * 
 *
 */
public abstract class Flight implements Comparable<Flight> {

	/**
	 * Creates a String representation of this flight.<br>
	 * 
	 * @see Object#toString()
	 * @ProgrammingProblem.Hint provided
	 */
	@Override
	public String toString() {
		return String.format(
				"%5s from %3.3S (%s) to %3.3S (%s)" + " with a crew of %d and %d passengers "
						+ "<%s> boarding%scompleted \n%s\n%s",
				flightID, origin, departure, destination, arrival, 
				crew == null ? 0 : crew.size(),
				passengers == null ? 0 : passengers.size(),
				vessel == null ? "no vessel" : vessel.toString(),
				boardingCompleted() ? " " : " not ",
				crew,
				passengers);
	}

}
