package net.files.controller;

import net.files.bean.ExplorerElement;
import net.files.type.ElementType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class FileExplorer {
	private String currentPath;
	private List<ExplorerElement> elements;
	private File currentFile;
	private Part uploadFile;

	@PostConstruct
	public void init(){
		currentPath="/Users";
		elements = new ArrayList<>();
		loadAllFilesAndDirectories();
	}

	private void loadAllFilesAndDirectories(){
		currentFile = new File(currentPath);
		elements.clear();
		for(File f:currentFile.listFiles()){
			if(f.isHidden()) continue;
			ExplorerElement ee = new ExplorerElement();
			if(f.isDirectory()){
				ee.setType(ElementType.FOLDER);
			} else if (f.isFile()){
				ee.setType(ElementType.FILE);
			}
			ee.setName(f.getName());
			ee.setSize(f.length());
			elements.add(ee);
		}
	}

	public void enter(ExplorerElement e){
		currentPath = currentPath +"/"+ e.getName();
		loadAllFilesAndDirectories();
	}

	public void back(){
		currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
		loadAllFilesAndDirectories();
	}

	public void upload(){
		try {
			File copyFile = new File(currentPath + "/" + uploadFile.getSubmittedFileName());
			copyFile.createNewFile();

			InputStream inputStream = uploadFile.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream(copyFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length=inputStream.read(buffer))!=-1){
				fileOutputStream.write(buffer, 0, length);
			}
			fileOutputStream.close();
			loadAllFilesAndDirectories();
		} catch (Exception ex){
			ex.printStackTrace(System.out);
		}
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	public List<ExplorerElement> getElements() {
		return elements;
	}

	public void setElements(List<ExplorerElement> elements) {
		this.elements = elements;
	}

	public Part getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(Part uploadFile) {
		this.uploadFile = uploadFile;
	}
}
