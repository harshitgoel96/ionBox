package com.harshit.ionBox.DB;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;


import org.json.JSONObject;



//import android.R.string;
import android.content.Context;
import android.webkit.WebSettings.PluginState;

public class IonBoxPlugin extends CordovaPlugin {
	private Context context;
	private IonBoxServices services;
	private CacheSystem cacheObj;
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		//callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.NO_RESULT));
		try {
			if (this.context == null) {
				this.context = this.cordova.getActivity()
						.getApplicationContext();
			}// end of context init
			if(this.services==null){
				this.services=new IonBoxServices(this.context);
			}
			if(this.cacheObj==null){
			this.cacheObj = CacheSystem.getObject(this.context);
			}
			if (action.equalsIgnoreCase("getFromCache")) {
				String key=args.getString(0);
				String value=getValueFromCache(key);
				LOG.e("@@@@@ get cache",key+":"+value);
				try{
					JSONObject json=new JSONObject();
					json.put("success",true);
					json.put("key",key);
					
					if(Constants.strEmptyOrNull(value)){
						json.put("valuePresent",false);
					}
					else{
						json.put("valuePresent",true);
						json.put("value", value);
						LOG.e("KEY VAL",value);
					}
					PluginResult result=new PluginResult(PluginResult.Status.OK,json);
					result.setKeepCallback(false);
					callbackContext.sendPluginResult(result);
					return true;
				}
				catch(Exception ex){
					LOG.e("Get Cache","EXCEPTION!!!!!!!!!! :" +ex);
					return true;
				}
			}
			if (action.equalsIgnoreCase("getFolderContent")) {
				boolean isRoot=args.getBoolean(0);
				String path="";
				if(isRoot)
				{
					path="/";
				}
				else{
					
					path=args.getString(1);
					LOG.e("getFOlderContent","path "+path);
				}
				getFolderContent(path, callbackContext);
			}
			if (action.equalsIgnoreCase("storeInCache")) {
				String key=args.getString(0);
				String value=args.getString(1);
				LOG.e("key:val", key+":"+value);
				try{
					JSONObject json=new JSONObject();
					json.put("success",true);
					json.put("key",key);
					if(setValueToCache(key, value)){
						json.put("isValueSet",true);
					}
					else{
						json.put("isValueSet",false);
					}
					PluginResult result=new PluginResult(PluginResult.Status.OK,json);
					result.setKeepCallback(false);
					callbackContext.sendPluginResult(result);
				}catch(Exception ex){
					
				}
			}

		}
		catch(Exception ex){
		}

		return true;
	}
	private void getFolderContent(String path,CallbackContext callbackContext){
		String token=this.getValueFromCache("token");
		this.services.getFolderContent(path,token,callbackContext);
	}
	private String getValueFromCache(String key)
	{
		String value=this.cacheObj.getCacheValue(key);
		//PluginResult result=new PluginResult(PluginResult.Status.OK);

		if(!Constants.strEmptyOrNull(value)){
			return value;
		}


		return null;
	}
	private boolean setValueToCache(String key,String value)
	{
		LOG.e("@@@@@@ Seeting value",key+":"+value);
		return this.cacheObj.addToCache(key, value);
		// false;
	}



}
