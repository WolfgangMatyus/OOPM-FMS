package FMS.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import FMS.provided.Aircraft;
import FMS.provided.DateTime;
import FMS.provided.Passenger;
import FMS.provided.Staff;

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


	private String destination;
	private String flightID;
	private String origin;
	private DateTime arrival;
	private DateTime departure;
	private Aircraft vessel;
	private java.util.Set<Staff> crew = new HashSet<>();
	private java.util.Set<Passenger> passengers = new HashSet<>();




	public Flight(String flightID, String origin, String destination, DateTime departure, DateTime arrival){
		setFlightID(flightID);
		setOrigin(origin);
		setDestination(destination);
		setDeparture(departure);
		setArrival(arrival);
	}

	public Flight(Flight f){
		setFlightID(flightID);
		setOrigin(origin);
		setDestination(destination);
		setDeparture(new DateTime(f.departure));
		setArrival(new DateTime(f.arrival));
		vessel = new Aircraft(f.vessel);
		crew = new HashSet<>();
		for(Staff s : f.crew){
			crew.add(new Staff(s));
		}
		passengers = new HashSet<>();
		for(Passenger p : f.passengers) {
		 	passengers.add(new Passenger(p));
		}
	}

	private final String ensureNonNullNonEmpty(String str){
		if(str == null || str.isEmpty())
			throw new IllegalArgumentException();
		return str;
	}

	public final void setDestination(String destination) {
		this.destination = ensureNonNullNonEmpty(destination);
	}

	public final void setFlightID(String flightID) {
		this.flightID = ensureNonNullNonEmpty(flightID);
	}

	public final String getFlightID() {
		return flightID;
	}
	public final void setOrigin(String origin) {
		this.origin = ensureNonNullNonEmpty(origin);
	}

	public final String getOrigin() {
		return origin;
	}

	public final void setArrival(DateTime arrival) {
		this.arrival = new DateTime(arrival);
	}

	public final void setDeparture(DateTime departure) {
		this.departure = new DateTime(departure);
	}

	public DateTime getDeparture() {
		return new DateTime(departure);
	}

	public void setVessel(Aircraft vessel) {
		this.vessel = vessel;
	}

	public abstract int getBonusMiles();

	public int compareTo(Flight o){
		// if flightID is integer
		//Integer.compare(this.flightID, o.flightID);
		return this.flightID.compareTo(o.flightID);
	}

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

	public boolean add(Staff staff){
		return crew.add(staff);
	}

	public boolean add(Passenger passenger){
		return passengers.add(passenger);
	}

	public boolean readyToBoard(){
		return crew.size() > 0;
	}

	public boolean boardingCompleted(){
		if(passengers.size() < 1)
			return false;

		for(Passenger p : passengers) {
			if (p.getBoarded().equals(this) == false)
				return false;
		}
	return true;
	}

	public boolean board(Passenger p){
		if (readyToBoard() == false)
			return false;

		if (passengers.contains(p) && p.getBoarded() == null){
			p.setBoarded(this);
			return true;
		}
		return false;
	}

}


