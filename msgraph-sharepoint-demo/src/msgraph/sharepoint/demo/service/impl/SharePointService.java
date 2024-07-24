package msgraph.sharepoint.demo.service.impl;


import java.util.List;

import com.microsoft.graph.MicrosoftGraphDriveItem;
import com.microsoft.graph.MicrosoftGraphSite;


import msgraph.sharepoint.demo.service.ISharePointService;
import msgraph.sharepoint.demo.service.MsGraphServiceUtil;

public class SharePointService implements ISharePointService {
	
	@Override
	public List<MicrosoftGraphSite> searchSites(){
		return null;
	}

	@Override
	public List<MicrosoftGraphDriveItem> getDriveItemByDriveId(String driveId) {
		return MsGraphServiceUtil.getDriveItemByDriveId(driveId);
	}

	@Override
	public List<MicrosoftGraphDriveItem> getDriveItemByItemId(String driveId, String itemId) {
		return MsGraphServiceUtil.getDriveItemByItemId(driveId, itemId);
	}

	@Override
	public void createNewFolderInDriveItem(String folderName, String driveId, String itemId) {
		MicrosoftGraphDriveItem item = new MicrosoftGraphDriveItem();
		item.setName(folderName);
		MsGraphServiceUtil.createNewFolderInDriveItem(driveId, itemId, item);
	}

	@Override
	public void deleteDriveItem(String driveId, String itemId) {
		MsGraphServiceUtil.deleteDriveItem(driveId, itemId);
	}
	
	@Override
	public void uploadFile(String siteId, String itemId, String fileName) {
		MsGraphServiceUtil.uploadFile(siteId, itemId, fileName);
	}
	

}



















