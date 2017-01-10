package ir.bestoon.alireza6677.utils;

import java.util.prefs.Preferences;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class Prefs {
	
	private static final String USERNAME="USERNAME_BESTOON";	
	private static final String TOKEN="TOKEN_BESTOON";
	//private static String LOGIN="LOGIN_BESTOON";
	private static Prefs PREFS = new Prefs();
	
	//private boolean loggedIn = false;
	
	private Preferences p;
	
	private Prefs(){
		p = Preferences.userRoot();
	}
	
	public synchronized static Prefs getPrefs(){
		if(PREFS == null)
			PREFS = new Prefs();
		return PREFS;
	}
	
	public String getUsername() {
		return p.get(USERNAME, "");
	}

	public void setUsername(String u) {
		p.put(USERNAME, u);;
	}

	public String getToken() {
		return p.get(TOKEN, "");
	}

	public void setToken(String t) {
		p.put(TOKEN, t);
	}

	public void removeToken() {
		p.remove(TOKEN);
	}

	public void removeUsername() {
		p.remove(USERNAME);
	}
}
