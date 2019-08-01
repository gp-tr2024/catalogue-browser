<<<<<<< HEAD
package messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "messages.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static String ICT_UI_lblLoadingBar_text;

	private Messages() {
	}

	public static String getString(String key, Object... args) {
		
		MessageFormat formatter = new MessageFormat("");
		
		try {
			
			String message = RESOURCE_BUNDLE.getString(key);
			
			formatter.applyPattern(message);
			
		    String output = formatter.format(args);
		    
		    return output;
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
=======
package messages;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "messages.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static String ICT_UI_lblLoadingBar_text;

	private Messages() {
	}

	public static String getString(String key, Object... args) {
		
		MessageFormat formatter = new MessageFormat("");
		
		try {
			
			String message = RESOURCE_BUNDLE.getString(key);
			
			formatter.applyPattern(message);
			
		    String output = formatter.format(args);
		    
		    return output;
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
>>>>>>> 574ffe363e78d250cf6350ff4ea89f2f48352380
