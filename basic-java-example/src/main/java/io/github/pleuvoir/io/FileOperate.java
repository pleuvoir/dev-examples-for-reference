package io.github.pleuvoir.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Cleanup;
import lombok.SneakyThrows;

/**
 * 简单的文件操作，创建文件，删除文件，复制文件
 * <p>这里偷了个懒 用lombok处理了异常和流的关闭
 * @author pleuvoir
 *
 */
public class FileOperate {

	public static void create(String pathname){
		File file = new File(pathname);
		try {
			file.createNewFile();
			System.out.println("文件创建成功，pathname：" + pathname);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("创建文件失败，pathname：" + pathname);
		}
	}
	
	@SneakyThrows
	public static void write(String filepath,String contents){
		File file = new File(filepath);
		@Cleanup
		FileOutputStream os = new FileOutputStream(file);
		os.write(contents.getBytes());
		System.out.println("文件已生成，filepath：" + filepath);
	}
	
	public static void delete(String pathname) {
		File file = new File(pathname);
		if (file.exists()) {
			file.delete();
			System.out.println("文件删除成功，pathname：" + pathname);
		}else{
			System.err.println("文件不存在，pathname：" + pathname);
		}
	}
	
	@SneakyThrows
	public static void copy(String src, String des) {
		long start = System.currentTimeMillis();
		File srcFile = new File(src);
		File desFile = new File(des);
		// 加载源文件到输入流
		@Cleanup
		FileInputStream input = new FileInputStream(srcFile);
		// 创建输出流
		@Cleanup
		FileOutputStream out = new FileOutputStream(desFile);
		// 创建字节数组
		byte[] bytes = new byte[(int) srcFile.length()];
		for (int i = 0; i < bytes.length; i++) {
			// 挨个字节读取
			bytes[i] = (byte) input.read();
		}
		// 写入到输出流
		out.write(bytes);
		System.out.println("复制成功，耗时：" + (System.currentTimeMillis() - start) + "ms，源文件：" + src + "目标文件：" + des);
	}
	
	
	// 效率更高
	@SneakyThrows
	public  static void copy2(String src,String des){
		long start = System.currentTimeMillis();
		@Cleanup
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(src)));
		@Cleanup
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(des)));
		byte[] bytes = new byte[1024];
		while(bis.read(bytes) != -1){ //将流添加至缓冲区，此处会循环多次，当buffer容量到达上限时，会触发真正的磁盘写入。
			bos.write(bytes);
		}
		bos.flush();
		System.out.println("复制成功，耗时：" + (System.currentTimeMillis() - start) + "ms，源文件：" + src + "目标文件：" + des);
	}
	
	
	public static void main(String[] args) throws IOException {
		create("D://hello.txt");
		delete("D://hello.txt");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 100000; i++) {
			sb.append(new SimpleDateFormat().format(new Date()) + "\r\n");
		}
		write("D://时间.txt", sb.toString());
		copy("D://时间.txt", "D://复制的产物.txt");
		copy2("D://时间.txt", "D://复制的产物2.txt");
	}
}
