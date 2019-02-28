package io.github.pleuvoir.jvm;

import java.nio.ByteBuffer;

/**
 * 演示直接内存溢出 <br>
 * 
 * -XX:MaxDirectMemorySize=10m 直接内存设置10m
 * @author pleuvoir
 *
 */
public class DirectMem {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ByteBuffer b = ByteBuffer.allocateDirect(1024 * 1024 * 14);
	}

	
	// Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
}
