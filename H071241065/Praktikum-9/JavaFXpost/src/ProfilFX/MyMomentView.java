package ProfilFX;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MyMomentView {

  // Konstanta Styling
  private static final double DEFAULT_PADDING = 15;
  private static final double DEFAULT_SPACING = 15;
  private static final String FONT_FAMILY_SYSTEM = "System";
  private static final String STYLE_TEXT_INPUT_MAX_WIDTH = "-fx-max-width: 300px;";
  private static final String STYLE_PROFILE_IMAGE_PLACEHOLDER = "-fx-background-color: lightgray; -fx-border-color: gray; -fx-border-width: 1; -fx-border-radius: %f; -fx-background-radius: %f;";
  private static final String STYLE_POST_IMAGE_PLACEHOLDER = "-fx-background-color: lightgray; -fx-border-color: gray; -fx-border-width: 1;";
  private static final String STYLE_POST_CONTAINER_NORMAL = "-fx-border-radius: 4; -fx-background-radius: 4; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.3, 2, 2);";
  private static final String STYLE_POST_CONTAINER_HOVER = "-fx-border-radius: 4; -fx-background-radius: 4; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 15, 0.5, 3, 3);";
  private static final String STYLE_CAPTION_LABEL = "-fx-background-color: rgba(0, 0, 0, 0.6); -fx-background-radius: 0 0 4 4;";

  // Komponen UI yang perlu diakses oleh Controller
  private TextField nickNameField;
  private TextField fullNameField;
  private Button submitRegisterButton;
  private Button uploadProfileButton;
  private ImageView profileImageViewRegister;
  private Button addPostButton;
  private FlowPane postsDisplayArea;

  // Getter untuk komponen yang membutuhkan event handler
  public TextField getNickNameField() {
    return nickNameField;
  }

  public TextField getFullNameField() {
    return fullNameField;
  }

  public Button getSubmitRegisterButton() {
    return submitRegisterButton;
  }

  public Button getUploadProfileButton() {
    return uploadProfileButton;
  }

  public ImageView getProfileImageViewRegister() {
    return profileImageViewRegister;
  }

  public Button getAddPostButton() {
    return addPostButton;
  }

  public Scene createRegisterScene() {
    VBox registerLayout = new VBox(DEFAULT_SPACING);
    registerLayout.setPadding(new Insets(25));
    registerLayout.setAlignment(Pos.TOP_CENTER);

    Label mainTitleLabel = createLabel("MyMoment", Font.font(FONT_FAMILY_SYSTEM, FontWeight.BOLD, 28));
    mainTitleLabel.setPadding(new Insets(0, 0, 10, 0));
    Label sectionTitleLabel = createLabel("Input User Account", Font.font(FONT_FAMILY_SYSTEM, FontWeight.BOLD, 16));

    nickNameField = createTextField("Nick Name", STYLE_TEXT_INPUT_MAX_WIDTH);
    fullNameField = createTextField("Full Name", STYLE_TEXT_INPUT_MAX_WIDTH);

    profileImageViewRegister = new ImageView();
    VBox profileSection = createProfileUploadNodeRegister(profileImageViewRegister);

    submitRegisterButton = new Button("Submit");

    registerLayout.getChildren().addAll(mainTitleLabel, sectionTitleLabel, nickNameField, fullNameField, profileSection,
        submitRegisterButton);
    return new Scene(registerLayout, 450, 550);
  }

  public Scene createHomeScene(User currentUser) {
    BorderPane homeLayout = new BorderPane();
    homeLayout.setPadding(new Insets(10));
    homeLayout.setStyle("-fx-background-color: #f4f4f4;");

    homeLayout.setTop(createHomeTopBar(currentUser));

    postsDisplayArea = new FlowPane(DEFAULT_SPACING, DEFAULT_SPACING);
    postsDisplayArea.setPadding(new Insets(DEFAULT_PADDING));
    postsDisplayArea.setAlignment(Pos.TOP_LEFT);

    ScrollPane scrollPane = new ScrollPane(postsDisplayArea);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setStyle("-fx-background-color:transparent;");

    homeLayout.setCenter(scrollPane);
    return new Scene(homeLayout, 700, 600);
  }

  public void showAddPostWindow(Stage owner, java.util.function.Consumer<Post> postConsumer) {
    Stage postWindow = new Stage();
    postWindow.initModality(Modality.APPLICATION_MODAL);
    postWindow.initOwner(owner);
    postWindow.setTitle("Upload Post");

    VBox postLayout = new VBox(DEFAULT_SPACING);
    postLayout.setPadding(new Insets(20));
    postLayout.setAlignment(Pos.TOP_CENTER);

    ImageView postImageViewPreview = new ImageView();
    final java.io.File[] selectedPostImageFile = { null };
    VBox imageUploadSection = createImageUploadNodePost(postImageViewPreview, selectedPostImageFile, postWindow);

    TextField captionField = createTextField("Caption", null);

    Button submitPostButton = new Button("Submit");
    submitPostButton.setOnAction(e -> {
      if (postImageViewPreview.getImage() == null || captionField.getText() == null
          || captionField.getText().trim().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Post Error", "Image and caption are required for a post.");
        return;
      }
      Post newPost = new Post(captionField.getText(), postImageViewPreview.getImage());
      postConsumer.accept(newPost);
      postWindow.close();
    });

    postLayout.getChildren().addAll(imageUploadSection, captionField, submitPostButton);
    Scene postScene = new Scene(postLayout, 400, 450);
    postWindow.setScene(postScene);
    postWindow.showAndWait();
  }

  public void refreshPostsDisplay(ObservableList<Post> posts, java.util.function.Consumer<Post> onPostClick) {
    if (postsDisplayArea == null)
      return;
    postsDisplayArea.getChildren().clear();
    for (Post post : posts) {
      postsDisplayArea.getChildren().add(createPostNode(post, onPostClick));
    }
  }

  public void showPhotoDetailWindow(Stage owner, Post post) {
    Stage imageDetailStage = new Stage();
    imageDetailStage.initOwner(owner);
    imageDetailStage.setTitle("Photo Detail");
    imageDetailStage.initModality(Modality.APPLICATION_MODAL);

    ImageView fullImageView = new ImageView(post.getPostImage());
    fullImageView.setPreserveRatio(true);

    StackPane detailRootPane = new StackPane(fullImageView);
    detailRootPane.setPadding(new Insets(20));
    detailRootPane.setStyle("-fx-background-color: #1A1A1A;");
    StackPane.setAlignment(fullImageView, Pos.CENTER);

    // Kalkulasi ukuran
    double displayWidth = post.getPostImage().getWidth();
    double displayHeight = post.getPostImage().getHeight();
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double maxStageWidth = screenBounds.getWidth() * 0.85;
    double maxStageHeight = screenBounds.getHeight() * 0.85;

    if (displayWidth > maxStageWidth) {
      displayHeight = (maxStageWidth / displayWidth) * displayHeight;
      displayWidth = maxStageWidth;
    }
    if (displayHeight > maxStageHeight) {
      displayWidth = (maxStageHeight / displayHeight) * displayWidth;
      displayHeight = maxStageHeight;
    }

    fullImageView.setFitWidth(displayWidth);
    fullImageView.setFitHeight(displayHeight);

    Scene imageScene = new Scene(detailRootPane);
    imageDetailStage.setScene(imageScene);
    imageDetailStage.setWidth(Math.max(displayWidth + 40, 400));
    imageDetailStage.setHeight(Math.max(displayHeight + 40, 300));
    imageDetailStage.setMinWidth(300);
    imageDetailStage.setMinHeight(200);
    imageDetailStage.showAndWait();
  }

  public FileChooser createConfiguredFileChooser(String title) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
    return fileChooser;
  }

  public void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  // --- Metode Helper Pembuat Komponen UI (Private) ---

  private Label createLabel(String text, Font font) {
    Label label = new Label(text);
    label.setFont(font);
    return label;
  }

  private TextField createTextField(String promptText, String style) {
    TextField textField = new TextField();
    textField.setPromptText(promptText);
    if (style != null && !style.isEmpty()) {
      textField.setStyle(style);
    }
    return textField;
  }

  private VBox createProfileUploadNodeRegister(ImageView imageViewToUpdate) {
    double diameter = 120;
    configureCircularImageView(imageViewToUpdate, diameter,
        String.format(STYLE_PROFILE_IMAGE_PLACEHOLDER, diameter / 2, diameter / 2));
    uploadProfileButton = new Button("Upload Profile Photo");

    VBox profileSection = new VBox(10, uploadProfileButton, imageViewToUpdate);
    profileSection.setAlignment(Pos.CENTER);
    profileSection.setPadding(new Insets(10, 0, 10, 0));
    return profileSection;
  }

  private VBox createImageUploadNodePost(ImageView imageViewToUpdate, java.io.File[] selectedFileArray, Stage owner) {
    double previewSize = 200;
    imageViewToUpdate.setFitHeight(previewSize);
    imageViewToUpdate.setFitWidth(previewSize);
    imageViewToUpdate.setPreserveRatio(true);
    imageViewToUpdate.setStyle(STYLE_POST_IMAGE_PLACEHOLDER);

    Button uploadImageButton = new Button("Upload Image");
    uploadImageButton.setOnAction(e -> {
      FileChooser fileChooser = createConfiguredFileChooser("Select Post Image");
      selectedFileArray[0] = fileChooser.showOpenDialog(owner);
      if (selectedFileArray[0] != null) {
        loadImageIntoView(selectedFileArray[0], imageViewToUpdate, "");
      }
    });
    VBox imageUploadSection = new VBox(10, uploadImageButton, imageViewToUpdate);
    imageUploadSection.setAlignment(Pos.CENTER);
    return imageUploadSection;
  }

  private HBox createHomeTopBar(User currentUser) {
    HBox topBar = new HBox(DEFAULT_SPACING);
    topBar.setPadding(new Insets(10, DEFAULT_PADDING, 10, DEFAULT_PADDING));
    topBar.setAlignment(Pos.CENTER_LEFT);

    ImageView currentProfileImageView = new ImageView(currentUser.getProfileImage());
    configureCircularImageView(currentProfileImageView, 70, null);

    VBox profileTextInfo = new VBox(3);
    profileTextInfo.getChildren().addAll(
        createLabel(currentUser.getNickName(), Font.font(FONT_FAMILY_SYSTEM, FontWeight.BOLD, 20)),
        createLabel(currentUser.getFullName(), Font.font(FONT_FAMILY_SYSTEM, FontWeight.NORMAL, 14)));

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    addPostButton = new Button("Add Post");

    topBar.getChildren().addAll(currentProfileImageView, profileTextInfo, spacer, addPostButton);
    return topBar;
  }

  private StackPane createPostNode(Post post, java.util.function.Consumer<Post> onPostClick) {
    double targetWidth = 180;
    ImageView postView = new ImageView(post.getPostImage());
    postView.setFitWidth(targetWidth);
    postView.setPreserveRatio(true);

    Label captionLabel = createLabel(post.getCaption(), Font.font(FONT_FAMILY_SYSTEM, FontWeight.BOLD, 13));
    captionLabel.setTextFill(Color.WHITE);
    captionLabel.setPadding(new Insets(5, 8, 5, 8));
    captionLabel.setStyle(STYLE_CAPTION_LABEL);
    captionLabel.setVisible(false);
    captionLabel.setMaxWidth(targetWidth);
    captionLabel.setWrapText(true);

    StackPane postStackContainer = new StackPane(postView, captionLabel);
    StackPane.setAlignment(captionLabel, Pos.BOTTOM_LEFT);
    postStackContainer.setCursor(Cursor.HAND);
    postStackContainer.setStyle(STYLE_POST_CONTAINER_NORMAL);

    Rectangle clipShape = new Rectangle();
    clipShape.widthProperty().bind(postStackContainer.widthProperty());
    clipShape.heightProperty().bind(postStackContainer.heightProperty());
    postStackContainer.setClip(clipShape);

    Scale scaleTransform = new Scale(1, 1, targetWidth / 2, 0);
    postStackContainer.getTransforms().add(scaleTransform);

    postStackContainer.setOnMouseEntered(event -> {
      scaleTransform.setX(1.03);
      scaleTransform.setY(1.03);
      captionLabel.setVisible(true);
      postStackContainer.setStyle(STYLE_POST_CONTAINER_HOVER);
    });

    postStackContainer.setOnMouseExited(event -> {
      scaleTransform.setX(1.0);
      scaleTransform.setY(1.0);
      captionLabel.setVisible(false);
      postStackContainer.setStyle(STYLE_POST_CONTAINER_NORMAL);
    });

    postStackContainer.setOnMouseClicked(mouseEvent -> {
      if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
        onPostClick.accept(post);
      }
    });

    return postStackContainer;
  }

  public void configureCircularImageView(ImageView imageView, double diameter, String placeholderStyle) {
    imageView.setFitHeight(diameter);
    imageView.setFitWidth(diameter);
    Circle clip = new Circle(diameter / 2, diameter / 2, diameter / 2);
    imageView.setClip(clip);
    if (placeholderStyle != null && imageView.getImage() == null) {
      imageView.setStyle(placeholderStyle);
    }
  }

  public boolean loadImageIntoView(java.io.File imageFile, ImageView imageView, String styleOnSuccess) {
    if (imageFile != null) {
      try {
        javafx.scene.image.Image image = new javafx.scene.image.Image(new java.io.FileInputStream(imageFile));
        imageView.setImage(image);
        if (styleOnSuccess != null) {
          imageView.setStyle(styleOnSuccess);
        }
        return true;
      } catch (java.io.FileNotFoundException ex) {
        System.err.println("Error loading image '" + imageFile.getName() + "': " + ex.getMessage());
        showAlert(Alert.AlertType.ERROR, "File Error", "Could not load image: " + imageFile.getName());
      }
    }
    return false;
  }
}