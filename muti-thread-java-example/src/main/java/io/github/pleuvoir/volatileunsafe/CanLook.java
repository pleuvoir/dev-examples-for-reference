package io.github.pleuvoir.volatileunsafe;

import io.github.pleuvoir.kit.SleepUtil;

/**
 * 演示 volatile 是如何可见的
 * @author pleuvoir
 *
 */
public class CanLook {

	
	public /**volatile*/ static int sharedVal = 0;	// 共享变量

	public void listener() {
		int local = sharedVal;		// 一旦一个线程开启，sharedVal 的值可能永远是 刚开始的值，其他线程修改了值它是不知道的，那监听线程永远停不下来
		while(sharedVal < 5){		
			if (local != sharedVal) {		// 等到这里的时候  1 有可能不再 = 1，sharedVal 获得了刷新
				System.err.println("changed...");
			}
			//System.out.println(sharedVal);	// 这步有可能会刷新到主内存
			local = sharedVal;		// 这里的赋值如  1 = 1
		}
		System.out.println("over..");
	}
	
	public void increment() {
		while(sharedVal < 5){
			++sharedVal;
			System.out.println("增加了。。现在值为：" + sharedVal);
		}
	}
	
	public static void main(String[] args) {
		CanLook canLook = new CanLook();
		Thread listener = new Thread(() -> canLook.listener());
		Thread increment = new Thread(() -> canLook.increment());
		listener.start();
		SleepUtil.ms(100); // 测试结果是 让监听线程先走 ，修改线程后行。监听线程无法获得新值，如果反过来，则会获得
		increment.start();
	}
	
}
