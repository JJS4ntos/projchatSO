package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import model.Cliente;

public class Servidor {

	private ServerSocket server;
	private Task<Void> server_listen;
	private final ListView<String> list_log, list_packets;
	public static ArrayList<Socket> conectados= new ArrayList<>();
	
	public Servidor(int porta, ListView<String> list_packets, ListView<String> list_log) throws IOException {
		this.list_log= list_log;
		this.list_packets=list_packets;
		server= new ServerSocket(porta); //Crio um ServerSocket informando uma porta
		initServidor();
	}
	
	/***
	 * Inicia o loop do servidor
	 */
	private void initServidor() {
		setServer_listen(new Task<Void>() {
			protected Void call() throws IOException {
				while(true) {
					Socket socket=server.accept(); //Crio um socket se houver uma nova conexão de um client
					conectados.add(socket);
					Cliente cliente= new Cliente(list_packets, socket);
					cliente.setOnFailed(e-> cliente.getException().printStackTrace(System.err));
					String now= LocalDateTime.now().toString();
					if(list_log!=null) {
						Platform.runLater(()->list_log.getItems().add("[".concat(now).concat("]")
								.concat(socket.getLocalAddress().getHostAddress()).concat(" se conectou.")));
					}
						
					Thread thread_cliente= new Thread(cliente);
					thread_cliente.setDaemon(true);
					thread_cliente.start();
				}
			}
		});
	}
	
	/***
	 * Retorna o ServerSocket
	 * @return
	 */
	public ServerSocket getSSocket() {
		return server;
	}

	/***
	 * Retorna o server_listen
	 * @return
	 */
	public Task<Void> getServer_listen() {
		return server_listen;
	}

	public void setServer_listen(Task<Void> server_listen) {
		this.server_listen = server_listen;
	}
	
	public static void Broadcast(String message) {
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

	public ListView<String> getList_packets() {
		return list_packets;
	}

}
