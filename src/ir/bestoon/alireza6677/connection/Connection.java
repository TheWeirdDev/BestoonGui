package ir.bestoon.alireza6677.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import ir.bestoon.alireza6677.utils.Prefs;

public class Connection {

	private String username, password;
	private Prefs pr;

	public Connection() {
		pr = Prefs.getPrefs();
	}

	public boolean isLoggedIn() {
		return pr.getToken().isEmpty() ? false : true;
	}
	
	private static boolean netIsAvailable() {
	    try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        return false;
	    }
	}
	public JSONObject sendPostRequest(HashMap<String, String> data, String url) {
		if(!netIsAvailable()){
			JOptionPane.showMessageDialog(null, "Internet connection error !", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		HttpClient httpClient = HttpClientBuilder.create().build();

		try {

			HttpPost request = new HttpPost(url);

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			data.forEach((k, v) -> {
				urlParameters.add(new BasicNameValuePair(k, v));
			});

			request.setEntity(new UrlEncodedFormEntity(urlParameters));
			request.addHeader("content-type", "application/x-www-form-urlencoded");
			HttpResponse response = httpClient.execute(request);

			if (response.getStatusLine().getStatusCode() == 404)
				return null;

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			JSONObject obj = new JSONObject(result.toString());

			return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;

		} finally {

		}
	}

	public boolean login(String user, String pass) {
		username = user;
		password = pass;

		HashMap<String, String> data = new HashMap<String, String>(){{
			put("username" , username);
			put("password" , password);
		}};
		
		JSONObject obj = sendPostRequest(data, URLs.LOGIN);
		if(obj == null)
			return false;
		
		String status;
		try {
			status = obj.getString("result");
			if (!status.equals("ok"))
				return false;
			String token = obj.getString("token");

			pr.setToken(token);
			pr.setUsername(username);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void logout() {
		pr.removeToken();
		pr.removeUsername();
	}

	private boolean submitValues(String amount ,String text, String url){
		if(!isLoggedIn()){
			return false;
		}
		
		HashMap<String, String> data = new HashMap<String, String>(){{
			put("token" , pr.getToken());
			put("amount" , amount);
			put("text" , text);
		}};
		
		JSONObject obj = sendPostRequest(data, url);
		if(obj == null)
			return false;
		
		String status;
		try {
			status = obj.getString("status");
			if (!status.equals("ok"))
				return false;
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean submitExpense(String amount ,String text){
		return submitValues(amount, text, URLs.EXPENSE);
	}
	
	public boolean submitIncome(String amount ,String text){
		return submitValues(amount, text, URLs.INCOME);
	}
	
	public HashMap<String, Integer> getStats(){
		if(!isLoggedIn()){
			return null;
		}
		
		HashMap<String, String> data = new HashMap<String, String>(){{
			put("token" , pr.getToken());
		}};
		
		JSONObject obj = sendPostRequest(data, URLs.STATS);
		if(obj == null)
			return null;
		
		String status;
		try {
			JSONObject expense = obj.getJSONObject("expense");
			JSONObject income = obj.getJSONObject("income");
			
			HashMap<String, Integer> map = new HashMap<String, Integer>(){{
				put("expense_amount", expense.getInt("amount__sum"));
				put("expense_count" , expense.getInt("amount__count"));
				
				put("income_amount", income.getInt("amount__sum"));
				put("income_count" , income.getInt("amount__count"));
			}};

			return map;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
