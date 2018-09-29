
package test;

import static controls.factory.IText.*;
import ui.*;

public class TestChatClientUI {

	public static void main(String[] args) {

		String username = showInput("what your name?", null);
		if(username == null || username.isEmpty()) { return; }
		ChatClientUI ui = new ChatClientUI("Chat client UI - " + username);
		ui.setUsername(username);
		ui.showWindow();
	}
}
