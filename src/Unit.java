import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** ABstract Unit class
 * 
 * @author Michael
 *
 */
public abstract class Unit extends GameObject {
	

	protected int fullShield;
	protected int shield = fullShield;
	protected int damage;
	
	protected double respawnPOSY;
	protected double respawnPOSX = 1296;
	
	public boolean drawObject = true;
	
    /** The value of Shield of the Unit. */
    public int getShield()
    {
    	return shield;
    }
    
    public int getDamage()
    {
    	return damage;
    }
    
	public void setShield(int i)
	{
		shield = i;
	}

	/** Constructor of unit
	 * 
	 * @param x
	 * @param y
	 * @param imageFile
	 * @throws SlickException
	 */
	public Unit(double x, double y, String imageFile) throws SlickException {
		super(x, y,imageFile);
		this.x = x;
		this.y = y;
		this.image = new Image(imageFile);
	}

	/** Method CollidedWith, that handles when unit is collided
	 * 
	 * @param enemy2
	 * @param collided
	 */
	public void collidedWith(Unit enemy2, boolean collided)
	{
		if (collided && this.drawObject)
		{
			this.setShield(this.getShield() - enemy2.getDamage());
			enemy2.setShield(enemy2.getShield() - this.getDamage());
			this.deadcheck();
		}
	}
	
	/** Checking if the unit is dead, with respect to its shield
	 * 
	 */
	public void deadcheck()
	{
		if (this.shield <= 0)
		{
			this.drawObject = false;
		}
		else 
		this.drawObject = true;
	}
}
