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
	 * Removes all tiles from the container
	 */
	public void removeAllTiles();
	
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
