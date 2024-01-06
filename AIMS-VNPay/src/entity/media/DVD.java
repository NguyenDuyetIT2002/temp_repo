package entity.media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.db.AIMSDB;

public class DVD extends Media {

	String discType;
	String director;
	int runtime;
	String studio;
	String subtitles;
	Date releasedDate;
	String filmType;

	public DVD() throws SQLException {

	}

	public DVD(int id, String title, String category, int price, int value, int quantity, String type, String discType,
			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType)
			throws SQLException {
		super(id, title, category, price, value, quantity, type);
		this.discType = discType;
		this.director = director;
		this.runtime = runtime;
		this.studio = studio;
		this.subtitles = subtitles;
		this.releasedDate = releasedDate;
		this.filmType = filmType;
	}

	/**
	 * @return String
	 */
	public String getDiscType() {
		return this.discType;
	}

	/**
	 * @param discType
	 * @return DVD
	 */
	public DVD setDiscType(String discType) {
		this.discType = discType;
		return this;
	}

	/**
	 * @return String
	 */
	public String getDirector() {
		return this.director;
	}

	/**
	 * @param director
	 * @return DVD
	 */
	public DVD setDirector(String director) {
		this.director = director;
		return this;
	}

	/**
	 * @return int
	 */
	public int getRuntime() {
		return this.runtime;
	}

	/**
	 * @param runtime
	 * @return DVD
	 */
	public DVD setRuntime(int runtime) {
		this.runtime = runtime;
		return this;
	}

	/**
	 * @return String
	 */
	public String getStudio() {
		return this.studio;
	}

	/**
	 * @param studio
	 * @return DVD
	 */
	public DVD setStudio(String studio) {
		this.studio = studio;
		return this;
	}

	/**
	 * @return String
	 */
	public String getSubtitles() {
		return this.subtitles;
	}

	/**
	 * @param subtitles
	 * @return DVD
	 */
	public DVD setSubtitles(String subtitles) {
		this.subtitles = subtitles;
		return this;
	}

	/**
	 * @return Date
	 */
	public Date getReleasedDate() {
		return this.releasedDate;
	}

	/**
	 * @param releasedDate
	 * @return DVD
	 */
	public DVD setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
		return this;
	}

	/**
	 * @return String
	 */
	public String getFilmType() {
		return this.filmType;
	}

	/**
	 * @param filmType
	 * @return DVD
	 */
	public DVD setFilmType(String filmType) {
		this.filmType = filmType;
		return this;
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "{" + super.toString() + " discType='" + discType + "'" + ", director='" + director + "'" + ", runtime='"
				+ runtime + "'" + ", studio='" + studio + "'" + ", subtitles='" + subtitles + "'" + ", releasedDate='"
				+ releasedDate + "'" + ", filmType='" + filmType + "'" + "}";
	}

	/**
	 * @param id
	 * @return Media
	 * @throws SQLException
	 */
	@Override
	public DVD getMediaById(int id) throws SQLException {
		String sql = "SELECT * FROM " + "DVD " + "INNER JOIN Media " + "ON Media.id = DVD.id "
				+ "where Media.id = " + id + ";";
		Statement stm = AIMSDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if (res.next()) {

			// from media table
            String title = res.getString("title");
			String type = res.getString("type");
			int price = res.getInt("price");
			int value = res.getInt("value");
			String category = res.getString("category");
			int quantity = res.getInt("quantity");
			// from DVD table
			String discType = res.getString("discType");
			String director = res.getString("director");
			int runtime = res.getInt("runtime");
			String studio = res.getString("studio");
			String subtitles = res.getString("subtitle");
			Date releasedDate = res.getDate("releasedDate");
			String filmType = res.getString("filmType");

			return new DVD(id, title, type, price, value, quantity, category, discType, director, runtime, studio,
					subtitles, releasedDate, filmType);

		} else {
			throw new SQLException();
		}
	}

	public void createDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String discType, String director, int runtime, String studio, String subtitles,Date releasedDate,
			String filmType, String imageUrl) {
		String insertMediaSql = "INSERT INTO Media (id, title, category, price, value, quantity, type, imageUrl) VALUES ("
				+ id + ", '" + title + "', '" + category + "', " + price + ", " + value + ", " + quantity + ", '" + type
				+ "', '" + imageUrl + "')";

		String insertDvdSql = "INSERT INTO DVD (id, discType, director, runtime, studio, subtitle, releasedDate, filmType) VALUES ("
				+ id + ", '" + discType + "', '" + director + "', " + runtime + ", '" + studio + "', '" + subtitles
				+ "', '" + releasedDate + "', '" + filmType + "')";

		try {
			Statement stm = AIMSDB.getConnection().createStatement();
			stm.executeUpdate(insertMediaSql);
			stm.executeUpdate(insertDvdSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String discType, String director, int runtime, String studio, String subtitles, Date releasedDate,
			String filmType) {
		String updateMediaSql = "UPDATE Media SET " + "title = '" + title + "', " + "category = '" + category + "', "
				+ "price = " + price + ", " + "value = " + value + ", " + "quantity = " + quantity + ", " + "type = '"
				+ type + "' " + "WHERE id = " + id;

		String updateDvdSql = "UPDATE DVD SET " + "discType = '" + discType + "', " + "director = '" + director + "', "
				+ "runtime = " + runtime + ", " + "studio = '" + studio + "', " + "subtitle = '" + subtitles + "', "
				+ "releasedDate = '" + new java.sql.Date(releasedDate.getTime()) + "', " + "filmType = '" + filmType
				+ "' " + "WHERE id = " + id;

		try {
			Statement stm = AIMSDB.getConnection().createStatement();
			stm.executeUpdate(updateMediaSql);
			stm.executeUpdate(updateDvdSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDVD(int id) {
	    String deleteDvdSql = "DELETE FROM DVD WHERE id = " + id;
	    String deleteMediaSql = "DELETE FROM Media WHERE id = " + id;

	    try {
	        Statement stm = AIMSDB.getConnection().createStatement();
	        // It's important to delete from the DVD table first because of foreign key constraints
	        stm.executeUpdate(deleteDvdSql);
	        stm.executeUpdate(deleteMediaSql);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * @return List
	 */
	@Override
	public List getAllMedia() throws SQLException {
	    String sql = "SELECT * FROM DVD " +
	                 "INNER JOIN Media ON Media.id = DVD.id;";
	    
	    Statement stm = AIMSDB.getConnection().createStatement();
	    ResultSet res = stm.executeQuery(sql);
	    
	    ArrayList DVDList = new ArrayList<>();
	    
	    while (res.next()) {
	        // from Media table
	        int id = res.getInt(1);
	        String title = res.getString("title");
	        String type = res.getString("type");
	        int price = res.getInt("price");
	        int value = res.getInt("value");
	        String category = res.getString("category");
	        int quantity = res.getInt("quantity");

	        // from DVD table
	        String discType = res.getString("discType");
	        String director = res.getString("director");
	        int runtime = res.getInt("runtime");
	        String studio = res.getString("studio");
	        String subtitles = res.getString("subtitle");
	        Date releasedDate = res.getDate("releasedDate");
	        String filmType = res.getString("filmType");

	        DVD dvd = new DVD(id, title, category, price, value, quantity, type, discType, director, runtime, studio,
	                          subtitles, releasedDate, filmType);

	        DVDList.add(dvd);
	    }
	    
	    return DVDList;
	}

}