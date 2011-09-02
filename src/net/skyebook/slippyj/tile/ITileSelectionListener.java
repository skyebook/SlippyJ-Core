/**
 * 
 */
package net.skyebook.slippyj.tile;

import net.skyebook.slippyj.Coordinate;

/**
 * @author Skye Book
 *
 */
public interface ITileSelectionListener {
	/**
	 * 
	 * @param locationOfSelection
	 * @param localPixelX
	 * @param localPixelY
	 */
	public void tileSelected(Coordinate locationOfSelection, int localPixelX, int localPixelY);
}
