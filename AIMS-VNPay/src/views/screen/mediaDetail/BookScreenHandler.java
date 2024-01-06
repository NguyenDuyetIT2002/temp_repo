package views.screen.mediaDetail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.media.Book;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;

public class BookScreenHandler extends BaseScreenHandler{
	
    private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());
    @FXML
    private ImageView aimsImage;
    @FXML
    private Label pageTitle;
    @FXML
    private VBox vboxDetail;
    
	public BookScreenHandler(Stage stage, String screenPath, Book book) throws IOException {
		super(stage, screenPath);
		
		File file = new File("assets/images/Logo.png");
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });
        
        System.out.println(book);
        
        file = new File(book.getImageURL());
        Image image = new Image(file.toURI().toString());
        ImageView bookImageView = new ImageView();
        bookImageView.setImage(image);
        bookImageView.setPreserveRatio(true);
        bookImageView.setFitWidth(400);
        //bookImageView.setTranslateX(400);
        
        Label bookTitle = new Label(book.getTitle());
        bookTitle.setFont(Font.font(40));
        
        Label bookAuthor = new Label("Author: " + book.getAuthor());
        bookAuthor.setFont(Font.font(20));
                
        Label bookPublisher = new Label("Publisher: " + book.getPublisher());
        bookPublisher.setFont(Font.font(20));
        
        Label bookPublicDate = new Label("Public date: " + book.getPublishDate().toString());
        bookPublicDate.setFont(Font.font(20));
        
        HBox hbox1 = new HBox(20);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().add(bookPublisher);
        hbox1.getChildren().add(bookPublicDate);
        
        Label bookLanguage = new Label("Language: " + book.getLanguage());
        bookLanguage.setFont(Font.font(20));
        
        Label bookCategory = new Label("Category: " + book.getBookCategory());
        bookCategory.setFont(Font.font(20));
        
        Label bookPageNum = new Label("Number of pages: " + book.getNumOfPages());
        bookPageNum.setFont(Font.font(20));    
        
        HBox hbox2 = new HBox(20);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().add(bookCategory);
        hbox2.getChildren().add(bookPageNum);
                
        vboxDetail.setAlignment(Pos.CENTER);
        vboxDetail.setSpacing(20);
        vboxDetail.getChildren().add(bookImageView);
        vboxDetail.getChildren().add(bookTitle);
        vboxDetail.getChildren().add(bookAuthor);
        vboxDetail.getChildren().add(hbox1);
        vboxDetail.getChildren().add(bookLanguage);
        vboxDetail.getChildren().add(hbox2);
        
	}
	
	/**
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewBook(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Book detail");
        show();
    }
}
