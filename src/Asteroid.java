import org.newdawn.slick.SlickException;

/**
 * Asteroid class that extends off Enemy class.
 * Handles movement of Asteroid, defines it's own stats
 */
public class Asteroid extends Enemy{

	private static int SHIELD = 24;
	private static int FULLSHIELD = SHIELD;
	private static final int DAMAGE = 12;
	private static final String image_file
	= Game.ASSETS_PATH + "/units/asteroid.png";

	/**
	 * Asteroid constuctor
	 * @param double x - x coord
	 * @param double y - y coord
	 * Passes in its own stats into enemy as well.*/
	public Asteroid(double x, double y) throws SlickException
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
	
	/** Asteroid Update, handles movement while it is alive.*/
	public void update(World world, int delta, boolean isAlive)
	{
		if (isAlive)
		{
			double amount = delta * 0.2;

			moveto(world, x, y+amount);
			
		}
		
	}
}
