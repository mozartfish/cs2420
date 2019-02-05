package rng;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {
  
  long multiplier, offset;
  private long seed;
  
	
  @Override
  public int nextInt(int max) {
    seed = (int) ((seed * multiplier + offset));
    return (int) Math.abs(seed % max);
  }

	@Override
	public void setSeed(long seed) {
	  this.seed = seed;
	}

	@Override
	public void setConstants(long const1, long const2) {
		this.multiplier = const1;
		this.offset = const2;
	}
}
