package com.appointments.system.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // todo: set logo image
    }


}
