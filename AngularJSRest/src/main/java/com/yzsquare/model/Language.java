package com.yzsquare.model;

public class Language {

	private String name;

	private String fluencyLevel;

	private Boolean canRead;

	private Boolean canWrite;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFluencyLevel() {
		return fluencyLevel;
	}

	public void setFluencyLevel(String fluencyLevel) {
		this.fluencyLevel = fluencyLevel;
	}

	public Boolean getCanRead() {
		return canRead;
	}

	public void setCanRead(Boolean canRead) {
		this.canRead = canRead;
	}

	public Boolean getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(Boolean canWrite) {
		this.canWrite = canWrite;
	}

}
