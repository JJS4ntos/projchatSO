package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.Servidor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class ServidorController implements Initializable {

    @FXML private HBox h_status;
    @FXML private Label lbl_status;
    @FXML private ListView<String> list_log, list_packets;
	@FXML private Button btn_ligar;
		  private Servidor servidor;
		  private Thread threadServidor;
    

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		initActions();
	}
	
	/***
	 * Adiciona ações ao elementos da tela
	 */
	private void initActions() {
		btn_ligar.setOnAction(e->{
			if(btn_ligar.getText().equals("Ligar servidor")) {
				iniciarServidor();
				btn_ligar.setText("Desligar servidor");
			}else {
				pararServidor();
				btn_ligar.setText("Ligar servidor");
			}
		});
	}
	
	/***
	 * Liga o servidor
	 */
	private void iniciarServidor() {
		try {
			servidor= new Servidor(512, list_packets, list_log);
			threadServidor= new Thread(servidor.getServer_listen());
			threadServidor.setDaemon(true);
			threadServidor.start();
			h_status.setStyle("-fx-background-color: lightgreen;");
			lbl_status.setText("Servidor ligado - Aguardando conexões");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Desliga o servidor
	 */
	private void pararServidor() {
		try {
			servidor.getSSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadServidor.interrupt();
		h_status.setStyle("-fx-background-color: #FF0000;");
		lbl_status.setText("O servidor foi desligado");
	}
	
}
