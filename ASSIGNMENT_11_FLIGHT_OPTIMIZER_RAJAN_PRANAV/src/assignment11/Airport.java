package assignment11;

import java.util.HashMap;

/**
 * The airport class represents a node in our representation of a network graph. An airport stores
 * flights (edges), and contains methods that set and get flights between two airports, whether or
 * not an airport has been visited, and the airport that we last visited
 *
 * @author Pranav Rajan and Hank Gansert
 * @version April 18, 2018
 */
public class Airport {
  /**
   * Hash Map that represents the flights between two airports The key represents the name of the
   * airport The value mapped to the key is the flight
   */
  private HashMap<String, Flight> flights;

  /** A string that represents the name of an airport */
  private String airportName;

  /** A double that represents the cost of a flight between two airports */
  private double cost;

  /** An airport that represents the airport that we just came from */
  private Airport cameFrom;

  /** A boolean that represents whether an airport has been visited */
  private boolean hasBeenVisited;

  private double flightsTaken;

  /**
   * The constructor of an airport object. Initializes all the instance variables in airport objects
   *
   * @param name - the name of the airport
   */
  public Airport(String name) {
    //initialize a hash map containing the flights between two airports
    this.flights = new HashMap<String, Flight>();

    //initialize the name of the airport
    this.airportName = name;

    //initialize the cost of a path between two airports
    this.cost = Double.MAX_VALUE;

    //initialize the airport that we have just visited as null
    this.cameFrom = null;

    //initialize the airport that we have just visited to false
    this.hasBeenVisited = false;

    this.flightsTaken = 0;
  }

  /**
   * Method that adds flights (edges) to the airport (node)
   *
   * @param newFlight - the edge that we store in the node
   */
  public void addFlight(Flight newFlight) {
    //if the flight between the airport exists already update that flight's information
    if (flights.containsKey(newFlight.getDestination())) {
      flights.get(newFlight.getDestination()).updateFlightInfo(newFlight);
    }

    //otherwise add the flight to the airport
    else {
      flights.put(newFlight.getDestination(), newFlight);
    }
  }

  /**
   * Method that gets the flights between two airports
   *
   * @return - a hash map that contains the flights between two airports
   */
  public HashMap<String, Flight> getFlights() {
    return this.flights;
  }

  /**
   * Method that returns the airport that we came from
   *
   * @return - an airport object that represents the airport we just came from
   */
  public Airport getCameFrom() {
    return this.cameFrom;
  }

  /**
   * Method that gets the flight between the airport of origin and the destination airport
   *
   * @param destinationAirport - a airport object that represents the destination airport
   * @return - the flights between the airport of origin and the destination airport
   */
  public Flight getFlight(String destinationAirport) {
    return flights.get(destinationAirport);
  }

  /**
   * Method that returns the name of the airport
   *
   * @return - a string that represents the name of the airport
   */
  public String getName() {
    return this.airportName;
  }

  /** Method that initializes the starting cost of a path between two airports */
  public void setStartingNodeCost() {
    this.cost = 0.0;
  }

  /**
   * Method that sets the starting cost of a path between two airports
   *
   * @param cost - a double that represents the cost of a flight based on the flight criteria that
   *     is specified
   */
  public void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * Method that sets the airport we came from last
   *
   * @param previousAirport - a airport that represents the airport that we just came from
   */
  public void cameFrom(Airport previousAirport) {
    this.cameFrom = previousAirport;
  }

  /**
   * Method that gets the cost of a flight between two airports
   *
   * @return - a double that represents the cost of a flight between two airports
   */
  public double getCost() {
    return this.cost;
  }

  /**
   * Method that sets whether an airport has been visited
   *
   * @param condition - a boolean that represents whether or not an airport has been visited
   */
  public void setHasBeenVisitedTo(boolean condition) {
    this.hasBeenVisited = condition;
  }

  /** Method that sets the number of flights taken between two airports */
  public void setFlightsTaken(double number) {
    this.flightsTaken = number;
  }

  /** Method that returns the number of flights taken between two airports */
  public double getNumberOfFlightsTaken() {
    return this.flightsTaken;
  }

  /** Method that returns a boolean that represents whether an airport has been visited */
  public boolean hasBeenVisited() {
    return this.hasBeenVisited;
  }

  /**
   * Method that determines the cost of an individual flight between two airports
   *
   * @param destination - a airport that represents the destination airport
   * @param criteria - an enum that represents the criteria that we will be using to determine the
   *     cheapest flight between to airports
   * @return - the cost of a flight between two airports based on the given flight criteria
   */
  public double getPotentialCost(String destination, FlightCriteria criteria) {
    return (this.getFlight(destination).getDestinationCost(criteria));
  }

  /**
   * Method that resets the cost to max value, airport just visited to null and whether or not an
   * airport has been visited to false
   */
  public void reset() {
    this.cost = Double.MAX_VALUE;
    this.cameFrom = null;
    this.hasBeenVisited = false;
  }
}
