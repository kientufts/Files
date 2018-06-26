package net.files.bean;

import net.files.type.ElementType;

public class ExplorerElement {
	private ElementType type;
	private String name;
	private long size;

	public String sizeWithFormat(){
		if(size<1024){
			return size + " Byte";
		} else if (size < 1024*1024){
			return size/1024 + " KB";
		} else {
			return size/(1024*1024) + " MB";
		}
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
