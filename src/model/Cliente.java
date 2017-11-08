package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import io.Servidor;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;

public class Cliente extends Task<Void>{

	private final Socket socket;
	private ListView<String> list_packets;
	
	public Cliente(ListView<String> list_packets, Socket socket) {
		this.socket= socket;
	}

	public Socket getSocket() {
		return socket;
	}
	
	@Override
	protected Void call() throws IOException {
		// TODO Auto-generated method stub
		while(true) {
			DataInputStream in= new DataInputStream(socket.getInputStream());
			String mensagem=in.readUTF();
			if(mensagem.split("ии")[1].equals("REMOVER$chat"))
				Servidor.conectados.remove(socket);
			else
				Servidor.Broadcast(mensagem);	
			//in.read
			//StringBuffer sb= new StringBuffer();
			/*while(in.read()!=-1) {
				sb.append(in.readByte());
			}*/
			//list_packets.getItems().add("[".concat(LocalDateTime.now().toString()).concat("] Pacote recebido de ".concat(socket.getLocalAddress().getHostAddress())).concat(sb.toString()));
		}
	}
}
