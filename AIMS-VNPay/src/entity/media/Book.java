package entity.media;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Book extends Media {

    private static Logger LOGGER = Utils.getLogger(Media.class.getName());

    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book() throws SQLException {

    }

    public Book(int id, String title, String category, int price, int value, int quantity, String type, String author,
                String coverType, String publisher, Date publishDate, int numOfPages, String language,
                String bookCategory) throws SQLException {
        super(id, title, category, price, value, quantity, type);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }


    /**
     * @return int
     */
    // getter and setter
    public int getId() {
        return this.id;
    }


    /**
     * @return String
     */
    public String getAuthor() {
        return this.author;
    }


    /**
     * @param author
     * @return Book
     */
    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }


    /**
     * @return String
     */
    public String getCoverType() {
        return this.coverType;
    }


    /**
     * @param coverType
     * @return Book
     */
    public Book setCoverType(String coverType) {
        this.coverType = coverType;
        return this;
    }


    /**
     * @return String
     */
    public String getPublisher() {
        return this.publisher;
    }


    /**
     * @param publisher
     * @return Book
     */
    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }


    /**
     * @return Date
     */
    public Date getPublishDate() {
        return this.publishDate;
    }


    /**
     * @param publishDate
     * @return Book
     */
    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }


    /**
     * @return int
     */
    public int getNumOfPages() {
        return this.numOfPages;
    }


    /**
     * @param numOfPages
     * @return Book
     */
    public Book setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
        return this;
    }


    /**
     * @return String
     */
    public String getLanguage() {
        return this.language;
    }


    /**
     * @param language
     * @return Book
     */
    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }


    /**
     * @return String
     */
    public String getBookCategory() {
        return this.bookCategory;
    }


    /**
     * @param bookCategory
     * @return Book
     */
    public Book setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
        return this;
    }


    /**
     * @param id
     * @return Media
     * @throws SQLException
     */
    @Override
    public Book getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM " +
                "Book " +
                "INNER JOIN Media " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            // from Media table
            String title = res.getString("title");
            String type = res.getString("type");
            String imageURL = res.getString("imageURL");
            int price = res.getInt("price");
            int value = res.getInt("value");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");

            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = res.getDate("publishDate");
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");

            Book book = new Book(id, title, category, price, value, quantity, type,
                    author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
            book.imageURL = imageURL;
            
            return book;

        } else {
            throw new SQLException();
        }
    }


    /**
     * @return List
     */
    @Override
    public List getAllMedia() throws SQLException {
        String sql = "SELECT * FROM " +
                "Book " +
                "INNER JOIN Media " +
                "ON Media.id = Book.id " + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        ArrayList bookList = new ArrayList<>();
        while (res.next()) {

            // from Media table
            int id = res.getInt(1);
            String title = res.getString("title");
            String type = res.getString("type");
//            String imageURL = res.getString("imageURL");
            int price = res.getInt("price");
            int value = res.getInt("value");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");


            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = res.getDate("publishDate");
//            LOGGER.info("Quantity" + publishDate);
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");

            Book book = new Book(id, title, category, price, value, quantity, type,
                    author, coverType, publisher, publishDate, numOfPages, language, bookCategory);
            bookList.add(book);
        }
        return bookList;
    }
    
    public void createBook(int id, String title, String category, int price, int value, int quantity, String type, String author,
            String coverType, String publisher, Date publishDate, int numOfPages, String language,
            String bookCategory, String imageUrl) {
    		String insertMediaSql = "INSERT INTO Media (id, title, category, price, value, quantity, type, imageUrl) VALUES (" +
    				id + ", '" + title + "', '" + category + "', " + price + ", " + value + ", " + quantity + ", '" +
    				type + "', '" + imageUrl + "')";
    		String insertBookSql = "INSERT INTO Book (id, author, coverType, publisher, publishDate, numOfPages, language, bookCategory) VALUES (" +
    				id + ", '" + author + "', '" + coverType + "', '" + publisher + "', '" +
    				publishDate + "', " + numOfPages + ", '" + language + "', '" +
    				bookCategory + "')";
//LOGGER.info(insertBookSql);
//LOGGER.info(insertMediaSql);
    		try {
    			Statement stm = AIMSDB.getConnection().createStatement();
    			stm.executeUpdate(insertBookSql);
    			stm.executeUpdate(insertMediaSql);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }


    public void updateBook(int id, String title, String category, int price, int value, int quantity, String type, String author,
            String coverType, String publisher, Date publishDate, int numOfPages, String language,
            String bookCategory) {
// Update Media table
    		String updateMediaSql = "UPDATE Media SET " +
    				"title = '" + title + "', " +
    				"category = '" + category + "', " +
    				"price = " + price + ", " +
    				"value = " + value + ", " +
    				"quantity = " + quantity + ", " +
    				"type = '" + type + "' " +
    				"WHERE id = " + id;

// Update Book table
    		String updateBookSql = "UPDATE Book SET " +
    							"author = '" + author + "', " +
    							"coverType = '" + coverType + "', " +
    							"publisher = '" + publisher + "', " +
    							"publishDate = '" + publishDate + "', " +
    							"numOfPages = " + numOfPages + ", " +
    							"language = '" + language + "', " +
    							"bookCategory = '" + bookCategory + "' " +
    							"WHERE id = " + id;

    		try {
    			Statement stm = AIMSDB.getConnection().createStatement();
    			stm.executeUpdate(updateMediaSql);
    			stm.executeUpdate(updateBookSql);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }

    public void deleteBook(int id) {
        String deleteMediaSql = "DELETE FROM Media WHERE id = " + id;
        String deleteBookSql = "DELETE FROM Book WHERE id = " + id;

        try {
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(deleteMediaSql);
            stm.executeUpdate(deleteBookSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                super.toString() +
                " author='" + author + "'" +
                ", coverType='" + coverType + "'" +
                ", publisher='" + publisher + "'" +
                ", publishDate='" + publishDate + "'" +
                ", numOfPages='" + numOfPages + "'" +
                ", language='" + language + "'" +
                ", bookCategory='" + bookCategory + "'" +
                "}";
    }
}
