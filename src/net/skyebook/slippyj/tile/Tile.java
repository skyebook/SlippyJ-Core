/**
 * 
 */
package net.skyebook.slippyj.tile;

import net.skyebook.slippyj.Coordinate;

/**
 * @author Skye Book
 *
 */
public interface Tile{
	
	/**
	 * 
	 * @return
	 */
	public double getMinimumLatitude();
	
	/**
	 * 
	 * @return
	 */
	public double getMinimumLongitude();
	
	/**
	 * 
	 * @return
	 */
	public double getMaximumLatitude();
	
	/**
	 * 
	 * @return
	 */
	public double getMaximumLongitude();
	
	/**
	 * 
	 * @return
	 */
	public Coordinate getCenter();
	
	/**
	 * 
	 * @param tileSelectionListener
	 */
	public void addTileSelectionListener(ITileSelectionListener tileSelectionListener);
	
	/**
	 * 
	 * @param tileMouseMotionListener
	 */
	public void addTileMouseMotionListener(ITileMouseMotionListener tileMouseMotionListener);
	
	/**
	 * 
	 * @return
	 */
	public int getZoomLevel();
	
	/**
	 * 
	 * @return
	 */
	public int getTileXValue();
	
	/**
	 * 
	 * @return
	 */
	public int getTileYValue();
	
	/**
	 * 
	 * @return
	 */
	public boolean isDisplayed();
	
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
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y);
	
	/**
	 * 
	 * @param displayed
	 * @return
	 */
	public boolean setDisplayed(boolean displayed);
	
}
