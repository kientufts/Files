package net.files.controller;

import net.files.bean.ExplorerElement;
import net.files.type.ElementType;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class FileExplorer {
	private String currentPath;
	private List<ExplorerElement> elements;
	private File currentFile;

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
}
