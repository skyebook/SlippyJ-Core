/**
 * 
 */
package net.skyebook.slippyj.tile;

/**
 * @author Skye Book
 *
 */
public abstract class TileFactory{
	
	protected String tileServer;
	
	public TileFactory(String tileServer){
		this.tileServer=tileServer;
	}
	
	/**
	 * 
	 * @param zoomLevel
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract Tile createTile(int zoomLevel, int x, int y);
}
