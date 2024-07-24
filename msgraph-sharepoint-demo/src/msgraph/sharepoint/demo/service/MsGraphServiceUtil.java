package msgraph.sharepoint.demo.service;

import java.util.List;

import com.microsoft.graph.MicrosoftGraphDriveItem;

import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;

public class MsGraphServiceUtil {

	private static final String MSGRAPH_FILE_SERVICE = "msFiles";
	private static final String ITEM_ATTRIBUTE = "items";

	@SuppressWarnings("unchecked")
	public static List<MicrosoftGraphDriveItem> getDriveItemByDriveId(String driveId) {
		SubProcessCallResult callResult = SubProcessCall.withPath(MSGRAPH_FILE_SERVICE)
				.withStartSignature("getDriveItemByDriveId(String)")
				.withParam("driveId", driveId)
				.call();

		return (List<MicrosoftGraphDriveItem>) callResult.get(ITEM_ATTRIBUTE);
	}
	
	@SuppressWarnings("unchecked")
	public static List<MicrosoftGraphDriveItem> getDriveItemByItemId(String driveId, String itemId) {
		SubProcessCallResult callResult = SubProcessCall.withPath(MSGRAPH_FILE_SERVICE)
				.withStartSignature("getDriveItemByItemId(String, String)")
				.withParam("driveId", driveId)
				.withParam("driveItemId", itemId)
				.call();

		return (List<MicrosoftGraphDriveItem>) callResult.get(ITEM_ATTRIBUTE);
	}
	
	public static void createNewFolderInDriveItem(String driveId, String itemId, MicrosoftGraphDriveItem item) {
		SubProcessCall.withPath(MSGRAPH_FILE_SERVICE)
				.withStartSignature("createFolderInItem(String,String,MicrosoftGraphDriveItem)")
				.withParam("driveId", driveId)
				.withParam("driveItemId", itemId)
				.withParam("item", item)
				.call();
	}
	
	public static void deleteDriveItem(String driveId, String itemId) {
		SubProcessCall.withPath(MSGRAPH_FILE_SERVICE)
				.withStartSignature("deleteDriveItem(String,String)")
				.withParam("driveId", driveId)
				.withParam("driveItemId", itemId)
				.call();
	}
	
	// /sites/{site-id}/drive/items/{parent-id}:/{filename}:/content
	public static void uploadFile(String siteId, String itemId, String fileName) {
		
	}
	
	

}
