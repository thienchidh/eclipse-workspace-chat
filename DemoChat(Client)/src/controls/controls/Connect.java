
package controls.controls;

import java.net.*;

import controls.icontrols.*;

public class Connect implements IConnect {

	private Socket socket;

	public Connect() {

		super();
		try {
			openConnect();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean openConnect() throws Exception {

		// TODO Auto-generated method stub
		System.out.println("Connect.openConnect()");
		socket = new Socket("localhost", 8199);
		socket.setSoTimeout(100000);
		return true;
	}

	@Override
	public boolean closeConnect() throws Exception {

		// TODO Auto-generated method stub
		System.out.println("Connect.closeConnect()");
		socket.close();
		return false;
	}

	/**
	 * @return the socket
	 */
	public synchronized Socket getSocket() {

		return socket;
	}

}
