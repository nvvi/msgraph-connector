package msgraph.sharepoint.demo.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import com.microsoft.graph.MicrosoftGraphDriveItem;
import com.microsoft.graph.MicrosoftGraphSite;

import ch.ivyteam.ivy.environment.Ivy;

import msgraph.sharepoint.demo.service.ISharePointService;
import msgraph.sharepoint.demo.service.impl.SharePointService;
import msgraph.sharepoint.demo.utils.MessageUtil;

@ManagedBean(name = "overviewBean")
@ViewScoped
public class OverviewBean {
	
	// content state
	private boolean showSummarySection;
	
	private ISharePointService sharePointService;
	private String newFolderName;
	private MicrosoftGraphDriveItem selectedDriveItem;
	private List<MicrosoftGraphDriveItem> driveItems;

	private static final String driveId = "b!9-NuiI_iDEGDJQs1bqybdQbuDngjOaFMtSO9dicFuHALv0sFOD21RLgrJrOkwOx2";
	
	private String currentDriveItemId;
	
	private UploadedFile file;
	
	private StreamedContent fileDownload;
	
	private MicrosoftGraphSite selectedSite;
	


	
	public OverviewBean() {
		sharePointService = new SharePointService();
	}

	@PostConstruct
	public void init() {
		driveItems = sharePointService.getDriveItemByDriveId(driveId);
		currentDriveItemId = "root";
		
		try {

			fileDownload = DefaultStreamedContent.builder()
                .name("test.PNG")
                .contentType("image/jpg")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("//test.PNG"))
                .build();
			
			Ivy.log().info(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
			Ivy.log().info(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());

			
		}catch(Exception ex) {
			
			Ivy.log().info("error: "+ex);
			
		}
	}
	
	public StreamedContent getDownloadValue() throws Exception {
	    StreamedContent download=new DefaultStreamedContent();
	    File file = new File("C:\\file.csv");
	    InputStream input = new FileInputStream(file);
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    download = new DefaultStreamedContent();
	    System.out.println("PREP = " + download.getName());
	    return download;
	}

    public void onFileUploadHandler(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        // call upload file here
        Ivy.log().info("handleFileUpload called");
        
        
        
        

        
	}
    
    public void onFileDownloadHandler() {
    	
    }
	
	public List<MicrosoftGraphSite> completeSearchSite(String query) {
		String queryLowerCase = query.toLowerCase();
		List<MicrosoftGraphSite> countries = sharePointService.searchSites();
		return countries.stream().filter(t -> t.getName().toLowerCase().contains(queryLowerCase))
				.collect(Collectors.toList());
	}
	
	public void onSiteChange(){
		this.showSummarySection = true;
	}

	public void onRowDoubleClick() {
		if(selectedDriveItem.getFile() != null) {
			return;
		}

		driveItems = sharePointService.getDriveItemByItemId(driveId, selectedDriveItem.getId());
		
		if(selectedDriveItem != null) {
			currentDriveItemId = selectedDriveItem.getId();
		}
	}
	
	public void openNewFolderHandler() {
		
	}
	
	public void goToRoot() {
		driveItems = sharePointService.getDriveItemByDriveId(driveId);
		currentDriveItemId = "root";
	}
	
	public void addNewFolderHandler() {
		sharePointService.createNewFolderInDriveItem(newFolderName, driveId, currentDriveItemId);
		
		// load latest driveItem
		driveItems = sharePointService.getDriveItemByItemId(driveId, currentDriveItemId);
		
		newFolderName = null;
		MessageUtil.showInfoWithMessage("Created folder successfully ");
	}
	
	
	public void deleteDriveItem(String driveItemId) {
		Ivy.log().info("Deleting drive item id: "+driveItemId);
		
		sharePointService.deleteDriveItem(driveId, driveItemId);
		
		// load latest driveItem
		driveItems = sharePointService.getDriveItemByItemId(driveId, currentDriveItemId);
		
		MessageUtil.showInfoWithMessage("Delete item successfully ");
	}
	
	public void openNew() {
		Ivy.log().info("openNew called");
	}

	public void uploadFolder() {
		Ivy.log().info("uploadFolder called");
	}
	
	public void uploadFile() {
		Ivy.log().info("uploadFile called");
	}
	
	public void upload() {
		Ivy.log().info("upload called");
	}
	
	public void download() {
		Ivy.log().info("download called");
	}
	
	public void delete() {
		Ivy.log().info("download called");
	}

	public boolean getShowSummarySection() {
		return showSummarySection;
	}

	public void setShowSummarySection(boolean showSummarySection) {
		this.showSummarySection = showSummarySection;
	}

	public String getNewFolderName() {
		return newFolderName;
	}

	public void setNewFolderName(String newFolderName) {
		this.newFolderName = newFolderName;
	}

	public List<MicrosoftGraphDriveItem> getDriveItems() {
		return driveItems;
	}

	public void setDriveItems(List<MicrosoftGraphDriveItem> driveItems) {
		this.driveItems = driveItems;
	}

	public MicrosoftGraphDriveItem getSelectedDriveItem() {
		return selectedDriveItem;
	}

	public void setSelectedDriveItem(MicrosoftGraphDriveItem selectedDriveItem) {
		this.selectedDriveItem = selectedDriveItem;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public MicrosoftGraphSite getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(MicrosoftGraphSite selectedSite) {
		this.selectedSite = selectedSite;
	}
	
}
