/**
 * 
 */
package net.skyebook.slippyj;

/**
 * A Coordinate based on latitude and longitude
 * 
 * @author Skye Book
 *
 */
public class Coordinate {
	
	private double latitude;
	private double longitude;

	/**
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public Coordinate(double latitude, double longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
