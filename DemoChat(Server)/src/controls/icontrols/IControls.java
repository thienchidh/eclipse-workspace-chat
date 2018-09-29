
package controls.icontrols;

import java.io.*;

public interface IControls {

	boolean startServer(int port) throws IOException;

	void stopServer() throws IOException;

	void processing();

}
