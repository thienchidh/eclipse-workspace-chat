
package ui;

import static controls.factory.IText.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;

import controls.factory.*;
import controls.icontrols.*;
import model.*;

public class ChatClientUI extends JFrame {

	private JTextField			txtMsgToSend;
	private JButton				btnSend;
	private JTextArea			txtChat;

	private IControls			controls;
	private SimpleDateFormat	sdf;
	private StringBuilder		sb;
	private String				username;

	public ChatClientUI(String arg0) throws HeadlessException {

		super(arg0);
		addControls();
		addEvents();
		controls = FactoryInstanceControls.getControls();
		sdf = new SimpleDateFormat(" dd/MM/yyyy - hh:mm:ss ");
		sb = new StringBuilder();
		choDoiLaHanhPhuc();
	}

	public void showWindow() {

		setSize(450, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private boolean isEmptyString(String s) {

		return s == null || s.isEmpty();
	}

	private void choDoiLaHanhPhuc() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while(true) {
					Message ms = controls.getTinNhan();
					sb.setLength(0);
					txtChat.append(sb.append(sdf.format(ms.getDateSend())).append(ms.getOwner()).append(": ").append(ms.getContent()).append("\n").toString());
					txtChat.setCaretPosition(txtChat.getText().length());
				}
			}
		}).start();
	}

	protected void xuLySendChat() {

		// TODO Auto-generated method stub
		String msg = txtMsgToSend.getText();
		if(isEmptyString(msg)) { return; }
		controls.guiTinNhan(new Message(username, msg));
		txtMsgToSend.setText("");

	}

	protected void xuLyThoat() {

		int ret = showConFirm("Xác Nhận Thoát?", JOptionPane.YES_NO_OPTION);
		if(ret == JOptionPane.YES_OPTION) {
			// add close connect here
			closeConnect();
			System.exit(0);
		}
	}

	private void closeConnect() {

		// TODO Auto-generated method stub
		System.out.println("ChatClientUI.closeConnect()");
		controls.dongKetNoi();

	}

	private void addControls() {

		Container con = getContentPane();
		con.setLayout(new BorderLayout());

		JPanel pnMain = new JPanel(new BorderLayout());
		con.add(pnMain, BorderLayout.CENTER);

		JPanel pnTitle = new JPanel();
		pnMain.add(pnTitle, BorderLayout.NORTH);
		JLabel lblTitle = new JLabel(":)");
		lblTitle.setFont(new Font(null, Font.BOLD, 13));
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);

		JPanel pnMid = new JPanel(new BorderLayout());
		pnMain.add(pnMid, BorderLayout.CENTER);

		txtChat = new JTextArea();
		JScrollPane sc = new JScrollPane(txtChat);
		txtChat.setWrapStyleWord(true);
		txtChat.setLineWrap(true);
		txtChat.setFont(new Font(null, Font.PLAIN, 14));
		txtChat.setEditable(false);
		pnMid.add(sc, BorderLayout.CENTER);

		JPanel pnSouth = new JPanel();
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));
		pnMain.add(pnSouth, BorderLayout.SOUTH);

		txtMsgToSend = new JTextField(10);
		pnSouth.add(txtMsgToSend);

		btnSend = new JButton("Send");
		pnSouth.add(btnSend);

	}

	private void addEvents() {

		// TODO Auto-generated method stub
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				xuLyThoat();
			}
		});
		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				xuLySendChat();
			}
		});

	}

	/**
	 * @param username
	 *            the username to set
	 */
	public synchronized void setUsername(String username) {

		this.username = username;
	}

}
