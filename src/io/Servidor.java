package io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.concurrent.Task;
import model.Cliente;

public class Servidor {

	private ServerSocket server;
	private Task<Void> server_listen;
	
	public Servidor(int porta) throws IOException {
		server= new ServerSocket(porta); //Crio um ServerSocket informando uma porta
		initServidor();
	}
	
	private void initServidor() {
		setServer_listen(new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					Socket socket=server.accept(); //Crio um socket se houver uma nova conexão de um client
					Cliente cliente= new Cliente(socket);
					Thread thread_cliente= new Thread(cliente);
					thread_cliente.setDaemon(true);
					thread_cliente.start();
				}
			}
		});
	}
	
	public ServerSocket getSSocket() {
		return server;
	}

	public Task<Void> getServer_listen() {
		return server_listen;
	}

	public void setServer_listen(Task<Void> server_listen) {
		this.server_listen = server_listen;
	}
	
}
