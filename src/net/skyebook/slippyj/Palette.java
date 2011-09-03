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

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import net.skyebook.slippyj.tile.ITileMouseMotionListener;
import net.skyebook.slippyj.tile.Tile;
import net.skyebook.slippyj.tile.TileFactory;

/**
 * @author Skye Book
 *
 */
public class Palette {

	//private Coordinate southWestCorner;
	//private Coordinate northEastCorner;

	private int zoomLevel;

	@SuppressWarnings("rawtypes")
	private TileContainer tileContainer;

	public static final String TilesAtHomeServer = "http://tah.openstreetmap.org/Tiles/tile/";
	public static final String OSMSlippyServer = "http://tile.openstreetmap.org/";

	private TileFactory tileFactory;
	
	private AtomicBoolean isUpdating = new AtomicBoolean(false);
	
	private Executor builderThread;

	/**
	 * 
	 */
	public Palette(final Coordinate center, int zoomLevel, TileContainer<Tile> tileContainer, TileFactory tileFactory) {
		this.zoomLevel=zoomLevel;
		this.tileContainer=tileContainer;
		this.tileFactory=tileFactory;
		builderThread = Executors.newFixedThreadPool(1);
		builderThread.execute(new Runnable() {
			
			@Override
			public void run() {
				build(center);
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	public void build(Coordinate center){
		isUpdating.set(true);
		tileContainer.removeAllTiles();
		int[] centerXY = getTileXY(center);
		
		/* cache the width and height of the container to avoid inconsistencies
		 * in the event that the container is changed while the build method is
		 * running
		 */
		int widthOnBuildCall = tileContainer.getWidth();
		int heightOnBuildCall = tileContainer.getHeight();
		
		int halfWidth = widthOnBuildCall/2;
		int halfHeight = heightOnBuildCall/2;

		int tileSize = 256;

		int numberXTiles = 0;
		int numberYTiles = 0;

		// how many tiles do we need to render around the center?

		while(numberXTiles*tileSize <= halfWidth){
			numberXTiles++;
		}

		while(numberYTiles*tileSize <= halfHeight){
			numberYTiles++;
		}

		int startingX = centerXY[0]-numberXTiles;
		int startingY = centerXY[1]-numberYTiles;
		int endingX = centerXY[0]+numberXTiles;
		int endingY = centerXY[1]+numberYTiles;
		
		System.out.println("Creating X tiles from " + startingX + " to " + endingX);
		System.out.println("Creating Y tiles from " + startingY + " to " + endingY);

		for(int x=startingX; x<endingX; x++){
			for(int y=startingY; y<endingY; y++){
				int xLocation=(tileContainer.isXInverted()?-1:1)*(widthOnBuildCall/2)+((x-centerXY[0])*tileSize)-(tileSize/2);
				int yLocation=(tileContainer.isYInverted()?-1:1)*(heightOnBuildCall/2)+((y-centerXY[1])*tileSize)-(tileSize/2);
				Tile tile = tileFactory.createTile(zoomLevel, x, y, xLocation, yLocation);
				tile.setLocation(
						xLocation,
						yLocation
				);
				tile.addTileMouseMotionListener(new ITileMouseMotionListener() {
					
					@Override
					public void mouseDragged(int oldX, int oldY, int newX, int newY) {
						tileContainer.moveTiles(newX-oldX, newY-oldY);
					}
				});
				
				System.out.println("Tile {"+x+","+y+"} Created");
				tileContainer.addTile(tile);
			}
		}
		isUpdating.set(false);
	}

	private int[] getTileXY(Coordinate coordinate){
		return getTileXY(coordinate.getLatitude(), coordinate.getLongitude());
	}

	/* This is based on a method found on the OpenStreetMaps wiki:
	 * http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames#Java
	 */
	private int[] getTileXY(double lat, double lon){
		int xTile = (int)Math.floor((lon + 180) / 360 * (1<<zoomLevel)) ;
		int yTile = (int)Math.floor((1 - Math.log(Math.tan(Math.toRadians(lat)) + 1 / Math.cos(Math.toRadians(lat))) / Math.PI) / 2 * (1<<zoomLevel)) ;
		return new int[]{xTile,yTile};
	}
	
	public synchronized boolean isUpdating(){
		return isUpdating.get();
	}
}