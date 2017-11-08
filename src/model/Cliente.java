package model;

import java.io.DataInputStream;
import java.net.Socket;

import io.Servidor;
import javafx.concurrent.Task;

public class Cliente extends Task<Void>{

	private final Socket socket;
	
	public Cliente(Socket socket) {
		this.socket= socket;
	}

	public Socket getSocket() {
		return socket;
	}
	
	@Override
	protected Void call() throws Exception {
		// TODO Auto-generated method stub
		while(true) {
			DataInputStream in= new DataInputStream(socket.getInputStream());
			String mensagem=in.readUTF();
			if(mensagem.split(":")[1].equals("REMOVER$chat"))
				Servidor.conectados.remove(socket);
			else
				Servidor.Ecoar(mensagem);		
		}
	}
}
