package io.github.pleuvoir.jvm;

/**
 * 栈上分配（开启后速度 0ms 不开1571ms)
 * 
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis  -XX:+PrintGC -XX:+EliminateAllocations -XX:-UseTLAB
 * 
 * <pre>
 * 
 * 如何启用栈上分配
	-server JVM运行的模式之一, server模式才能进行逃逸分析， JVM运行的模式还有mix/client
	-Xmx10m和-Xms10m：堆的大小
	-XX:+DoEscapeAnalysis：启用逃逸分析(默认打开)
	-XX:+PrintGC：打印GC日志
	-XX:+EliminateAllocations：标量替换(默认打开)
	-XX:-UseTLAB 关闭本地线程分配缓冲
	TLAB： ThreadLocalAllocBuffer
	对栈上分配发生影响的参数就是三个，-server、-XX:+DoEscapeAnalysis和-XX:+EliminateAllocations，任何一个发生变化都不会发生栈上分配。
	因为启用逃逸分析和标量替换默认是打开的，所以，在我们的例子中，JVM的参数只用-server一样可以有栈上替换的效果(以JDK1.8为例，其他版本JDK请自行尝试)。

 * 
 * <pre>
 * @author pleuvoir
 *
 */
public class StackAllocate {

	public static class User {
		public int id = 0;
		public String name = "";

		User() {
		}
	}
	
	public static User alloc() {
		User u = new User();
		u.id = 5;
		u.name = "pleuvoir";
		return u;
	}
	
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println((e - b) + "ms");
	}
}
