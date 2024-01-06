package views.screen.home;
import controller.ManagerHomeController;
import controller.MangagerUserScreenController;
import controller.AuthenticationController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


public class LoginScreenHandler extends BaseScreenHandler{

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }

    @FXML
    void login() throws IOException, InterruptedException, SQLException {
        try {
            int role = getBController().login(email.getText(), password.getText());

            PopupScreen.success("Login Successfully!");
            if (role == 1) {
                ManagerUserScreenHandler managerUserScreen = new ManagerUserScreenHandler(this.stage, Configs.MANAGER_USER_SCREEN_PATH);
                managerUserScreen.setScreenTitle("User Manager");
                managerUserScreen.setBController(new MangagerUserScreenController());
                managerUserScreen.setHomeScreenHandler(homeScreenHandler);
                managerUserScreen.show();
            } else {
                if (role == 0) {
                    ManagerScreenHandler managerScreen = new ManagerScreenHandler(this.stage, Configs.MANAGER_SCREEN_PATH);
                    managerScreen.setScreenTitle("Manager");
                    managerScreen.setBController(new ManagerHomeController());
                    managerScreen.setHomeScreenHandler(homeScreenHandler);
                    managerScreen.show();
                }
            }

        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    void backToHomeScreen(MouseEvent event) throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
