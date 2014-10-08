import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

/** Item class that extends GameObject */
public abstract class Item extends GameObject {

	public boolean drawItem = true;

	/** Constructor for item
	 * 
	 * @param x
	 * @param y
	 * @param imageFile
	 * @throws SlickException
	 */
	public Item(double x, double y, String imageFile) throws SlickException {
		super(x,y,imageFile);
		this.x = x;
		this.y = y;
		this.image = new Image(imageFile);
	}

	/** method that detects if player is touching an item
	 *
	 * @param player
	 */
	public void isPickedUp(Player player){
		if(Math.abs(this.x - player.getX()) < MISSILEY && Math.abs(this.y - player.getY()) < MISSILEY && this.drawItem == true){
			this.drawItem = false;
			this.pickup(player);
		}
	}
	
	/** Method that determines the effects to do to player, handled in individual classes
	 * @
	 * @param player
	 */
	public abstract void pickup(Player player);

}
