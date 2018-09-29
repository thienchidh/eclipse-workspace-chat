
package controls.controls;

import java.io.*;
import java.net.*;

import controls.factory.*;
import controls.icontrols.*;
import model.*;

public class Controls implements IControls {

	IConnect	connect;
	IHandle		handle;

	Socket		socket;

	public Controls(IConnect connect, IHandle handle) {

		super();
		this.connect = connect;
		this.handle = handle;
		socket = FactoryInstanceControls.getSocket();
	}

	@Override
	public boolean dongKetNoi() {

		try {
			connect.closeConnect();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean moKetNoi() {

		try {
			connect.openConnect();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Message getTinNhan() {

		try {
			return handle.receiveMessage();
		} catch(ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void guiTinNhan(Message s) {
		System.out.println("Controls.guiTinNhan()");

		try {
			handle.sendMessage(s);
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
