package views.screen.home;

import com.sun.media.jfxmedia.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.user.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import views.screen.BaseScreenHandler;
import controller.MangagerUserScreenController;

import javax.security.auth.login.FailedLoginException;

public class ManagerUserScreenHandler extends BaseScreenHandler implements Initializable {
    @FXML
    private Button userBtn;
    @FXML
    private AnchorPane userForm;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableColumn<User, Integer> userIDCol;
    @FXML
    private TableColumn<User, String> userNameCol, userAddressCol, userPhoneNumberCol, userEmailCol, userRoleCol;
    @FXML
    private TextField userAddressField, userEmailField, userPhoneNumberField, userNameField;
    @FXML
    private Label userLabelForm;
    @FXML
    private Button saveCreateUserBtn, saveUpdateUserBtn, banUserBtn, createUserBtn, updateUserBtn, deleteUserBtn, changeUserPasswordBtn;
    @FXML
    private AnchorPane subUserForm;
    @FXML
    private  ChoiceBox<String> roleChoice;
    @FXML
    private AnchorPane changePasswordForm;
    @FXML
    private Button saveChangePassword;
    @FXML
    private TextField newPasswordField, confirmPasswordField;

    private enum CHECK {
        WRONG_ADDRESS,
        WRONG_PHONENUMBER,
        RIGHT_PHONENUMBER,
        WRONG_NAME,
        WRONG_EMAIL,
        RIGHT_EMAIL,
    }

    private CHECK checkPhoneNumber(String phone) {
        if (phone.length() > 10 || phone.length() < 10 || phone.charAt(0) != '0')
            return CHECK.WRONG_PHONENUMBER;
        return CHECK.RIGHT_PHONENUMBER;
    }

    private CHECK checkEmail (String email) {
        if (email.contains("@"))
            return  CHECK.RIGHT_EMAIL;
        return CHECK.WRONG_EMAIL;
    }
    public ManagerUserScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        userForm.setVisible(true);
        // TODO Auto-generated constructor stub
    }
    public MangagerUserScreenController getBController() {
        return (MangagerUserScreenController) super.getBController();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new MangagerUserScreenController());
        try {
            showAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigate(ActionEvent e) throws SQLException {
            userForm.setVisible(true);
    }

    public void logout(ActionEvent e) throws SQLException {
        this.homeScreenHandler.show();
    }

    public void showAllUser() throws SQLException{
        List<User> listUser = getBController().getAllUser();
        roleChoice.getItems().setAll("Admin", "User");
        userIDCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        userAddressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        userPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        userTableView.getItems().setAll(listUser);

        subUserForm.setVisible(false);
        changePasswordForm.setVisible(false);

        userTableView.setRowFactory(tv -> new TableRow<User>() {
            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty) ;
                String AdminStyle = "-fx-font-weight: bold";
                String BanUserStyle = "-fx-background-color: red";
                String style = "";
                if (item == null) {
                    setStyle("");
                } else if (item.getBan()) {
                    style = BanUserStyle;
                } else {
                    style = "";
                };

                if (item == null) {
                    setStyle("");
                } else if (item.getRole() == 1) {
                    style = style +  ";" +AdminStyle;
                }

                setStyle(style);
                }
        });
    }

    public void setCreateUserBtn() {
        userLabelForm.setText("Create User");
        subUserForm.setVisible(true);
        saveUpdateUserBtn.setVisible(false);
        saveCreateUserBtn.setVisible(true);
        changePasswordForm.setVisible(false);

        userNameField.setText("");
        userAddressField.setText("");
        userPhoneNumberField.setText("");
        userEmailField.setText("");
    }

    public void setUpdateUserBtn() {
        changePasswordForm.setVisible(false);
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userLabelForm.setText("Edit User");
            subUserForm.setVisible(true);
            saveUpdateUserBtn.setVisible(true);
            saveCreateUserBtn.setVisible(false);

            userNameField.setText(selectedUser.getName());
            userAddressField.setText(selectedUser.getAddress());
            userPhoneNumberField.setText(selectedUser.getPhone());
            userEmailField.setText(selectedUser.getEmail());
        } else {
            subUserForm.setVisible(false);
        }
    }

    public void setBanUserBtn() throws SQLException {
        subUserForm.setVisible(false);
        changePasswordForm.setVisible(false);
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to ban / unban this user?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.YES) {
                getBController().banUser(selectedUser.getId(), selectedUser.getBan());
                showAllUser();
            }
        }
    }

    public void setDeleteUserBtn() throws SQLException {
        subUserForm.setVisible(false);
        changePasswordForm.setVisible(false);
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this user?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.YES) {
                getBController().deleteUser(selectedUser.getId());
                showAllUser();
            }
                    }
    }

    public void setChangeUserPasswordBtn() throws SQLException {
        changePasswordForm.setVisible(true);
        subUserForm.setVisible(false);
    }

    public void setSaveCreateUserBtn() throws SQLException {
        String name = userNameField.getText();
        String address = userAddressField.getText();
        String phone = userPhoneNumberField.getText();
        String email = userEmailField.getText();
        String role_str;
        if (roleChoice.getSelectionModel().getSelectedItem() != null) {
            role_str = roleChoice.getSelectionModel().getSelectedItem().toString();
        } else {
            role_str = "0";
        }
        int role = role_str == "Admin" ? 1 : 0;

        CHECK check_phone = checkPhoneNumber(phone);
        CHECK check_email = checkEmail(email);

        if (check_phone == CHECK.WRONG_PHONENUMBER || check_email == CHECK.WRONG_EMAIL) {
            showAlert(Alert.AlertType.WARNING, "Fail to create new user", "Enter information again please", "Enter information again please");
        } else {
            getBController().createUser(name, email, address, phone, role);
            showAllUser();
        }
    }

    public void setSaveUpdateUserBtn() throws SQLException{
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int id = selectedUser.getId();
            String name = userNameField.getText();
            String address = userAddressField.getText();
            String phone = userPhoneNumberField.getText();
            String email = userEmailField.getText();
            String role_str;
            if (roleChoice.getSelectionModel().getSelectedItem() != null) {
                role_str = roleChoice.getSelectionModel().getSelectedItem().toString();
            } else {
                role_str = "0";
            }
            int role = role_str == "Admin" ? 1 : 0;

            CHECK check_phone = checkPhoneNumber(phone);
            CHECK check_email = checkEmail(email);

            if (check_phone == CHECK.WRONG_PHONENUMBER || check_email == CHECK.WRONG_EMAIL) {
                showAlert(Alert.AlertType.WARNING, "Fail to change user information", "Enter again please", "Enter again please");
            } else {
                getBController().updateUser(id, name, email, address, phone, role);
                showAllUser();
            }
        } else {
            subUserForm.setVisible(false);
        }
    }

    public void setSaveChangePassword() throws SQLException, Exception{
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        int id = selectedUser.getId();
        if (selectedUser != null) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to change password?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.get() == ButtonType.YES) {
                String newPassword = newPasswordField.getText();
                String confirmPassword =  confirmPasswordField.getText();
                    if (newPassword .compareTo(confirmPassword)  != 0) {
                    showAlert(Alert.AlertType.ERROR, "Wrong confirm password " + newPassword + confirmPassword, "The new password is not the same with the confirm password",
                            "Please enter confirm password again.");
                } else {
                    getBController().changePassword(id, newPassword);
                    changePasswordForm.setVisible(false);
                }
            }
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
