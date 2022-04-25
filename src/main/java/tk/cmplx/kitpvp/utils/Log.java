package tk.cmplx.kitpvp.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import tk.cmplx.kitpvp.Main;

public class Log {
	
	static Logger internal;
	
	public static void initialize() {
		internal = Main.instance.getLogger();
	}

	public static void info(String message) {
		internal.log(Level.INFO, message);
	}

	public static void error(String message) {
		internal.log(Level.SEVERE, message);
	}

	public static void warn(String message) {
		internal.log(Level.WARNING, message);
	}

	public static void debug(String message) {
		internal.log(Level.FINE, message);
	}

}
