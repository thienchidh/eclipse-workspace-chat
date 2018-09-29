
package controls.controls;

import java.io.*;
import java.net.*;

import controls.factory.*;
import controls.icontrols.*;
import model.*;

public class Handle implements IHandle {

	Socket				socket;

	InputStream			is;
	OutputStream		os;

	ObjectInputStream	ois;
	ObjectOutputStream	oos;
	// hiện tại chưa biết cách đóng mấy cái này lại, thiết kế lởm rồi

	public Handle() {

		super();
		socket = FactoryInstanceControls.getSocket();

		try {
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		} catch(IOException e) {
			e.printStackTrace(System.out);
		}

	}

	@Override
	public boolean sendMessage(Message s) throws IOException {

		// TODO Auto-generated method stub
		oos.writeObject(s);

		return false;
	}

	@Override
	public Message receiveMessage() throws IOException, ClassNotFoundException {

		// TODO Auto-generated method stub
		is = socket.getInputStream();
		if(ois == null) ois = new ObjectInputStream(is);
		return (Message)ois.readObject();
	}

}
