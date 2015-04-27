package com.harshit.ionBox.DB;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

import android.os.AsyncTask;

public class OffloadTask extends AsyncTask<bckModel, Void, JSONObject> {
	private CallbackContext callbackContext;

	public OffloadTask(CallbackContext _callbackContext) {
		this.callbackContext = _callbackContext;
	}

	protected JSONObject doInBackground(bckModel... _obj) {
		JSONObject json = new JSONObject();
		bckModel obj = _obj[0];
		String val = "";


		if (obj.reqName.equalsIgnoreCase("getFolderContent")) {
			try{
				val=makeGetRequest(null, obj.URL, obj.token);
				json.put("success", "true");
				json.put("response", new JSONObject(val));
				return json;
			}
			catch(Exception e){
				callbackContext.error(e.toString());
				return null;
			}
		}// end of auth service do background



		try {
			json.put("SUCCESS", "FALSE");
			json.put("ERROR", val);
			return json;
		} catch (Exception e) {
			callbackContext.error(e.toString());
			return null;
		}
		// return json;
	}

	protected void onPostExecute(JSONObject resp) {
		try {
			if (resp != null
					&& !(resp.getString("success").equalsIgnoreCase("false"))) {
				LOG.i("JSON obj", resp.toString());
				PluginResult result = new PluginResult(PluginResult.Status.OK,
						resp);
				result.setKeepCallback(false);
				callbackContext.sendPluginResult(result);
			} else {
				PluginResult result = new PluginResult(
						PluginResult.Status.ERROR,
						"{\"ERROR\":\"SOMTHING IS WRONG\"}");
				result.setKeepCallback(false);
				callbackContext.sendPluginResult(result);
			}
		} catch (Exception e) {
			PluginResult result = new PluginResult(PluginResult.Status.ERROR,
					"{\"ERROR\":\"SOMTHING IS WRONG\"}");
			result.setKeepCallback(false);
			callbackContext.sendPluginResult(result);
		}
	}

	private String makePostRequest(String rawRequest, String SOAPAction, String URL) {
		try {
			StringBuffer rspString = new StringBuffer();
			HttpURLConnection con = (HttpURLConnection) (new URL(URL))
					.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			con.setRequestProperty("Content-Length",
					String.valueOf(rawRequest.getBytes().length));
			con.setRequestProperty("Accept-Encoding", "gzip,deflate");
			LOG.i("Length:", String.valueOf(rawRequest.length()));
			LOG.i("ByteLength:", String.valueOf(rawRequest.getBytes().length));
			con.setRequestProperty("SOAPAction", SOAPAction);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.getOutputStream().write(rawRequest.getBytes());
			con.connect();
			int reqStatus = con.getResponseCode();
			LOG.i("Status Code", String.valueOf(reqStatus));
			if (reqStatus == 200) {
				InputStream is = con.getInputStream();
				// InputStream is = con.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(is));
				// StringBuilder total = new StringBuilder();
				String line;
				while ((line = r.readLine()) != null) {
					rspString.append(line);
				}
				con.disconnect();
				// appendLOG("Response********");
				// appendLOG(rspString.toString());
				LOG.i("Request", "val:" + rawRequest);
				//LOG.i("Response", "val==" + rspString.toString());
				return rspString.toString();
			}
			return "_MYC0lt_432_ERR0R_INVALID Response from server, status code not 200 but "
			+ reqStatus;
		} catch (Exception e) {
			LOG.e("WEB RESPONSE DATA", e.toString());
			return "_MYC0lt_432_ERR0R_" + e.toString();
		}

	}

	private String makeGetRequest(JSONObject args,
			String URL, String auth) {
		try {
			StringBuffer rspString = new StringBuffer();

			HttpURLConnection con = (HttpURLConnection) (new URL(URL))
					.openConnection();
			con.setRequestMethod("GET");
			//con.setRequestProperty("Connection", "Keep-Alive");
			//con.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			//con.setRequestProperty("SOAPAction", SOAPAction);
			/*con.setRequestProperty("Content-Length",
					String.valueOf(rawRequest.getBytes().length));*/
			con.setRequestProperty("Authorization", "Bearer "+auth);
			//con.setRequestProperty("Accept-Encoding", "gzip,deflate");
			con.setDoInput(true);
			con.setDoOutput(true);
			LOG.i("AuthVal", auth);
			LOG.e("Request********",URL);
			//LOG.i();
			//con.getOutputStream().write(rawRequest.getBytes());
			con.connect();
			int reqStatus = con.getResponseCode();
			LOG.i("Status Code", String.valueOf(reqStatus));
			if (reqStatus == 200) {
				InputStream is = con.getInputStream();
				// InputStream is = con.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(is));
				// StringBuilder total = new StringBuilder();
				String line;
				while ((line = r.readLine()) != null) {
					rspString.append(line);
				}
				con.disconnect();
				// appendLOG("Response********");
				// appendLOG(rspString.toString());
				//	LOG.i("Request", "val:" + rawRequest);
				LOG.i("Response", "val==" + rspString.toString());
				return rspString.toString();
			}
			return "_MYC0lt_432_ERR0R_INVALID Response from server, status code not 200 but "
			+ reqStatus;
		} catch (Exception e) {
			LOG.e("WEB RESPONSE DATA", e.toString());
			for (StackTraceElement el : e.getStackTrace()) {
				LOG.e("Exception point", el.toString());
			}
			return "error";
		}

	}



}
