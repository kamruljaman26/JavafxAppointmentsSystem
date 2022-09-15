package com.appointments.system.controller;

import com.appointments.system.model.Users;
import com.appointments.system.repo.UsersDao;
import com.appointments.system.utils.DataTraveler;
import com.appointments.system.utils.FXUtil;
import com.appointments.system.utils.LanguageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Admin Login controller for FXML to manage and verify login details also able to manage
 */
public class AppLoginController implements Initializable, DataTraveler {

    // fxml views init ids
    public Label userLoginLabelID;
    public Button loginBtnID;
    public PasswordField passwordTxtFldID;
    public TextField usernameTxtFldID;
    public SplitMenuButton languageSplitMenuID;
    public Label locationLabelID;
    public ImageView logoImgViewID;
    public Label messageLabelID;

    // properties
    private Object[] data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // init logo img
        Image image = new Image(String.valueOf(getClass().getResource("images/logo.png")));
        logoImgViewID.setImage(image);

        // change language based on computer language
        if (LanguageUtil.getLocale().getLanguage().equals("fr")) {
            LanguageUtil.setLanguage("fr");
            updateLanguageForView();
            languageSplitMenuID.setText("French");
        }

        setupLanguageMenu();
        setUserLocationInLabel();

        // login button
        loginBtnID.setOnAction(this::handleLogin);
    }

    // verify login and handle button action
    private void handleLogin(ActionEvent event) {
        try {
            // write login attempts in file
            File file = new File("login_activity.txt");
            if (!file.isFile()) file.createNewFile();
            FileWriter writer = new FileWriter(file, true);

            messageLabelID.setText("");

            String username = usernameTxtFldID.getText();
            String password = passwordTxtFldID.getText();
            boolean isLoginSuccess = false;

            if (username.isEmpty() || password.isEmpty()) {
                messageLabelID.setText(LanguageUtil.getString("empty.username.password"));
            } else {
                UsersDao dao = new UsersDao();
                Users users = dao.getUserByUsername(username);

                // after validation move/load admin dashboard
                if (users != null && users.verifyPassword(password)) {
                    // load new view
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXUtil.loadView(
                            getClass(),
                            event,
                            FXUtil.DASHBOARD,
                            "Admin Dashboard",
                            users
                    );
                    isLoginSuccess = true;
                }
                // show invalid message
                else {
                    messageLabelID.setText(LanguageUtil.getString("invalid.username.password"));
                }
            }

            // write in file
            if (isLoginSuccess)
                writer.write("username=" + usernameTxtFldID.getText()
                        + ", time=" + LocalDateTime.now().toString() + ", status=success\n");
            else
                writer.write("username=" + usernameTxtFldID.getText() +
                        ", time=" + LocalDateTime.now().toString() + ", status=fail\n");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // setup side menu
    private void setupLanguageMenu() {
        MenuItem english = new MenuItem("English");
        english.setOnAction(event -> {
            LanguageUtil.setLanguage("en");
            updateLanguageForView();
            languageSplitMenuID.setText("English");
        });

        MenuItem french = new MenuItem("French");
        french.setOnAction(event -> {
            LanguageUtil.setLanguage("fr");
            updateLanguageForView();
            languageSplitMenuID.setText("French");
        });

        languageSplitMenuID.getItems().addAll(english, french); // add views
    }

    // reset page language
    private void updateLanguageForView() {
        userLoginLabelID.setText(LanguageUtil.getString("btn.user.login.label"));
        usernameTxtFldID.setPromptText(LanguageUtil.getString("txt.field.username.input"));
        passwordTxtFldID.setPromptText(LanguageUtil.getString("txt.field.password.input"));
        loginBtnID.setText(LanguageUtil.getString("btn.login"));
    }

    // show user location
    private void setUserLocationInLabel() {
        locationLabelID.setText(LanguageUtil.getString("location")+" : "+LanguageUtil.getLocale().getDisplayCountry());
    }

    @Override
    public void data(Object... o) {

    }
}
