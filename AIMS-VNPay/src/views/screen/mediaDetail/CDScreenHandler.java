package views.screen.mediaDetail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import entity.media.CD;
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

public class CDScreenHandler extends BaseScreenHandler{
	private static Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());
    @FXML
    private ImageView aimsImage;
    @FXML
    private Label pageTitle;
    @FXML
    private VBox vboxDetail;
    
    public CDScreenHandler(Stage stage, String screenPath, CD cd) throws IOException {
		super(stage, screenPath);
		
		File file = new File("assets/images/Logo.png");
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });
        
        System.out.println(cd);
        
        file = new File(cd.getImageURL());
        Image image = new Image(file.toURI().toString());
        ImageView cdImageView = new ImageView();
        cdImageView.setImage(image);
        cdImageView.setPreserveRatio(true);
        cdImageView.setFitWidth(400);
        
        Label cdTitle = new Label(cd.getTitle());
        cdTitle.setFont(Font.font(40));
        
        Label cdArtist = new Label("Artist: " + cd.getArtist());
        cdArtist.setFont(Font.font(20));
                
        Label cdRecordLabel = new Label("Record label: " + cd.getRecordLabel());
        cdRecordLabel.setFont(Font.font(20));
        
        Label cdReleaseDate = new Label("Release date: " + cd.getReleasedDate().toString());
        cdReleaseDate.setFont(Font.font(20));
        
        Label cdMusicType = new Label("Music type: " + cd.getMusicType());
        cdMusicType.setFont(Font.font(20));
                
        vboxDetail.setAlignment(Pos.CENTER);
        vboxDetail.setSpacing(20);
        vboxDetail.getChildren().add(cdImageView);
        vboxDetail.getChildren().add(cdTitle);
        vboxDetail.getChildren().add(cdArtist);
        vboxDetail.getChildren().add(cdRecordLabel);
        vboxDetail.getChildren().add(cdReleaseDate);
        vboxDetail.getChildren().add(cdMusicType);
        
	}
	
	/**
     * @param prevScreen
     * @throws SQLException
     */
    public void requestToViewCD(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("CD detail");
        show();
    }
}
