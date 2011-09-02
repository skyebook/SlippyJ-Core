/**
 *  SlippyJ - Slippery Maps in Java
 *  Copyright (C) 2011  Skye Book
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
