
import org.newdawn.slick.SlickException;

/** Shield class extending item class.
 * 
 * @author Michael
 *
 */
public class Shield extends Item{

	private static final int INCREASESHIELD = 40;
	private static final String image_file
	 = Game.ASSETS_PATH + "/items/shield.png";
	

	/** COnstructor of Shield
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Shield(double x, double y) throws SlickException
	{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
	}
	/** Effect on player when it is taken by player
	 * 
	 */
	public void pickup(Player player)
	{
		player.setFullShield(player.getFullShield() + INCREASESHIELD);
		player.setShield(player.getShield() + INCREASESHIELD);
	}
	

}
