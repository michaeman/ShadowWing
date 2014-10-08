
import org.newdawn.slick.SlickException;

/** Missile class that extends GameObject, has stats of Missile, damage, shield, collision */
public class Missile extends GameObject {

	private static final int SHIELD = 1;
	private static final int damage = 8;
	private static final String image_file
	= Game.ASSETS_PATH + "/units/missile-player.png";

	private static final String image_fileE
	= Game.ASSETS_PATH + "/units/missile-enemy.png";

	private boolean eMissile;
	public boolean drawMissile = true;

	private int shield = SHIELD;
	
	/** Constructor of Missile
	 * 
	 * @param x
	 * @param y
	 * @param enemymissile
	 * @throws SlickException
	 */
	public Missile(double x, double y, boolean enemymissile) throws SlickException
	{
		super(x,y, enemymissile ? image_fileE : image_file);
		this.eMissile = enemymissile;
	}

	/** Missile speed method
	 * 
	 * @return
	 */
	private double getMissileSpeed()
	{
		return 0.7;
	}

	/** Missile update method, handles lifeCheck, doesnt do anything unless missile is "Alive"
	 * 
	 * @param world
	 * @param delta
	 * @param lifeCheck
	 */
	public void update(World world,int delta, boolean lifeCheck)
	{
		if (lifeCheck)
		{
			double amount = delta * getMissileSpeed();

			if (this.eMissile)
			{
				moveto(world, x, y + amount);	
			} else {
				moveto(world, x, y - amount);	
			}
		}

	}
	/** Method to check if missile has collided with another unit 
	 * 
	 * @param world
	 * @param unit
	 */
	public void missileCollided(World world, Unit unit) 
	{
		if (this.drawMissile)
		{
			unit.setShield(unit.getShield() - Missile.damage);
			this.deadcheck();
		}

	}
	/** Check is missile is dead */
	public void deadcheck()
	{
		if (this.shield <= 0)
		{
			this.drawMissile = false;
		}
		else 
			this.drawMissile = true;
	}

	/** Override moveto method that checks if missile is trying to go it shouldn't
	 * 
	 */
	@Override
	protected void moveto(World world, double x, double y)
	{
		// If the destination is not blocked by terrain, move there
		if (!world.terrainBlocks(x + halfPlayerWidth(), y - halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(x + halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), y - halfPlayerHeight()))
		{
			this.x = x;
			this.y = y;
		}
		// Else: Check vertically collision
		else if (!world.terrainBlocks(this.x + halfPlayerWidth(), y - halfPlayerHeight()) &&
				!world.terrainBlocks(this.x - halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(this.x + halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(this.x - halfPlayerWidth(), y - halfPlayerHeight()))
		{
			this.y = y;

			this.drawMissile = false;
		}
		// Else: Check horizontally collision
		else if (!world.terrainBlocks(x + halfPlayerWidth(), this.y - halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x + halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y - halfPlayerHeight()))
		{
			this.x = x;

			this.drawMissile = false;
		}
	}
}
