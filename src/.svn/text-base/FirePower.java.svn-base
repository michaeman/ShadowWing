import org.newdawn.slick.SlickException;

/**Firepower class, extends item class*/
public class FirePower extends Item{

	/** Image path of firepower pic*/
	private static final String image_file
	 = Game.ASSETS_PATH + "/items/firepower.png";
	

	/** Constructor of FirePower,
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public FirePower(double x, double y) throws SlickException
	{
		super(x,y,image_file);
		this.x = x;
		this.y = y;
	}
	
	/** pickup effect,
	 * @param Player, cause it changes players stats.
	 */
	public void pickup(Player player)
	{
		if (player.getFirePower() < Player.MAX_FIREPOWER)
		{
			player.setFirePower(player.getFirePower() + 1);
		}
	}
	

}
