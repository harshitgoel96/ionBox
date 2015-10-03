package com.harshit.ionBox.DB;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;

import android.content.Context;
import android.os.AsyncTask;

public class IonBoxServices {
	private Context context;

	public IonBoxServices(Context _context) {
		context = _context;
	}
	public void getFolderContent(String path,String token,CallbackContext callbackContext){
		//String url=Constants.getFileMetadataUrl;
		bckModel model=new bckModel();
		model.URL=Constants.getFileMetadataUrl+path;
		model.token=token;
		model.reqName="getFolderContent";
		OffloadTask backgroundRequest=new OffloadTask(callbackContext);
		backgroundRequest.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,model);
		LOG.e("BackGround "+model.reqName,"Request made");
	}
}
