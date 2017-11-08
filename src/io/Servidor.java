package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Cliente;

public class Servidor {

	private ServerSocket server;
	private Task<Void> server_listen;
	private ListView<Label> list_log;
	public static ArrayList<Socket> conectados= new ArrayList<>();
	
	public Servidor(int porta) throws IOException {
		server= new ServerSocket(porta); //Crio um ServerSocket informando uma porta
		initServidor();
	}
	
	private void initServidor() {
		setServer_listen(new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					Socket socket=server.accept(); //Crio um socket se houver uma nova conexão de um client
					conectados.add(socket);
					Cliente cliente= new Cliente(socket);
					if(list_log!=null)
						list_log.getItems().add(new Label(socket.getLocalAddress().getHostAddress()));
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
	
	public void setListLog(ListView<Label> list_log) {
		this.list_log= list_log;
	}
	
	public static void Ecoar(String message) {
		conectados.stream().forEach(s->{
			try {
				if(s.isConnected()) {
					DataOutputStream out=new DataOutputStream(s.getOutputStream());
					out.writeUTF(message);
					out.flush();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		});
	}

}
