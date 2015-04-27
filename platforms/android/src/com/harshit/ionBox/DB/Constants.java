package com.harshit.ionBox.DB;



public class Constants {

/*	public static final String DevIdKey="DeviceID_myC0lt_435";
	public static final String UserId="UserID_myC0lt_435";
	public static final String Password="Pass_myC0lt_435";
	public static final String isAuthenticated="isAuthenticated_myC0lt_435";
	public static final String authResponseKey="authResponse_myC0lt_435";
	public static final String ticketListMax="ticketListMax_myC0lt_435";
	public static final String serviceListMax="serviceListMax_myC0lt_435";
	public static final String ocnListKey="ocnList_myC0lt_435";
	public static final String isPatnerKey="isPatner_myC0lt_435";
	public static final String refOcnKey="refOcn_myC0lt_435";
	https://www.dropbox.com/1/oauth2/authorize
*/
	public static final String authorizeUrl="https://www.dropbox.com/1/oauth2/authorize";
	public static final String callbackUrl="http://localhost";
	public static final String accountInfoUrl="https://api.dropbox.com/1/account/info";
	public static final String getFileUrl="https://api-content.dropbox.com/1/files/auto";
	public static final String getFileMetadataUrl="https://api.dropbox.com/1/metadata/auto";
	public static final String searchFileUrl="https://api.dropbox.com/1/search/auto";
	public static final String getThumbNailUrl="https://api-content.dropbox.com/1/thumbnails/auto";
	public static final String getPreviewOfDoc="https://api-content.dropbox.com/1/previews/auto";
	public static final String getSharedFolder="https://api.dropbox.com/1/shared_folders";
	public static boolean strEmptyOrNull(String value) {
		if(value!=null && !value.isEmpty())
		{
			return false;
		}
		return true;
	}
}
