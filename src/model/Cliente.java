package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

import io.Servidor;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;

public class Cliente extends Task<Void>{

	private final Socket socket;
	private ListView<String> list_packets;
	
	public Cliente(ListView<String> list_packets, Socket socket) {
		this.list_packets=list_packets;
		this.socket= socket;
	}

	public Socket getSocket() {
		return socket;
	}
	
	@Override
	protected Void call() throws IOException {
		// TODO Auto-generated method stub
		DataInputStream in= new DataInputStream(socket.getInputStream());
		while(true) {
			if(!socket.isClosed()) {
				String mensagem=in.readUTF();
				if(mensagem.split("ии")[1].equals("REMOVER$chat"))
					Servidor.conectados.remove(socket);
				else
					Servidor.Broadcast(mensagem);	
				StringBuffer sb= new StringBuffer();
				for(byte b:mensagem.getBytes())
					sb.append(String.valueOf(b).concat(" "));
				Platform.runLater(()->list_packets.getItems().add("[".concat(LocalDateTime.now().toString()).concat("]").concat("Recebemos o pacote :"
						.concat(sb.toString()))));
			}
		}
	}
}
