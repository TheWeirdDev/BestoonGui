package ir.bestoon.alireza6677.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import ir.bestoon.alireza6677.connection.Connection;

public class Tests {

	public static void main(String[] args) {
		Connection c = new Connection();
		c.login("alireza6677", "Alireza1248"); 
		System.out.println(c.isLoggedIn());
		

		
	}

}
