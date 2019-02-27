package io.github.pleuvoir.jvm;

import java.util.LinkedList;
import java.util.List;

/**
 * 演示堆内存溢出
 * 
 * <p>
 * -Xms5m -Xmx5m -XX:+PrintGC 堆内存设置为 5M 大小 ，打印 GC 信息
 * </p>
 * @author pleuvoir
 *
 */
public class OOM {

	public static void main(String[] args) {

		List<Object> list = new LinkedList<>();
	//	List<Object> list = new ArrayList<>();     
		int i = 0;
		for (;;) {
			i++;
			if (i % 10000 == 0)
				System.out.println("i=" + i);
			list.add(new Object());
		}
	}
	
	
 // 1.	使用 ArrayList 扩容时发现内存不足，和 分配巨型数组 new String[10000000] 效果一致都是发现堆内存不足 （有巨型对象在分配，直接超出了堆的大小）
	
//	Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//	at java.util.Arrays.copyOf(Unknown Source)
//	at java.util.Arrays.copyOf(Unknown Source)
//	at java.util.ArrayList.grow(Unknown Source)
//	at java.util.ArrayList.ensureExplicitCapacity(Unknown Source)
//	at java.util.ArrayList.ensureCapacityInternal(Unknown Source)
//	at java.util.ArrayList.add(Unknown Source)
//	at io.github.pleuvoir.jvm.OOM.main(OOM.java:27)

	
// 2.  使用 LinkedList 报错如下
//  Exception in thread "main" [Full GC (Ergonomics) java.lang.OutOfMemoryError: GC overhead limit exceeded 
//	无限添加对象，当 GC 尝试回收多次后无果，撑爆了 堆溢出 （可能有循环一直在分配对象，GC 尝试无果撑爆了）
	
}
