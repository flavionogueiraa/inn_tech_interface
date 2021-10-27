package interface_package;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainInterface extends Application {
	@Override
	public void start(Stage arg0) throws Exception {
		Parent StartInterface = FXMLLoader.load(getClass().getResource("/interface_package/FxmlDoccument.fxml"));
		Scene cena = new Scene(StartInterface);

		arg0.setTitle("Meu novo programa com interface");
		arg0.setScene(cena);
		arg0.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
