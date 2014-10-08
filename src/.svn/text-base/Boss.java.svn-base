
import org.newdawn.slick.SlickException;

/**Boss class that extends Enemy class
 * Movement of boss, Fire, and its cooldown.*/
public class Boss extends Enemy{
	
	
	private static int BOSSLEFT = 1013;
	private static int BOSSRIGHT = 1589;
	private static int SHIELD = 240;
	private static int FULLSHIELD = SHIELD;
	private static final int DAMAGE = 100;
	private static final int FIREPOWER = 3;
	private static final String image_file
	= Game.ASSETS_PATH + "/units/boss.png";
	private static int direction = 1;
	private float cooldown = 0;

	/**
	 * Constructor of Boss updating x,y,and boss stats.
	 * @param double x - x coord
	 * @param double y - y coord
	 * */
	public Boss(double x, double y) throws SlickException
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
	
	/** Boss update, Only when it is alive, it will move left and right, and fire when cooldown is <= 0.*/
	public void update(World world, int delta, boolean isAlive) throws SlickException
	{
		if (isAlive)
		{
			this.x += getAutoSpeed() * delta * direction;
			if(this.x < BOSSLEFT || this.x  > BOSSRIGHT)
				direction = direction * -1;

			if (cooldown <= 0)
			{
				this.fire(world, delta);
			}	else  {
				if(this.getCooldown() > 0)
				{
					this.updateCoolDown(delta);
				}
			}
		}
		
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
	
	/** A method that updates the boss cooldown.
	 * */
	protected void updateCoolDown(int delta)
	{
		cooldown -=delta;
	}
	
	/**Gets moving speed of boss.
	 * */
	protected double getAutoSpeed()
	{
		return 0.2;
	}

	/**Gets firepower
	 * */
	protected int getFirePower() {

		return FIREPOWER;
	}
	/**Gets cooldown
	 * */
	protected float getCooldown() {

		return cooldown;
	}
	/**sets cooldown
	 * */
	protected void setCoolDown(float cd)
	{
		cooldown = cd;
	}

}
