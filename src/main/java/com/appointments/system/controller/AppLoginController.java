package com.appointments.system.controller;

import com.appointments.system.model.Users;
import com.appointments.system.repo.UsersDao;
import com.appointments.system.utils.LanguageUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class AppLoginController implements Initializable {

    // fxml views init ids
    public Label userLoginLabelID;
    public Button loginBtnID;
    public PasswordField passwordTxtFldID;
    public TextField usernameTxtFldID;
    public SplitMenuButton languageSplitMenuID;
    public Label locationLabelID;
    public ImageView logoImgViewID;
    public Label messageLabelID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupLanguageMenu();
        setUserLocationInLabel();

        // login button
        loginBtnID.setOnAction(event -> {
            handleLogin();
        });
    }

    // verify login and handle button action
    private void handleLogin() {
        messageLabelID.setText("");

        String username = usernameTxtFldID.getText();
        String password = passwordTxtFldID.getText();
        if(username.isEmpty() || password.isEmpty()){
            messageLabelID.setText(LanguageUtil.getString("empty.username.password"));
        }else {
            UsersDao dao = new UsersDao();
//            Users users = dao.getUserByUsername(username);
//            System.out.println(users);
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
        locationLabelID.setText(LanguageUtil.getLocale().getDisplayCountry());
    }
}
