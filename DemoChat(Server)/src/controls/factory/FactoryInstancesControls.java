
package controls.factory;

import controls.controls.*;
import controls.icontrols.*;

public class FactoryInstancesControls {

	static IControls controls;

	/**
	 * @return the controls
	 */
	public static synchronized IControls getControls() {

		if(controls == null) {
			controls = new Controls();
		}

		return controls;
	}

}
