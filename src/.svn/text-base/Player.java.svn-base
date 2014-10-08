/* SWEN20003 Object Oriented Software Development
 * Space Game Engine (Sample Project)
 * Author: Matt Giuca <mgiuca>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** The ship which the player controls.
 */
public class Player extends Unit
{
	int [] respawnY;

	private static int FULLSHIELD = 100;
	private static int SHIELD = FULLSHIELD;
	private static final int DAMAGE = 10;
	private static int CAMERAOFFSET = 72;
	private static int ABOVEPANEL = 10;
	/** File path of the player's ship image file. */
	private static final String image_file
	= Game.ASSETS_PATH + "/units/player.png";

	public static final int MAX_FIREPOWER = 3;


	/** Image of the player's ship. */
	//    private Image img;

	public float cooldown = 0;
	private int firePower = 0;
	public boolean ranOver = false;

	public void setRespawnPOSY(double y)
	{
		this.respawnPOSY = y;
	}
	
	public int getDamage() {
		return DAMAGE;
	}

	public float getCooldown() {

		return cooldown;
	}

	public void setCoolDown(float cd)
	{
		cooldown = cd;
	}

	/** The value of the FullShield of the player. */
	public int getFullShield()
	{
		return FULLSHIELD;
	}

	public int getFirePower()
	{
		return firePower;
	}

	/** The number of pixels the player may move per millisecond. */
	private double getSpeed()
	{
		return 0.4;
	}

	public void setShield(int i)
	{
		SHIELD = i;
	}

	/** The number of pixels the player automatically moves per millisecond.
	 */
	private double getAutoSpeed()
	{
		return 0.25;
	}

	public void setFirePower(int i) {

		firePower = i;
	}

	public void setFullShield(int i) {

		FULLSHIELD = i;

	}

	public int getShield()
	{
		return SHIELD;
	}

	/** constructor of player
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Player(double x, double y)
			throws SlickException
			{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
		this.shield = FULLSHIELD;
		this.fullShield = shield;
		this.damage = DAMAGE;
		respawnY = new int[5];
		respawnY[0] = 13716;
		respawnY[1] = 9756;
		respawnY[2] = 7812;
		respawnY[3] = 5796;
		respawnY[4] = 2844;
			}

	/** Draw the Player to the screen at the correct place.
	 * @param g The current Graphics context.
	 * @param cam Camera used to render the current screen.
	 */
	public void render(Graphics g, Camera cam)
	{
		// Calculate the player's on-screen location from the camera
		int screen_x, screen_y;
		screen_x = (int) (this.x - cam.getLeft());
		screen_y = (int) (this.y - cam.getTop());
		image.drawCentered(screen_x, screen_y);
	}
	/** Updates the cd of the player can shoot
	 * 
	 * @param delta
	 */
	public void updateCoolDown(int delta)
	{
		cooldown -=delta;
	}

	/** update for player, handles respawn locations, firing etc
	 * 
	 * @param world
	 * @param cam
	 * @param dir_x
	 * @param dir_y
	 * @param delta
	 * @param isFiring
	 * @throws SlickException
	 */
	public void update(World world, Camera cam, double dir_x, double dir_y,
			int delta, boolean isFiring) throws SlickException
			{
		/* Calculate the amount to move in each direction, based on speed */
		double amount = delta * getSpeed();
		/* The new location */
		double x = this.x + dir_x * amount;
		double y = this.y + dir_y * amount;

		if (!world.reachedTop())
			y -= delta * getAutoSpeed();

		// Check if the player is off the screen, and push back in
		if (x < cam.getLeft())
			x = cam.getLeft();
		if (x > cam.getRight() - 1)
			x = cam.getRight() - 1;
		if (y < cam.getTop())
			y = cam.getTop();
		if (y > cam.getBottom() + ABOVEPANEL)
		{
			this.ranOver = true;
		}
		if (y > cam.getBottom() - CAMERAOFFSET)
		{
			y = cam.getBottom() -CAMERAOFFSET;
		}
			
		
		for (int i=0;i<respawnY.length;i++)
		{
			if (this.getY() < respawnY[i])
			{
				this.setRespawnPOSY(respawnY[i]);
			}
		}
		
		moveto(world, x, y);

		if(isFiring){
			this.fire(world, delta);
		}	else  {
			if(this.getCooldown() > 0)
			{
				this.updateCoolDown(delta);
			}
		}
			}
	
	/** Handles firing, handles cooldown
	 * 
	 * @param world
	 * @param delta
	 * @throws SlickException
	 */
	private void fire(World world, int delta) throws SlickException {
		if (this.getCooldown() <= 0)
		{
			this.setCoolDown(300 - 80 * this.getFirePower());
			Missile addMissile = new Missile(this.getX(), this.getY() - MISSILEY, false);
			world.addMissile(addMissile);
		} else  {
			this.updateCoolDown(delta);
		}
	}
	/** resets the player to closest checkpoint
	 * 
	 */
	public void resetPlayer()
	{
		this.y = this.respawnPOSY;
		this.x = this.respawnPOSX;
		this.drawObject = true;
		this.setShield(this.getFullShield());
	}
	/** Check death
	 * 
	 */
	public void deadcheck()
	{
		if (this.getShield() <= 0)
		{
			this.drawObject = false;
		}
		else 
		this.drawObject = true;
	}


}
