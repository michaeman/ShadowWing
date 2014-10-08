
import org.newdawn.slick.SlickException;

/** Repair class, extends item class
 * 
 * @author Michael
 *
 */
public class Repair extends Item{

	/** Path to image of item.
	 * 
	 */
	private static final String image_file
	 = Game.ASSETS_PATH + "/items/repair.png";
	

	/** Constructor of Repair
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Repair(double x, double y) throws SlickException
	{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
	}
	
	/** Effect to player when taken
	 * 
	 */
	public void pickup(Player player)
	{
		player.setShield(player.getFullShield());
	}
}
