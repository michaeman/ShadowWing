import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**Enemy class, extends unit, passes in x,y,imagefile to the constructor in Unit.*/
public abstract class Enemy extends Unit {

	public Enemy(double x, double y, String imageFile) throws SlickException {
		super(x,y,imageFile);
		this.x = x;
		this.y = y;
		this.image = new Image(imageFile);
	}

/**Overriding moveto method that handles collision between enemy and terrains.
 * @param World
 * @param x coord of where Enemy is
 * @param y coord of where Enemy is*/
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
			this.drawObject = false;

		}
		// Else: Check horizontally collision
		else if (!world.terrainBlocks(x + halfPlayerWidth(), this.y - halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x + halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y - halfPlayerHeight()))
		{
			this.x = x;
			this.drawObject = false;
		}
		else {
			this.drawObject = false;
		}
	}  
	/**Abstract update method, so the enemies can handle it themselves.*/
	public abstract void update(World world,int delta, boolean isAlive) throws SlickException;

	/**Resets the enemies to their originally position and restores their shield.*/
	public void resetEnemy() {

		this.x = this.respawnPOSX;
		this.y = this.respawnPOSY;
		this.drawObject = true;
		this.shield = this.getFullShield();

	}

	public int getFullShield()
	{
		return fullShield;
	}
}	
