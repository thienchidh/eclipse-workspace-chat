
package ui;

import static controls.factory.IText.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import controls.factory.*;
import controls.icontrols.*;

public class ServerUI extends JFrame {

	private JButton	btnStart;
	private JButton	btnStop;
	
	IControls controls;

	public ServerUI(String title) throws HeadlessException {

		super(title);
		controls = FactoryInstancesControls.getControls();
		addControls();
		addEvents();
		setStatusButtonWhenChangeButton(false);
	}

	public void showWindow() {

		pack();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	protected void xuLyStartServer() {

		System.out.println("ServerUI.xuLyStartServer()");

		// TODO Auto-generated method stub
		setStatusButtonWhenChangeButton(true);
		try {
			controls.startServer(8199);
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void xuLyStopServer() {

		System.out.println("ServerUI.xuLyStopServer()");
		// TODO Auto-generated method stub
		setStatusButtonWhenChangeButton(false);
		try {
			controls.stopServer();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void xyLyThoat() {

		int ret = showConFirm("Xác Nhận Thoát?", JOptionPane.YES_NO_OPTION);
		if(ret == JOptionPane.YES_OPTION) {
			// add close connect here
			System.exit(0);
		}
	}

	private void addControls() {

		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		JPanel pnMain = new JPanel();
		con.add(pnMain, BorderLayout.CENTER);
		btnStart = new JButton("Start Server");

		pnMain.add(btnStart);
		btnStop = new JButton("Stop Server");
		pnMain.add(btnStop);
	}

	private void addEvents() {

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				xyLyThoat();
			}
		});
		btnStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				xuLyStopServer();
			}
		});
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				xuLyStartServer();
			}
		});
	}

	private void setStatusButtonWhenChangeButton(boolean b) {

		btnStart.setEnabled(!b);
		btnStop.setEnabled(b);
	}

}