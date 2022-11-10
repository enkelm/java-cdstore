package model;

import java.io.Serializable;

public class CD implements Serializable{
	
	private int cdID;
	private String Album;
	private String Artist;
	private String Genre;
	private int stock;
	private double price;
	
	public CD(int cdID, String Album, String Artist, String Genre, int stock, double price) {
		this.cdID = cdID;
		this.Album = Album;
		this.Artist = Artist;
		this.Genre = Genre;
		this.stock = stock;
		this.price = price;
	}
	
	public int getCdID() {
		return cdID;
	}

	public void setCdID(int cdID) {
		this.cdID = cdID;
	}

	public String getAlbum() {
		return Album;
	}

	public void setAlbumName(String albumName) {
		Album = albumName;
	}

	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CD [cdID=" + cdID + ", AlbumName=" + Album + ", Artist=" + Artist + ", Genre=" + Genre + ", stock="
				+ stock + ", price=" + price + "]";
	}

	

}
