package controller;

import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;
import entity.user.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ManagerHomeController extends BaseController {
	public List getAllMedia() throws SQLException {
		return new Media().getAllMedia();
	}

	public int getCountMedia(String type) throws SQLException {
		return new Media().getCountMedia(type);
	}

	public List getAllBook() throws SQLException {
		return new Book().getAllMedia();
	}

	public void createBook(int id, String title, String category, int price, int value, int quantity, String type,
						   String author, String coverType, String publisher, Date publishDate, int numOfPages, String language,
						   String bookCategory, String imageUrl) throws SQLException {
		new Book().createBook(id, title, category, price, value, quantity, type, author, coverType, publisher,
				publishDate, numOfPages, language, bookCategory, imageUrl);
	}

	public void updateBook(int id, String title, String category, int price, int value, int quantity, String type,
						   String author, String coverType, String publisher, Date publishDate, int numOfPages, String language,
						   String bookCategory) throws SQLException {
		new Book().updateBook(id, title, category, price, value, quantity, type, author, coverType, publisher,
				publishDate, numOfPages, language, bookCategory);
	}

	public void deleteBook(int id) throws SQLException {
		new Book().deleteBook(id);
	}

	// Methods for CD
	public List getAllCD() throws SQLException {
		return new CD().getAllMedia();
	}

	public void createCD(int id, String title, String category, int price, int value, int quantity, String type,
						 String artist, String recordLabel, String musicType, Date releaseDate, String imageUrl) throws SQLException {
		// Tạo đối tượng CD mới và sử dụng hàm createCD trong lớp CD
		CD cd = new CD();
		cd.createCD(id, title, category, price, value, quantity, type, artist, recordLabel, musicType, releaseDate, imageUrl);
	}

	public void updateCD(int id, String title, String category, int price, int value, int quantity, String type,
						 String artist, String recordLabel, String musicType, Date releaseDate) throws SQLException {
		// Tạo đối tượng CD mới và sử dụng hàm updateCD trong lớp CD
		CD cd = new CD();
		cd.updateCD(id, title, category, price, value, quantity, type, artist, recordLabel, musicType, releaseDate);
	}

	public void deleteCD(int id) throws SQLException {
		// Tạo đối tượng CD mới và sử dụng hàm deleteCD trong lớp CD
		CD cd = new CD();
		cd.deleteCD(id);
	}


	// Methods for DVD
	
	public void createDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String discType, String director, int runtime, String studio, String subtitles,Date releasedDate,
			String filmType, String imageUrl) throws SQLException {
		DVD dvd = new DVD();
		dvd.createDVD(id, title, category, price, value, quantity, type, discType, director, runtime, studio, subtitles, releasedDate, filmType, imageUrl);
	}
	public void updateDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String discType, String director, int runtime, String studio, String subtitles, Date releasedDate,
			String filmType) throws SQLException {
		DVD dvd = new DVD();
		dvd.updateDVD(id, title, category, price, value, quantity, type, discType, director, runtime, studio, subtitles, releasedDate, filmType);
	}
	
	public void deleteDVD(int id) throws SQLException {
		DVD dvd = new DVD();
		dvd.deleteDVD(id);
	}
	public List getAllDVD() throws SQLException {
		return new DVD().getAllMedia();
	}

	//	public void createDVD(int id, String title, String category, int price, int value, int quantity, String type,
//			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType,
//			String imageUrl) throws SQLException {
//		new DVD().createDVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
//				releasedDate, filmType, imageUrl);
//	}
//
//	public void updateDVD(int id, String title, String category, int price, int value, int quantity, String type,
//			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType)
//			throws SQLException {
//		new DVD().updateDVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
//				releasedDate, filmType);
//	}
//
//	public void deleteDVD(int id) throws SQLException {
//		new DVD().deleteDVD(id);
//	}
//	public List getAllUser() throws SQLException {
//		return new User().getAllUser();
//	}
//
//	public void createUser(String name, String address, String email, String phone) throws SQLException {
//		User  user = new User();
//		user.createUser(name, address, email, phone);
//	}
//
//	public void updateUser(int id, String name, String address, String email, String phone) throws SQLException {
//		User  user = new User();
//		user.updateUser(id, name, address, email, phone);
//	}
//
//	public void deleteUser(int id) throws SQLException {
//		User user = new User();
//		user.deleteUser(id);
//	}
//
//	public void banUser(int id, boolean gt) throws SQLException {
//		User user = new User();
//		user.banUser(id, gt);
//	}
//
//	public void update(int id, String title, String category, int price, int value, int quantity, String type,
//						 String artist, String recordLabel, String musicType, Date releaseDate) throws SQLException {
//		// Tạo đối tượng CD mới và sử dụng hàm updateCD trong lớp CD
//		CD cd = new CD();
//		cd.updateCD(id, title, category, price, value, quantity, type, artist, recordLabel, musicType, releaseDate);
//	}
//
//	public void deleteCD(int id) throws SQLException {
//		// Tạo đối tượng CD mới và sử dụng hàm deleteCD trong lớp CD
//		CD cd = new CD();
//		cd.deleteCD(id);
//	}
}
