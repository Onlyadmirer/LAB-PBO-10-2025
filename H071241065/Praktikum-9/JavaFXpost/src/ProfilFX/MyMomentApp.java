package ProfilFX;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MyMomentApp extends Application {

  private Stage primaryStage;
  private MyMomentView view; // Referensi ke View

  private User currentUser;
  private final ObservableList<Post> userPosts = FXCollections.observableArrayList();
  private File selectedProfileImageFile;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.view = new MyMomentView(); // Inisialisasi View
    primaryStage.setTitle("MyMoment");
    showRegisterScene();
    primaryStage.show();
  }

  private void showRegisterScene() {
    Scene registerScene = view.createRegisterScene();

    // Menambahkan event handler ke komponen dari View
    view.getUploadProfileButton().setOnAction(e -> handleUploadProfileImage());
    view.getSubmitRegisterButton().setOnAction(e -> handleRegisterSubmit());

    primaryStage.setScene(registerScene);
    primaryStage.setTitle("MyMoment - Register");
  }

  private void showHomeScene() {
    Scene homeScene = view.createHomeScene(currentUser);

    // Menambahkan event handler ke komponen dari View
    view.getAddPostButton().setOnAction(e -> handleAddPost());

    // Memuat postingan awal
    refreshPostsDisplay();

    primaryStage.setScene(homeScene);
    primaryStage.setTitle("MyMoment");
  }

  private void handleUploadProfileImage() {
    FileChooser fileChooser = view.createConfiguredFileChooser("Select Profile Image");
    selectedProfileImageFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedProfileImageFile != null) {
      view.loadImageIntoView(selectedProfileImageFile, view.getProfileImageViewRegister(), "");
    }
  }

  private void handleRegisterSubmit() {
    String nickName = view.getNickNameField().getText();
    String fullName = view.getFullNameField().getText();
    Image profileImage = view.getProfileImageViewRegister().getImage();

    if (isStringEmpty(nickName) || isStringEmpty(fullName) || profileImage == null) {
      view.showAlert(Alert.AlertType.ERROR, "Registration Error",
          "Nick Name, Full Name, and Profile Photo are required.");
      return;
    }
    currentUser = new User(nickName, fullName, profileImage);
    showHomeScene();
  }

  private void handleAddPost() {
    // Menggunakan lambda untuk menerima Post baru dari window
    view.showAddPostWindow(primaryStage, newPost -> {
      userPosts.add(newPost);
      refreshPostsDisplay();
    });
  }

  private void handlePostClick(Post post) {
    view.showPhotoDetailWindow(primaryStage, post);
  }

  private void refreshPostsDisplay() {
    // Mengirim data dan callback ke view untuk di-render
    view.refreshPostsDisplay(userPosts, this::handlePostClick);
  }

  private boolean isStringEmpty(String str) {
    return str == null || str.trim().isEmpty();
  }
}