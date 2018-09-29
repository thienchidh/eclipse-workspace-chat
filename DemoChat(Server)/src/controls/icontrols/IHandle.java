
package controls.icontrols;

import java.io.*;
import java.net.*;

import model.*;

public interface IHandle extends Runnable {

	void sendMessage(Message s) throws IOException;

	Message receiveMessage() throws IOException, ClassNotFoundException;

	public Socket getSocket();

}
