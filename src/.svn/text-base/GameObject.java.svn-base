import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class GameObject {
	
	protected Image image;
	
	protected final int OFFSETHEIGHT = 1;
	protected final int OFFSETWIDTH = 1;
	protected final int SCREENBOTTOM = 0;
	protected final int SCREENTOP = 600;
	protected final int MISSILEY = 50;
	
	protected boolean isAlive;
	protected boolean OnScreen;
	protected boolean drawItem;

	/** The x coordinate of the gameobject, relative to map (pixels). */
	protected double x;
	/** The y coordinate of the gameobject, relative to map (pixels). */
	protected double y;
	
	/** Constructor
	 * @ Construtor 1
	 * @param x
	 * @param y
	 * @param imageFile
	 * @throws SlickException
	 */
	public GameObject(double x, double y, String imageFile) throws SlickException
    {
		this.x = x;
		this.y = y;
		this.image = new Image(imageFile);
	}
	/** Constructor
	 * @ Constructor 2
	 * @param x
	 * @param y
	 */
	public GameObject(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

    protected void render(Graphics g, Camera cam)
	{
    	this.image.drawCentered((float)this.x - (float)cam.getLeft(), (float)this.y - (float)cam.getTop());
	}
    
	/** The x coordinate of the gameobject, relative to map (pixels). */
    public double getX()
    {
        return x;
    }

    /** The y coordinate of the gameobject, relative to map (pixels). */
    public double getY()
    {
        return y;
    }
    
    /** The isOnScreen Checker
     * 
     * @param cam
     * @return
     */
    public boolean isOnScreen(Camera cam){

		if(this.y - cam.getTop() < SCREENBOTTOM || this.y - cam.getTop() > SCREENTOP){
			return false;
		}
		else{
			return true;
		}
	}
    
    /** The Is Colliding checker
     * 
     * @param gameobject
     * @return
     */
    public boolean oncollide(GameObject gameobject)
    {
    	if(Math.abs(this.x - gameobject.getX()) < halfPlayerWidth() && Math.abs(this.y - gameobject.getY()) < halfPlayerWidth())
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /** Checking if object can move in the direction they're trying to
     * 
     * @param world
     * @param x
     * @param y
     */
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
		// Else: Try moving vertically only
		else if (!world.terrainBlocks(this.x + halfPlayerWidth(), y - halfPlayerHeight()) &&
				!world.terrainBlocks(this.x - halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(this.x + halfPlayerWidth(), y + halfPlayerHeight()) &&
				!world.terrainBlocks(this.x - halfPlayerWidth(), y - halfPlayerHeight()))
		{
			this.y = y;
		}
		// Else: Try moving horizontally only
		else if (!world.terrainBlocks(x + halfPlayerWidth(), this.y - halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x + halfPlayerWidth(), this.y + halfPlayerHeight()) &&
				!world.terrainBlocks(x - halfPlayerWidth(), this.y - halfPlayerHeight()))
		{
			this.x = x;
		}
	}
	
	protected double halfPlayerWidth()
	{
		return image.getWidth()/2-OFFSETWIDTH;
	}
	
	protected double halfPlayerHeight()
	{
		return image.getHeight()/2-OFFSETHEIGHT;
	}
	
	 public boolean isOnScreen() {
			return OnScreen;
		}

		public void setOnScreen(boolean onScreen){
			OnScreen = onScreen;
		}

}
