/**
 * 
 */
package net.skyebook.slippyj;

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

	private TileContainer tileContainer;

	public static final String TilesAtHomeServer = "http://tah.openstreetmap.org/Tiles/tile/";
	public static final String OSMSlippyServer = "http://tile.openstreetmap.org/";

	private TileFactory tileFactory;

	/**
	 * 
	 */
	public Palette(Coordinate center, int zoomLevel, TileContainer tileContainer) {
		this.zoomLevel=zoomLevel;
		this.tileContainer=tileContainer;
		build(center);
	}

	private void build(Coordinate center){
		// create the center tile
		int[] centerXY = getTileXY(center);
		//Tile centerTile = tileFactory.createTile(zoomLevel, centerXY[0], centerXY[1]);
		//centerTile.setLocation((tileContainer.getWidth()/2)-(centerTile.getWidth()/2), (tileContainer.getHeight()/2)-(centerTile.getHeight()/2));
		//tileContainer.addTile(centerTile);

		int halfWidth = tileContainer.getWidth()/2;
		int halfHeight = tileContainer.getHeight()/2;

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

		for(int x=startingX; x<endingX; x++){
			for(int y=startingY; y<endingY; y++){
				Tile tile = tileFactory.createTile(zoomLevel, x, y);
				tile.setLocation(
						(tileContainer.getWidth()/2)+((x-centerXY[0])*tileSize)-(tileSize/2),
						(tileContainer.getHeight()/2)+((y-centerXY[1])*tileSize)-(tileSize/2)
				);
				tileContainer.addTile(tile);
				tile.addTileMouseMotionListener(new ITileMouseMotionListener() {
					
					@Override
					public void mouseDragged(int oldX, int oldY, int newX, int newY) {
						tileContainer.moveTiles(newX-oldX, newY-oldY);
					}
				});
			}
		}
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

}