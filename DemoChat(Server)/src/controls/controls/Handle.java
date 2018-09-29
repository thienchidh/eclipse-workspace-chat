
package controls.controls;

import java.io.*;
import java.net.*;

import controls.factory.*;
import controls.icontrols.*;
import model.*;

public class Handle implements IHandle {

	Socket						socket;
	IReceive					iReceive;
	private OutputStream		os;
	private ObjectOutputStream	oos;
	private InputStream			is;
	private ObjectInputStream	ois;

	public Handle(Socket socket) {

		super();
		this.socket = socket;
		iReceive = (IReceive)FactoryInstancesControls.getControls();

		try {
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);

			is = socket.getInputStream();
			ois = new ObjectInputStream(is);

		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		try {
			while(true) {
				Message message = receiveMessage();
				iReceive.sendMessageToClient(message);

				Thread.sleep(10);
			}
		} catch(ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				ois.close();
				is.close();
				os.close();
			} catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void sendMessage(Message s) throws IOException {

		oos.writeObject(s);
	}

	@Override
	public Message receiveMessage() throws IOException, ClassNotFoundException {

		return (Message)ois.readObject();

	}

	/**
	 * @return the socket
	 */
	public synchronized Socket getSocket() {

		return socket;
	}

}
