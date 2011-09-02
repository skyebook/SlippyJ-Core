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

	@SuppressWarnings("rawtypes")
	private TileContainer tileContainer;

	public static final String TilesAtHomeServer = "http://tah.openstreetmap.org/Tiles/tile/";
	public static final String OSMSlippyServer = "http://tile.openstreetmap.org/";

	private TileFactory tileFactory;

	/**
	 * 
	 */
	public Palette(Coordinate center, int zoomLevel, TileContainer<Tile> tileContainer, TileFactory tileFactory) {
		this.zoomLevel=zoomLevel;
		this.tileContainer=tileContainer;
		this.tileFactory=tileFactory;
		build(center);
	}

	@SuppressWarnings("unchecked")
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
				int xLocation=(tileContainer.getWidth()/2)+((x-centerXY[0])*tileSize)-(tileSize/2);
				int yLocation=(tileContainer.getHeight()/2)+((y-centerXY[1])*tileSize)-(tileSize/2);
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
				
				System.out.println("Tile Created");
				tileContainer.addTile(tile);
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