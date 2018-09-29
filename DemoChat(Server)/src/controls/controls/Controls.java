
package controls.controls;

import java.io.*;
import java.net.*;
import java.util.*;

import controls.icontrols.*;
import model.*;

public class Controls implements IControls, IReceive {

	ServerSocket	serverSocket;
	Set<IHandle>	handles;

	@Override
	public boolean startServer(int port) throws IOException {

		System.out.println("Controls.startServer()");
		serverSocket = new ServerSocket(port);
		handles = new HashSet<>();
		new Thread(new Runnable() {

			@Override
			public void run() {

				processing();
			}
		}).start();
		return true;
	}

	@Override
	public void stopServer() throws IOException {

		System.out.println("Controls.stopServer()");
		serverSocket.close();
	}

	@Override
	public void processing() {

		System.out.println("Controls.processing()");
		try {
			while(true) {
				Handle target = new Handle(serverSocket.accept());
				handles.add(target);
				new Thread(target).start();
			}

		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stopServer();
			} catch(IOException e) {
				e.printStackTrace();
				// shut down server mà không đc thì a cho chết hết
				System.exit(1);
			}
		}

	}

	@Override
	public boolean sendMessageToClient(Message s) throws IOException {

		System.out.println("Controls.sendMessageToClient()");
		for(IHandle handle : handles) {
			if(!handle.getSocket().isClosed()) {
				handle.sendMessage(s);
			}
		}
		return true;
	}

}