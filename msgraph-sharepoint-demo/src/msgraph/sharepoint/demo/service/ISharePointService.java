package msgraph.sharepoint.demo.service;

import java.util.List;

import com.microsoft.graph.MicrosoftGraphDriveItem;
import com.microsoft.graph.MicrosoftGraphSite;

public interface ISharePointService {
	
	List<MicrosoftGraphSite> searchSites();
	List<MicrosoftGraphDriveItem> getDriveItemByDriveId(String driveId);
	List<MicrosoftGraphDriveItem> getDriveItemByItemId(String driveId, String itemId);
	void createNewFolderInDriveItem(String folderName, String driveId, String itemId);
	void deleteDriveItem(String driveId, String itemId);
	void uploadFile(String siteId, String itemId, String fileName);
	
	
}
