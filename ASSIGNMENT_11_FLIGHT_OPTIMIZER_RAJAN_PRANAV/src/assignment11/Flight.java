package assignment11;

import java.util.LinkedList;

/**
 * The flight class represents an edge in our network graphs. Flight objects contain information
 * about the flight including the airport of origin, destination airport, the number of times a
 * flight has been canceled, the amount of time a flight takes, the total number of flights, and the
 * cost of a flight
 *
 * @author Pranav Rajan and Hank Gansert
 * @version April 18, 2018
 */
public class Flight {

  /** A node that represents the airport from which we start from */
  private Airport origin;

  /** A node that represents the airport that we want to arrive at */
  private Airport destination;

  /** A string that stores the name of the destination airport */
  private String destinationName;

  /**
   * A linked list that contains the carrier of a flight between the airport of origin and the
   * destination airport
   */
  private LinkedList<String> carriers;

  /** A variable of type double that stores the number of times that a flight has been canceled */
  private double numberOfTimesCanceled;

  /** A variable of type double that stores the amount of time that a flight is delayed */
  private double timeOfDelay;

  /** A variable of type double that stores the amount of time for a flight between two airports */
  private double flightTime;

  /** A variable of type double that stores the distance of a flight between two airports */
  private double flightDistance;

  /** A variable of type double that stores the cost of a flight between two airports */
  private double flightCost;

  /**
   * A variable of type double that keeps track of the total number of flights for a specific
   * airport
   */
  private double totalNumberOfFlights;

  /** Constructor that initializes all the instance variables for the flight object */
  public Flight() {
    //initialize the airport from which we start from
    this.origin = null;

    //initialize the airport which we want to arrive at
    this.destination = null;

    //initialize the name of the airport we want to arrive at
    this.destinationName = "";

    //initialize a linked list that will contain the carriers for the flights between two airports
    this.carriers = new LinkedList<String>();

    //initialize the number of times that a flight has been canceled
    this.numberOfTimesCanceled = 0.0;

    //initialize the number of times that a flight has been delayed
    this.timeOfDelay = 0.0;

    //initialize the total number of flights between two airports
    this.totalNumberOfFlights = 1.0;
  }

  /**
   * Method that sets the airport of origin
   *
   * @param origin - airport object that represents the airport that we start from
   */
  public void setOrigin(Airport origin) {
    this.origin = origin;
  }

  /**
   * Method that sets the destination airport
   *
   * @param destination - airport object that represents the airport we want to go to
   */
  public void setDestination(Airport destination) {
    this.destination = destination;
  }

  /**
   * Method that sets the name of the destination airport
   *
   * @param name - a string that represents the name of the destination airport
   */
  public void setDestinationName(String name) {
    this.destinationName = name;
  }

  /**
   * Method that adds a carrier to the linked list of carriers that have flights between two
   * airports
   *
   * @param carrier - a string that represents the carrier of a specific flight
   */
  public void addCarrier(String carrier) {
    carriers.add(carrier);
  }

  /**
   * Method that sums up the number of times a flight has been canceled
   *
   * @param numOfCancelations - a double that represents the number of times a flight has been
   *     canceled
   */
  public void setNumberOfTimesCanceled(double numOfCancelations) {
    this.numberOfTimesCanceled += numOfCancelations;
  }

  /**
   * Method that sums up the number of times a flight has been delayed
   *
   * @param numOfDelays - a double that represents the number of times a flight has been delayed
   */
  public void setNumberOfTimesDelayed(double numOfDelays) {
    this.timeOfDelay += numOfDelays;
  }

  /**
   * Method that sums up the amount of time a flight takes
   *
   * @param time - a double that represents the amount of time a flight takes
   */
  public void setTime(double time) {
    this.flightTime += time;
  }
  /**
   * Method that sums up the distance of a flight between two airports
   *
   * @param distance - a double that represents the distance of a flight
   */
  public void setDistance(double distance) {
    this.flightDistance += distance;
  }
  /**
   * Method that sums up the cost of a flight between two airports
   *
   * @param cost -a double that represents the cost of a flight
   */
  public void setCost(double cost) {
    this.flightCost += cost;
  }
  /**
   * Method that updates the information for a flight between two airports
   *
   * @param flight - the specific flight we are updating
   */
  public void updateFlightInfo(Flight flight) {
    //increment the total number of flights between the two airports
    totalNumberOfFlights = totalNumberOfFlights + 1;

    //check to make sure that there is exactly one carrier for the flight we are updating for the specific carrier
    //between two airports. If the carrier does not exist then add the carrier to the linked list
    if (!this.carriers.contains(flight.carriers.get(0))) {
      this.carriers.add(flight.carriers.get(0));
    }

    //set the number of times the flight has been delayed
    this.setNumberOfTimesDelayed(flight.timeOfDelay);

    //set the number of times the flight has been canceled
    this.setNumberOfTimesCanceled(flight.numberOfTimesCanceled);

    //set the time of the flight
    this.setTime(flight.flightTime);

    //set the distance of the flight
    this.setDistance(flight.flightDistance);

    //set the cost of the flight
    this.setCost(flight.flightCost);
  }

  /**
   * Method that returns the name of the destination airport
   *
   * @return - a string that represents the name of an airport
   */
  public String getDestination() {
    return this.destinationName;
  }

  /**
   * Method that computes the cost of a flight between two airports
   *
   * @param criteria - the enum that represents the criteria that we will use to determine the
   *     cheapest flight between two airports
   * @return - a double that represents the cost of a path between two airports
   */
  public double getDestinationCost(FlightCriteria criteria) {
    switch (criteria) {
      case PRICE:
        double averagePrice = (double) this.flightCost / (double) totalNumberOfFlights;
        return this.origin.getCost() + averagePrice;

      case DELAY:
        double averageDelayTime = (double) this.timeOfDelay / (double) totalNumberOfFlights;
        return this.origin.getCost() + averageDelayTime;

      case DISTANCE:
        double averageDistance = (double) this.flightDistance / (double) totalNumberOfFlights;
        return this.origin.getCost() + averageDistance;

      case CANCELED:
        double oddsOfBeingCanceled =
            (double) this.numberOfTimesCanceled / (double) totalNumberOfFlights;
        double potentialCost = this.origin.getCost() + oddsOfBeingCanceled;
        this.destination.setFlightsTaken(this.origin.getNumberOfFlightsTaken() + 1);
        return potentialCost;

      case TIME:
        double averageTime = (double) this.flightTime / (double) totalNumberOfFlights;
        return this.origin.getCost() + averageTime;

      default:
        return 0.0; // This should not hit, but include if the above cases fail
    }
  }

  /**
   * Method that determines whether or not a carrier operates a flight between two airports
   *
   * @param carrierName - string that represents the name of the carrier
   * @return - a boolean that represents whether or not a carrier operates a flight between two
   *     airports
   */
  public boolean containsCarrier(String carrierName) {
    return carriers.contains(carrierName);
  }
}
