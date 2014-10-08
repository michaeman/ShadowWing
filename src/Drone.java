
import org.newdawn.slick.SlickException;

/** Drone Enemy class, extends Enemy,
 * deals with movement of drone, and its stats.
 * */
public class Drone extends Enemy{

	private double distx;
	private double disty;
	private double distt;

	private static int SHIELD = 16;
	private static int FULLSHIELD = SHIELD;
	private static final int DAMAGE = 8;
	private static final String image_file
	= Game.ASSETS_PATH + "/units/drone.png";

	/**
	 * Drone constuctor
	 * @param double x - x coord
	 * @param double y - y coord
	 * Passes in its own stats into enemy as well.*/
	public Drone(double x, double y) throws SlickException
	{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
		this.shield = SHIELD;
		this.fullShield = FULLSHIELD;
		this.damage = DAMAGE;
		this.respawnPOSX = x;
		this.respawnPOSY = y;
	}
	/**Player update, algorithm on how the drone moves towards the player.
	 * @param The World
	 * @param delta - handling the amount to move per frame.
	 * @param isAlive - checking if the unit is alive, does nothing if its dead.*/
	public void update(World world, int delta, boolean isAlive)
	{
		if (isAlive)
		{
			distx = world.getPlayer().getX() - this.x;
			disty = world.getPlayer().getY() - this.y;
			double amount = delta * getSpeed();

			distt = Math.sqrt(Math.pow(distx, 2) + Math.pow(disty, 2));

			double dx = (distx/distt) * amount;
			double dy = (disty/distt) * amount;

			moveto(world,x+dx,y+dy);
		}	
	}
	
	
	private double getSpeed()
	{
		return 0.2;
	}
}
