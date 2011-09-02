/**
 * 
 */
package net.skyebook.slippyj;

import net.skyebook.slippyj.tile.Tile;

/**
 * @author Skye Book
 *
 */
public interface TileContainer {

	/**
	 * 
	 * @param tile
	 */
	public void addTile(Tile tile);
	
	/**
	 * 
	 * @param tile
	 */
	public void removeTile(Tile tile);
	
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

}
