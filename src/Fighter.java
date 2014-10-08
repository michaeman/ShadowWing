import org.newdawn.slick.SlickException;

/**
 * Fighter class that extends off Enemy class.
 * Handles movement of fighter, defines it's own stats
 */
public class Fighter extends Enemy{
	
	private static int SHIELD = 24;
	private static int FULLSHIELD = SHIELD;
	private static final int DAMAGE = 9;
	private static final String image_file
	= Game.ASSETS_PATH + "/units/fighter.png";

	private int firePower = 0;
	private float cooldown = 0;
	
	/**
	 * Fighter constuctor
	 * @param double x - x coord
	 * @param double y - y coord
	 * Passes in its own stats into enemy as well.*/
	public Fighter(double x, double y) throws SlickException
	{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
		this.shield = SHIELD;
		this.damage = DAMAGE;
		this.fullShield = FULLSHIELD;
		this.respawnPOSX = x;
		this.respawnPOSY = y;
	}
    
	/** Fighter update, Only when it is alive, it will move down, and fire when cooldown is <= 0.*/
	public void update(World world, int delta, boolean isAlive) throws SlickException
	{
		
		if (isAlive)
		{
			if (cooldown <= 0)
			{
				this.fire(world, delta);
			}	else  {
				if(this.getCooldown() > 0)
				{
					this.updateCoolDown(delta);
				}
			}

			double amount = delta * getAutoSpeed();

			moveto(world,x,y+amount);
		}
		
	}
	
	/**Gets moving speed of boss.
	 * */
	private double getAutoSpeed()
	{
		return 0.2;
	}
	/** A method that updates the boss cooldown.
	 * */
	protected void updateCoolDown(int delta)
	{
		cooldown -=delta;
	}

	/** Method to handle all the firing components, checking cooldown etc.*/
	private void fire(World world, int delta) throws SlickException {
		if (this.getCooldown() <= 0)
		{

			this.setCoolDown(300 - 80 * this.getFirePower());
			Missile addMissile = new Missile(this.getX(), this.getY() + MISSILEY, true);
			world.addMissile(addMissile);
		} else  {
			this.updateCoolDown(delta);
		}

	}

	private int getFirePower() {

		return firePower;
	}

	public float getCooldown() {

		return cooldown;
	}

	public void setCoolDown(float cd)
	{
		cooldown = cd;
	}
}
