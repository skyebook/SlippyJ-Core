/**
 * 
 */
package net.skyebook.slippyj;

import net.skyebook.slippyj.tile.Tile;

/**
 * @author Skye Book
 *
 */
public interface TileContainer<T extends Tile>{

	/**
	 * 
	 * @param tile
	 */
	public void addTile(T tile);
	
	/**
	 * 
	 * @param tile
	 */
	public void removeTile(T tile);
	
	/**
	 * 
	 * @return
	 */
	public int getWidth();
	
	/**
	 * 
	 * @return
	 */
	public int getHeight();
	
	/**
	 * 
	 * @param xDelta
	 * @param yDelta
	 */
	public void moveTiles(int xDelta, int yDelta);
	
	public boolean isXInverted();
	
	public boolean isYInverted();

}
