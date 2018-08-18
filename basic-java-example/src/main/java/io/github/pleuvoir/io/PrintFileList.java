package io.github.pleuvoir.io;

import java.io.File;

/**
 * 打印文件目录
 * @author pleuvoir
 *
 */
public class PrintFileList {

	public static void print(File file){
		if(file.isDirectory()){
			System.out.println("进入目录：" + file);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File currentFile = files[i];
				System.out.println(i + 1 + "：" + currentFile.getName());
				print(currentFile);
			}
		}else{
			System.out.println("文件：" + file);
		}
	}
	
	
	public static void main(String[] args) {
		File file = new File("D:/Program Files (x86)/360/360DrvMgr/config");
		print(file);
	}
}
