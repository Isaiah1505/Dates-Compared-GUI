package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("DatesCompared.fxml"));
			Scene scene = new Scene(root,700, 450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image windowIcon = new Image("calendarIcon.png");
			primaryStage.setScene(scene);
			primaryStage.setTitle("DateToDate");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(windowIcon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
