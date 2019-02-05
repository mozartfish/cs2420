package assignment11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This class represents a graph of flights and airports along with specific data about those
 * flights. It is recommended to create an airport class and a flight class to represent nodes and
 * edges respectively. There are other ways to accomplish this and you are free to explore those.
 *
 * <p>Testing will be done with different criteria for the "best" path so be sure to keep all
 * information from the given file. Also, before implementing this class (or any other) draw a
 * diagram of how each class will work in relation to the others. Creating such a diagram will help
 * avoid headache and confusion later.
 *
 * <p>Be aware also that you are free to add any member variables or methods needed to completed the
 * task at hand
 *
 * @author CS2420 Teaching Staff - Spring 2018, Pranav Rajan and Hank Gansert
 * @version April 18, 2018
 */
public class NetworkGraph {

  /**
   * The representation of the network graph.The network graph is represented as a hash map where
   * the key is a string containing the airport name and the value mapped to the key is an airport
   * object containing the flight data associated with that specific airport. In our representation
   * of a network graph, the nodes of the graph are airport objects and the edges of the graph are
   * flight objects.
   */
  private HashMap<String, Airport> graph;

  /**
   * Constructs a NetworkGraph object and populates it with the information contained in the given
   * file. See the sample files or a randomly generated one for the proper file format.
   *
   * <p>You will notice that in the given files there are duplicate flights with some differing
   * information. That information should be averaged and stored properly. For example some times
   * flights are canceled and that is represented with a 1 if it is and a 0 if it is not. When
   * several of the same flights are reported totals should be added up and then reported as an
   * average or a probability (value between 0-1 inclusive).
   *
   * @param flightInfo - The inputstream to the flight data. The format is a *.csv(comma separated
   *     value) file
   */
  public NetworkGraph(InputStream flightInfo) {
    // create a new graph object to store the airport and flight information
    this.graph = new HashMap<String, Airport>();

    // create a new buffered reader object which will read the the file as an input
    // stream
    // the buffered reader reads text from a character-input stream,
    // buffering characters so as to provide for the efficient reading
    // of characters, arrays, and lines.
    BufferedReader fileReader = new BufferedReader(new InputStreamReader(flightInfo));

    // since we are reading a file as an input stream, there is a possibility errors
    // may occur
    // while reading the file which is why we use a try catch to catch any
    // exceptions that occur
    try {
      // read the first line of the file which contains all the headings of the
      // different columns
      // which describe the information about the flights and airports
      // we initialize the currentLine variable line to this information first in
      // order to
      // input all the information we need before computing the cheapest path
      String currentLine = fileReader.readLine();

      // while there is another line in the file, create an array that stores the
      // flight information
      // In order to do this we split on the comma which allows us to separate each
      // unique piece of
      // information about the flight into separate array indexes since each piece of
      // information is separated by a comma
      while ((currentLine = fileReader.readLine()) != null) {
        String[] flightData = currentLine.split(",");

        // create a new flight object
        // The flight object represents edge in our representation of a network graph
        Flight newFlight = new Flight();

        // Determine whether the origin and destination airports already exist

        // If the graph does not contain the destination airport, create a new airport
        // object that will store information
        // regarding that airport and its flights and add that new airport to the graph
        if (!graph.containsKey(flightData[0])) {
          Airport newAirport = new Airport(flightData[0]);
          graph.put(flightData[0], newAirport);
        }

        // If the graph does not contain the destination airport, create a new airport
        // object that will store information
        // regarding that airport and its flights and add that new airport to the graph
        if (!graph.containsKey(flightData[1])) {
          Airport newAirport = new Airport(flightData[1]);
          graph.put(flightData[1], newAirport);
        }

        // update the information contained in the flight object

        // set the airport of origin of the flight
        newFlight.setOrigin(graph.get(flightData[0]));

        // set the name of the destination of the flight
        newFlight.setDestinationName(flightData[1]);

        // set the destination airport of the flight
        newFlight.setDestination(graph.get(flightData[1]));

        // set the flight carrier
        newFlight.addCarrier(flightData[2]);

        // set the number of times that a flight has been delayed
        String stringValue = flightData[3];
        double doubleValue = Double.parseDouble(stringValue);
        newFlight.setNumberOfTimesDelayed(doubleValue);

        // set the number of times a flight has been canceled
        stringValue = flightData[4];
        doubleValue = Double.parseDouble(stringValue);
        newFlight.setNumberOfTimesCanceled(doubleValue);

        // set the time that a flight takes between two airports
        stringValue = flightData[5];
        doubleValue = Double.parseDouble(stringValue);
        newFlight.setTime(doubleValue);

        // set the distance between two flights
        stringValue = flightData[6];
        doubleValue = Double.parseDouble(stringValue);
        newFlight.setDistance(doubleValue);

        // set the cost of a flight between two airports
        stringValue = flightData[7];
        doubleValue = Double.parseDouble(stringValue);
        newFlight.setCost(doubleValue);

        // update the information of that particular flight between two airports
        graph.get(flightData[0]).addFlight(newFlight);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * This method returns a BestPath object containing information about the best way to fly to the
   * destination from the origin. "Best" is defined by the FlightCriteria parameter <code>enum
   * </code>. This method should throw no exceptions and simply return a BestPath object with
   * information dictating the result. If the destination or origin is not contained in this
   * instance of NetworkGraph, simply return a BestPath object with an empty path (not a <code>null
   * </code> path) and a path cost of 0. If origin or destination are <code>null</code>, also return
   * a BestPath object with an empty path and a path cost of 0 . If there is no path in this
   * NetworkGraph from origin to destination, also return a BestPath with an empty path and a path
   * cost of 0.
   *
   * @param origin - The starting location to find a path from. This should be a 3 character long
   *     string denoting an airport.
   * @param destination - The destination location from the starting airport. Again, this should be
   *     a 3 character long string denoting an airport.
   * @param criteria - This enum dictates the definition of "best". Based on this value a path
   *     should be generated and return.
   * @return - An object containing path information including origin, destination, and everything
   *     in between.
   */
  public BestPath getBestPath(String origin, String destination, FlightCriteria criteria) {
    // TODO: First figure out what kind of path you need to get (HINT: Use a
    // switch!) then
    // Search for the shortest path using Dijkstra's algorithm.

    // create an airport object that represents the starting airport
    Airport start = null;

    // create an airport object that represents the destination airport
    Airport goal = null;

    // create an array list of type string that does not contain anything to represent
    // if the destination or origin is not contained in this instance of NetworkGraph
    ArrayList<String> emptyPath = new ArrayList<String>();

    // if the destination or origin is not contained in this instance of NetworkGraph
    //or if the origin or destination is null then return a path length of 0
    BestPath noPathPossible = new BestPath(emptyPath, 0);

    // create a new comparator that compares the cost for a flight based on the
    // specified flight cost criteria
    Comparator<Airport> comp = (lhs, rhs) -> ((int) lhs.getCost() - (int) rhs.getCost());

    // create a new priority queue with the comparator that will store the airports
    // (nodes) visited
    PriorityQueue<Airport> PQ = new PriorityQueue<Airport>(comp);

    // check if the origin airport is null
    // if it is then return a best path object that contains a path length of 0 and an empty string array list
    // that represents no airports visited
    // otherwise set the start airport to the origin airport
    if (graph.get(origin) != null) {
      start = graph.get(origin);
    } else {
      return noPathPossible;
    }

    // check if the destination airport is null
    // if it is then return a best path object that contains a path length of 0 and an empty string array list
    // that represents no airports visited
    // otherwise set the goal airport to the destination airport
    if (graph.get(destination) != null) {
      goal = graph.get(destination);
    } else {
      return noPathPossible;
    }

    // create an array list to store the airports whose data was modified
    // data modified includes the cost of a flight between two airports, the airport that we just came from, and
    // whether or not the airport has been marked as visited
    ArrayList<Airport> modifiedAirports = new ArrayList<Airport>();

    // create a new stack that will store the names of the airports in the order in
    // which they were visited
    Stack<String> path = new Stack<String>();

    // create a new array list that will store the final path of the flight
    ArrayList<String> finalPath = new ArrayList<String>();

    // create a variable of type double that will store the final cost of the flight
    // between the two airports
    double finalCost = 0.0;

    // initialize the starting cost of a path between two nodes
    start.setStartingNodeCost(); // Starts cost at 0
    modifiedAirports.add(start);

    // initialize the starting node
    Airport current = start;

    // add the starting node to the priority queue
    PQ.add(start);

    // while the priority queue is not empty
    // remove the element in the priority queue and check if it is the element that
    // we are looking for
    // if it is not then we mark it as visited and iterate through all of its
    // neighbors and compare the costs and add them to the priority queue
    // we also update the airports that we came from and update the total cost that
    // we are keeping track of between the airport of origin and the destination
    // airport
    while (!PQ.isEmpty()) {
      current = PQ.remove();
      if (current.getName().equals(goal.getName())) {
        if (criteria.equals(FlightCriteria.CANCELED)) {
          current.setCost(current.getCost() / current.getNumberOfFlightsTaken());
        }
        finalCost = current.getCost();
        while (current.getCameFrom() != null) {
          Airport temp = current;
          path.push(temp.getName());
          current = current.getCameFrom();
        }

        // push the name of the airport onto the path stack to keep track of the
        // airports visited
        path.push(current.getName());

        // while the stack is not empty, pop the elements off the stack and add them to
        // the final
        // path array list to show the shortest path
        while (!path.isEmpty()) {
          finalPath.add(path.pop());
        }

        // create a new best path object that will store the cheapest path and cost
        BestPath result = new BestPath(finalPath, finalCost);

        // reset all the costs of airports whose costs were modified
        resetCosts(modifiedAirports);

        // return the best path
        return result;
      }
      current.setHasBeenVisitedTo(true);
      for (String airport : current.getFlights().keySet()) {
        if (current.getPotentialCost(airport, criteria) > 0) {
          if (!graph.get(airport).hasBeenVisited()) {
            modifiedAirports.add(graph.get(airport));
            if (graph.get(airport).getCost() > current.getPotentialCost(airport, criteria)) {
              graph.get(airport).cameFrom(current);
              graph.get(airport).setCost(current.getPotentialCost(airport, criteria));
              PQ.add(graph.get(airport));
            }
          }
        }
      }
    }
    return noPathPossible;
  }

  /**
   * This overloaded method should do the same as the one above only when looking for paths skip the
   * ones that don't match the given airliner.
   *
   * @param origin - The starting location to find a path from. This should be a 3 character long
   *     string denoting an airport.
   * @param destination - The destination location from the starting airport. Again, this should be
   *     a 3 character long string denoting an airport.
   * @param criteria - This enum dictates the definition of "best". Based on this value a path
   *     should be generated and return.
   * @param airliner - a string dictating the airliner the user wants to use exclusively. Meaning no
   *     flights from other airliners will be considered.
   * @return - An object containing path information including origin, destination, and everything
   *     in between.
   */
  public BestPath getBestPath(
      String origin, String destination, FlightCriteria criteria, String airliner) {

    // create an airport object that represents the starting airport
    Airport start = null;

    // create an airport object that represents the destination airport
    Airport goal = null;

    // create an array list of type string that does not contain anything to represent
    // if the destination or origin is not contained in this instance of NetworkGraph
    ArrayList<String> emptyPath = new ArrayList<String>();

    // if the destination or origin is not contained in this instance of NetworkGraph
    //or if the origin or destination is null then return a path length of 0
    BestPath noPathPossible = new BestPath(emptyPath, 0);

    // create a new comparator that compares the cost for a flight based on the
    // specified flight cost criteria
    Comparator<Airport> comp = (lhs, rhs) -> ((int) lhs.getCost() - (int) rhs.getCost());

    // create a new priority queue with the comparator that will store the airports
    // (nodes) visited
    PriorityQueue<Airport> PQ = new PriorityQueue<Airport>(comp);

    // check if the origin airport is null
    // if it is then return a best path object that contains a path length of 0 and an empty string array list
    // that represents no airports visited
    // otherwise set the start airport to the origin airport
    if (graph.get(origin) != null) {
      start = graph.get(origin);
    } else {
      return noPathPossible;
    }

    // check if the destination airport is null
    // if it is then return a best path object that contains a path length of 0 and an empty string array list
    // that represents no airports visited
    // otherwise set the goal airport to the destination airport
    if (graph.get(destination) != null) {
      goal = graph.get(destination);
    } else {
      return noPathPossible;
    }

    // create an array list to store the airports whose data was modified
    // data modified includes the cost of a flight between two airports, the airport that we just came from, and
    // whether or not the airport has been marked as visited
    ArrayList<Airport> modifiedAirports = new ArrayList<Airport>();

    // create a new stack that will store the names of the airports in the order in
    // which they were visited
    Stack<String> path = new Stack<String>();

    // create a new array list that will store the final path of the flight
    ArrayList<String> finalPath = new ArrayList<String>();

    // create a variable of type double that will store the final cost of the flight
    // between the two airports
    double finalCost = 0.0;

    // initialize the starting cost of a path between two nodes
    start.setStartingNodeCost(); // Starts cost at 0
    modifiedAirports.add(start);

    // initialize the starting node
    Airport current = start;

    // add the starting node to the priority queue
    PQ.add(start);

    // while the priority queue is not empty
    // remove the element in the priority queue and check if it is the element that
    // we are looking for
    // if it is not then we mark it as visited and iterate through all of its
    // neighbors and compare the costs and add them to the priority queue
    // we also update the airports that we came from and update the total cost that
    // we are keeping track of between the airport of origin and the destination
    // airport
    while (!PQ.isEmpty()) {
      current = PQ.remove();
      if (current.getName().equals(goal.getName())) {
        if (criteria.equals(FlightCriteria.CANCELED)) {
          current.setCost(current.getCost() / current.getNumberOfFlightsTaken());
        }
        finalCost = current.getCost();
        while (current.getCameFrom() != null) {
          Airport temp = current;
          path.push(temp.getName());
          current = current.getCameFrom();
        }

        // push the name of the airport onto the path stack in order to keep track of
        // the airports visited for the
        // shortest path
        path.push(current.getName());

        // while the stack is not empty, pop the elements off the stack and add them to
        // the final
        // path array list to show the shortest path
        while (!path.isEmpty()) {
          finalPath.add(path.pop());
        }

        // create a new best path object that will store the cheapest path and cost
        BestPath result = new BestPath(finalPath, finalCost);

        // reset all the costs of airports whose costs were modified
        resetCosts(modifiedAirports);

        // return the best path
        return result;
      }
      current.setHasBeenVisitedTo(true);
      for (String airport : current.getFlights().keySet()) {
        if (current.getPotentialCost(airport, criteria) > 0) {
          if (!graph.get(airport).hasBeenVisited()) {
            modifiedAirports.add(graph.get(airport));
            if (graph.get(airport).getCost() > current.getPotentialCost(airport, criteria)) {
              if (current.getFlight(airport).containsCarrier(airliner)) {
                PQ.add(graph.get(airport));
                graph.get(airport).cameFrom(current);
                graph.get(airport).setCost(current.getPotentialCost(airport, criteria));
              }
            }
          }
        }
      }
    }
    return noPathPossible;
  }

  /**
   * Method that resets the internal data for all the airports whose data was modified to compute
   * the shortest path flight
   *
   * @param modifiedAirports - the airports whose cost was modified
   */
  private void resetCosts(ArrayList<Airport> modifiedAirports) {
    for (Airport current : modifiedAirports) {
      current.reset();
    }
  }
}
