package main;

import java.io.File;

public class LoadModel {
	private File file;

	public LoadModel(){}

	public void setFile(File file){
		this.file = file;
	}

	public File getFile(){
		System.out.print("getting the file\n");
		return this.file;
	}
}
