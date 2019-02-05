package assignment11;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * A series of JUnit tests that the functionality of our flight optimizer project
 * @author Pranav Rajan and Hank Gansert
 * @version April 18, 2018
 *
 */
public class NetworkGraphJUnit {
  NetworkGraph dataForTies;
  NetworkGraph dataSetForLoop;
  NetworkGraph dataForCancelation;

  @Before
  public void setUp() throws Exception {

    dataForTies = null;
    try (FileInputStream fio = new FileInputStream(new File("dataForTies.csv"));
        BufferedInputStream bufferedInput = new BufferedInputStream(fio)) {
      dataForTies = new NetworkGraph(bufferedInput);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    dataSetForLoop = null;
    try (FileInputStream fio = new FileInputStream(new File("dataSetForLoop.csv"));
        BufferedInputStream bufferedInput = new BufferedInputStream(fio)) {
      dataSetForLoop = new NetworkGraph(bufferedInput);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    dataForCancelation = null;
    try (FileInputStream fio = new FileInputStream(new File("CancelationTests.csv"));
        BufferedInputStream bufferedInput = new BufferedInputStream(fio)) {
      dataForCancelation = new NetworkGraph(bufferedInput);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testForDelay() {
    BestPath result = null;
    double expectedCost = 1000;
    ArrayList<String> expectedPath = new ArrayList<String>();
    expectedPath.add("SFO");
    expectedPath.add("RNO");
    expectedPath.add("DFW");
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataSetForLoop.getBestPath("SFO", "DFW", FlightCriteria.DELAY);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testForTies() {
    BestPath result = null;
    double expectedCost = 400;
    ArrayList<String> expectedPath = new ArrayList<String>();
    expectedPath.add("SFO");
    expectedPath.add("RNO");
    expectedPath.add("LAX");
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("SFO", "LAX", FlightCriteria.PRICE);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testForCancelation() {
    BestPath result = null;
    double expectedCost = .5;
    ArrayList<String> expectedPath = new ArrayList<String>();
    expectedPath.add("SFO");
    expectedPath.add("LAX");
    expectedPath.add("DFW");
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForCancelation.getBestPath("SFO", "DFW", FlightCriteria.CANCELED);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testForTime() {
    BestPath result = null;
    double expectedCost = 50;
    ArrayList<String> expectedPath = new ArrayList<String>();
    expectedPath.add("SFO");
    expectedPath.add("RNO");
    expectedPath.add("JAC");
    expectedPath.add("SLC");
    expectedPath.add("LAX");
    expectedPath.add("DFW");
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForCancelation.getBestPath("SFO", "DFW", FlightCriteria.TIME);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testWhenOriginDoesNotExist() {
    BestPath result = null;
    double expectedCost = 0;
    ArrayList<String> expectedPath = new ArrayList<String>();
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("JAC", "DFW", FlightCriteria.PRICE);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testWhenDestinationDoesNotExist() {
    BestPath result = null;
    double expectedCost = 0;
    ArrayList<String> expectedPath = new ArrayList<String>();
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("SFO", "JAC", FlightCriteria.PRICE);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testWhenAFlightPathIsNotPossibleBetweenAirports() {
    BestPath result = null;
    double expectedCost = 0;
    ArrayList<String> expectedPath = new ArrayList<String>();
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("DFW", "RNO", FlightCriteria.PRICE);
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testWhenCarrierIsAFactor() {
    BestPath result = null;
    double expectedCost = 400;
    ArrayList<String> expectedPath = new ArrayList<String>();
    expectedPath.add("SFO");
    expectedPath.add("DFW");
    expectedPath.add("LAX");
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("SFO", "LAX", FlightCriteria.PRICE, "AA");
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }

  @Test
  public void testWhenSpecifiedCarrierDoesNotHaveAFlightPathBetweenOriginAndDestination() {
    BestPath result = null;
    double expectedCost = 0;
    ArrayList<String> expectedPath = new ArrayList<String>();
    String expectedResult = "Path Length: " + expectedCost + "\nPath: " + expectedPath;
    result = dataForTies.getBestPath("SFO", "RNO", FlightCriteria.PRICE, "AA");
    String resultingString = result.toString();
    assertTrue(expectedResult.equals(resultingString));
  }
}
