package ethics;

import ethics.Scenario.Actor;
import ethics.Scenario.Actor.Attribute;
import ethics.Scenario.Bystander;
import ethics.Scenario.Lane;

public class EthicsLab {
  /**
   * Given a scenario similar to the ones found in The Moral Machine calculate the 'cost' of the
   * scenario. The self-driving car will choose a scenario that 'costs' the least.
   *
   * @param scenario
   * @return
   */
  public boolean shouldSwitch(Scenario scenario) {
    return false;
  }

  public double cost(Scenario scenario) {
    return CountBabies(scenario.currentLane);
  }

  private double CountBabies(Lane currentLane) {
    // TODO Auto-generated method stub
    double cost = 0;
    for (Actor actor : currentLane.actors) {
      for (Attribute attribute : actor.getAttributes()) {
        switch (attribute) {
          case BABY:
            cost += 10.0;
            break;
          case EXECUTIVE:
            cost += 5.0;
            break;
          case FEMALE:
            cost += 2.0;
            break;
          case MALE:
            cost += 2.0;
            break;
          case DOCTOR:
            cost += 15.0;
            break;
          default:
            cost += 1.0;
        }
      }
    }
    return cost * (currentLane.bystander.equals(Bystander.PEDESTRIAN) ? 1.0 : 3.0);
  }

  public static void main(String[] args) {
    new Simulator().runSimulation(100_000);
  }
}
