package views.screen.mediaDetail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.media.Book;
import entity.media.DVD;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.cart.CartScreenHandler;

public class DVDScreenHandler extends BaseScreenHandler{
	private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());
    @FXML
    private ImageView aimsImage;
    @FXML
    private Label pageTitle;
    @FXML
    private VBox vboxDetail;
    
	public DVDScreenHandler(Stage stage, String screenPath, DVD dvd) throws IOException {
		super(stage, screenPath);
		
		File file = new File("assets/images/Logo.png");
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });
        
        System.out.println(dvd);
        
        file = new File(dvd.getImageURL());
        Image image = new Image(file.toURI().toString());
        ImageView dvdImageView = new ImageView();
        dvdImageView.setImage(image);
        dvdImageView.setPreserveRatio(true);
        dvdImageView.setFitWidth(400);
        
        Label dvdTitle = new Label(dvd.getTitle());
        dvdTitle.setFont(Font.font(40));
        
        Label dvdDirector = new Label("Director: " + dvd.getDirector());
        dvdDirector.setFont(Font.font(20));
                
        Label dvdStudio = new Label("Studio: " + dvd.getStudio());
        dvdStudio.setFont(Font.font(20));
        
        Label dvdReleaseDate = new Label("Release date: " + dvd.getReleasedDate().toString());
        dvdReleaseDate.setFont(Font.font(20));
        
        HBox hbox1 = new HBox(20);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().add(dvdStudio);
        hbox1.getChildren().add(dvdReleaseDate);
        
        Label dvdCategory = new Label("Category: " + dvd.getCategory());
        dvdCategory.setFont(Font.font(20));
        
        Label dvdType = new Label("DVD type: " + dvd.getFilmType());
        dvdType.setFont(Font.font(20));    
        
        HBox hbox2 = new HBox(20);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().add(dvdCategory);
        hbox2.getChildren().add(dvdType);
                
        vboxDetail.setAlignment(Pos.CENTER);
        vboxDetail.setSpacing(20);
        vboxDetail.getChildren().add(dvdImageView);
        vboxDetail.getChildren().add(dvdTitle);
        vboxDetail.getChildren().add(dvdDirector);
        vboxDetail.getChildren().add(hbox1);
        vboxDetail.getChildren().add(hbox2);
        
	}
	
	/**
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewDVD(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("DVD detail");
        show();
    }
}
