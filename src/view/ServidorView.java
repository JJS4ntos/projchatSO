package view;

import controller.ServidorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServidorView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader fxml= new FXMLLoader(getClass().getResource("/fxml/server.fxml"));
		fxml.setController(new ServidorController());
		Scene scene= new Scene(fxml.load());
		stage.setScene(scene);
		stage.setTitle("Servidor - Projeto Chat. Sistemas Operacionais");
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
