package io.github.pleuvoir.jvm;

/**
 * 栈溢出
 * -Xss256k -XX:+PrintGC 栈内存 256K ，打印GC信息
 * @author pleuvoir
 *
 */
public class StackOOM {

	// Exception in thread "main" java.lang.StackOverflowError
	public static void main(String[] args) {

		StackOOM oom = new StackOOM();

		try {
			oom.test();
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("栈深度：" + oom.stackLength);
		}
	}

	private int stackLength = 1;

	private void test() {
		stackLength++;
		test();
	}
}
