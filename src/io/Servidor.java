package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.concurrent.Task;

public class Servidor {

	ServerSocket server;
	Task<Void> server_listen;
	
	public Servidor(int porta) throws IOException {
		server= new ServerSocket(porta); //Crio um ServerSocket informando uma porta
	}
	
	public void prepararServidor() {
		server_listen= new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					Socket socket=server.accept(); //Crio um socket se houver uma nova conexão de um client
				}
			}
		};
	}
	
}
