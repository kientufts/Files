package net.files.bean;

import net.files.type.ElementType;

public class ExplorerElement {
	private ElementType type;
	private String name;
	private long size;

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
