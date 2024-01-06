package views.screen.home;

import common.exception.MediaNotAvailableException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class MediaHandler extends FXMLScreenHandler {

    private static Logger LOGGER = Utils.getLogger(MediaHandler.class.getName());
    @FXML
    protected ImageView mediaImage;
    @FXML
    protected Label mediaTitle;
    @FXML
    protected Label mediaPrice;
    @FXML
    protected Label mediaAvail;
    @FXML
    protected Spinner<Integer> spinnerChangeNumber;
    @FXML
    protected Button addToCartBtn;
    @FXML
    protected Button viewBtn;
    
    
    private Media media;
    private HomeScreenHandler home;

    public MediaHandler(String screenPath, Media media, HomeScreenHandler home) throws SQLException, IOException {
        super(screenPath);
        this.media = media;
        this.home = home;
        addToCartBtn.setOnMouseClicked(event -> {
            try {
            	System.out.println(media.getQuantity());
                if (spinnerChangeNumber.getValue() > media.getQuantity()) throw new MediaNotAvailableException();
                Cart cart = Cart.getCart();
                // if media already in cart then we will increase the quantity by 1 instead of create the new cartMedia
                CartMedia mediaInCart = home.getBController().checkMediaInCart(media);
                if (mediaInCart != null) {
                    mediaInCart.setQuantity(mediaInCart.getQuantity() + 1);
                } else {
                    CartMedia cartMedia = new CartMedia(media, cart, spinnerChangeNumber.getValue(), media.getPrice());
                    cart.getListMedia().add(cartMedia);
                }

                // subtract the quantity and redisplay
                media.setQuantity(media.getQuantity() - spinnerChangeNumber.getValue());
                mediaAvail.setText(String.valueOf(media.getQuantity()));
                home.getNumMediaCartLabel().setText(String.valueOf(cart.getTotalMedia() + " media"));
                PopupScreen.success("The media " + media.getTitle() + " added to Cart");
            } catch (MediaNotAvailableException exp) {
                try {
                    String message = "Not enough media:\nRequired: " + spinnerChangeNumber.getValue() + "\nAvail: " + media.getQuantity();
                    LOGGER.severe(message);
                    PopupScreen.error(message);
                } catch (Exception e) {
                    LOGGER.severe("Cannot add media to cart: ");
                }

            } catch (Exception exp) {
                LOGGER.severe("Cannot add media to cart: ");
                exp.printStackTrace();
            }
        });
        viewBtn.setOnMouseClicked(event -> {
        	//System.out.print(media.getType());
        	if (Objects.equals(media.getType(), "book")) {
        		System.out.println("book");
        		try {
					Book book = new Book().getMediaById(this.media.getId());
					home.openBookDetail(book);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	else if (Objects.equals(media.getType(), "dvd")) {
        		System.out.println("dvd");
        		try {
					DVD dvd = new DVD().getMediaById(this.media.getId());
					home.openDVDDetail(dvd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	else if (Objects.equals(media.getType(), "cd")) {
        		System.out.println("cd");
        		try {
					CD cd = new CD().getMediaById(this.media.getId());
					home.openCDDetail(cd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        setMediaInfo();
    }


    /**
     * @return Media
     */
    public Media getMedia() {
        return media;
    }


    /**
     * @throws SQLException
     */
    private void setMediaInfo() throws SQLException {
        // set the cover image of media
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setFitHeight(160);
        mediaImage.setFitWidth(152);
        mediaImage.setImage(image);

        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(Utils.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));
        spinnerChangeNumber.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );

        setImage(mediaImage, media.getImageURL());
    }

}
