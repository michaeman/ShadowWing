

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	/** Map, containing terrain tiles. */
	private TiledMap map;
	/** The player's ship. */
	private Player player;
	/** The camera. */
	private Camera camera;

	private Panel panel;

	public Missile missile;

	private String objectName;

	public GameObject game;

	private static final String image_file
	= Game.ASSETS_PATH + "/units/enemypos.txt";

	private static final String item_image_file
	= Game.ASSETS_PATH + "/items/itempos.txt";

	/** Creating the ArrayList's to store Missile,Enemy, and items.
	 * 
	 */
	protected ArrayList<Missile> arrayOfMissile = new ArrayList<Missile>();
	protected ArrayList<Enemy> arrayOfEnemy = new ArrayList<Enemy>();
	protected ArrayList<Item> arrayOfItem = new ArrayList<Item>();
	
	/** Creating GetPlayer, so can be used to call player
	 * 
	 * @return
	 */
	public Player getPlayer()
	{
		return player;
	}

	public Missile getMissile()
	{
		return missile;
	}

	/** Get the width of the game world in pixels. */
	public int getWidth()
	{
		return map.getWidth() * map.getTileWidth();
	}

	/** Get the height of the game world in pixels. */
	public int getHeight()
	{
		return map.getHeight() * map.getTileHeight();
	}



	/** Create a new World object. */
	public World()
			throws SlickException
			{
		map = new TiledMap(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);
		player = new Player(1296, 13716);
		//        1296, 13716 1301, 1000
		// Create a camera, centred and with the player at the bottom
		camera = new Camera(this, player);
		panel = new Panel();
		/** Get info from Scanner
		 * 
		 */
		getInfoEnemy();
		getInfoItem();
			}

	/** True if the camera has reached the top of the map. */
	public boolean reachedTop()
	{
		return camera.getTop() <= 0;
	}

	/** Update the game state for a frame.
	 * @param dir_x The player's movement in the x axis (-1, 0 or 1).
	 * @param dir_y The player's movement in the y axis (-1, 0 or 1).
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(double dir_x, double dir_y, int delta, boolean firing)
			throws SlickException
			{
		// Move the camera automatically
		camera.update(delta);

		// Move the player automatically, and manually (by dir_x, dir_y)
		player.update(this, camera, dir_x, dir_y, delta, firing);

		// Centre the camera (in x-axis) over the player and bound to map
		camera.follow (player);
		
		
		/** Used an iterator so can go thru the array and delete a missile if its off the screen of collided with enemy.
		 * 
		 */
		for (Iterator <Missile> iter =  arrayOfMissile.iterator(); iter.hasNext();)
		{
			Missile missile = iter.next();
			for(Enemy enemy:arrayOfEnemy)
			{
				if (missile.oncollide(enemy) && enemy.drawObject)
				{
					missile.missileCollided(this, enemy);
					enemy.deadcheck();
					iter.remove();
				}
			}
			if (missile.oncollide(player))
			{
				missile.missileCollided(this, player);
				iter.remove();
			}
		}

		/** Iterating thru missile again for when its terrain blocked.
		 * 
		 */
		for (Iterator <Missile> iter =  arrayOfMissile.iterator(); iter.hasNext();)
		{
			Missile missile = iter.next();
			missile.update(this, delta,missile.drawMissile);

			if (this.terrainBlocks(missile.getX(), missile.getY()) == true)
			{
				iter.remove();
			}

			if (missile.isOnScreen(camera) == false || missile.drawMissile == false)
			{
				iter.remove();
			}

		}

		/** Checking if the enemy is blocked by terrain
		 * 
		 */
		for (Enemy enemy1: arrayOfEnemy)
		{
			if (this.terrainBlocks(enemy1.getX(), enemy1.getY()) == true)
			{
				enemy1.drawObject = false;
			}
			
			if(enemy1.isOnScreen(camera))
			{
				if (enemy1.oncollide(player))
				{
					enemy1.collidedWith(player, true);
				}
				enemy1.update(this, delta,enemy1.drawObject);

				for (Enemy enemy2: arrayOfEnemy)
				{
					if (enemy1.oncollide(enemy2) && enemy1 != enemy2 && enemy1.drawObject)
					{
						enemy1.collidedWith(enemy2,true);
					}
				}
			}
		}

		for (Item item:arrayOfItem)
		{
			item.isPickedUp(player);
		}
		
		player.deadcheck();
		if (!player.drawObject || player.ranOver)
		{
			player.resetPlayer();
			camera.centerOnPlayer(this, player);
			player.ranOver = false;
			for (Enemy enemy: arrayOfEnemy)
			{
				enemy.resetEnemy();
			}
		}
		
			}


	/** Render the entire screen, so it reflects the current game state.
	 * @param g The Slick graphics object, used for drawing.
	 * @param textrenderer A TextRenderer object.
	 */
	public void render(Graphics g)
			throws SlickException
			{
		// Calculate the camera location (in tiles) and offset (in pixels)
		int cam_tile_x = (int) camera.getLeft() / map.getTileWidth();
		int cam_offset_x = (int) camera.getLeft() % map.getTileWidth();
		int cam_tile_y = (int) camera.getTop() / map.getTileHeight();
		int cam_offset_y = (int) camera.getTop() % map.getTileHeight();
		// Render w+1 x h+1 tiles (where w and h are 12x9; add one tile extra
		// to account for the negative offset).
		map.render(-cam_offset_x, -cam_offset_y, cam_tile_x, cam_tile_y,
				getScreenTileWidth()+1, getScreenTileHeight()+1);

		// Render the player
		player.render(g, camera);

		for (Enemy enemy:arrayOfEnemy)
		{
			if (enemy.drawObject)
				enemy.render(g,camera);
		}


		for (Item item:arrayOfItem)
		{
			if (item.drawItem){
				item.render(g,camera);
			}
		}

		for(Missile missile:arrayOfMissile)
		{	
			missile.render(g,camera);

		}

		panel.render(g,player.getShield(), player.getFullShield(), player.getFirePower());

			}

	/** Determines whether a particular map location blocks movement due to
	 * terrain.
	 * @param x Map x coordinate (in pixels).
	 * @param y Map y coordinate (in pixels).
	 * @return true if the location blocks movement due to terrain.
	 */
	public boolean terrainBlocks(double x, double y)
	{
		int tile_x = (int) x / map.getTileWidth();
		int tile_y = (int) y / map.getTileHeight();
		// Check if the location is off the map. If so, assume it doesn't
		// block (potentially allowing ships to fly off the map).
		if (tile_x < 0 || tile_x >= map.getWidth()
				|| tile_y < 0 || tile_y >= map.getHeight())
			return false;
		// Get the tile ID and check whether it blocks movement.
		int tileid = map.getTileId(tile_x, tile_y, 0);
		String block = map.getTileProperty(tileid, "block", "0");
		return Integer.parseInt(block) != 0;
	}

	/** Get the width of the screen in tiles, rounding up.
	 * For a width of 800 pixels and a tilewidth of 72, this is 12.
	 */
	private int getScreenTileWidth()
	{
		return (Game.screenwidth / map.getTileWidth()) + 1;
	}

	/** Get the height of the screen in tiles, rounding up.
	 * For a height of 600 pixels and a tileheight of 72, this is 9.
	 */

	private int getScreenTileHeight()
	{
		return (Game.screenheight / map.getTileHeight()) + 1;
	}

	/** SCanners
	 * 
	 * @throws SlickException
	 */
	public void getInfoEnemy() throws SlickException
	{
		try
		{
			Scanner fileIn = new Scanner(
					new FileReader(image_file));
			while(fileIn.hasNext())
			{
				objectName = fileIn.next();
				double x = fileIn.nextDouble();
				double y = fileIn.nextDouble();

				if (objectName.equals("Fighter"))
				{
					arrayOfEnemy.add(new Fighter(x,y));

				}
				if (objectName.equals("Asteroid"))
				{
					arrayOfEnemy.add(new Asteroid(x,y));
				}
				if (objectName.equals("Drone"))
				{
					arrayOfEnemy.add(new Drone(x,y));
				}
				if (objectName.equals("Boss"))
				{
					arrayOfEnemy.add(new Boss(x,y));
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			System.exit(0);
		}
	}

	public void getInfoItem() throws SlickException
	{
		try
		{
			Scanner fileIn = new Scanner(
					new FileReader(item_image_file));
			while(fileIn.hasNext())
			{
				objectName = fileIn.next();
				double x = fileIn.nextDouble();
				double y = fileIn.nextDouble();

				if (objectName.equals("Repair"))
				{
					arrayOfItem.add(new Repair(x,y));

				}
				if (objectName.equals("Shield"))
				{
					arrayOfItem.add(new Shield(x,y));
				}
				if (objectName.equals("Firepower"))
				{
					arrayOfItem.add(new FirePower(x,y));
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			System.exit(0);
		}
	}

	/** Creating the missile object to be used
	 * 
	 * @param missile
	 */
	public void addMissile(Missile missile){
		this.arrayOfMissile.add(missile);
	}
}
