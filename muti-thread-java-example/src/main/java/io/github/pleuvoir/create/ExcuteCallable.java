package io.github.pleuvoir.create;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 带返回值的线程
 * @author pleuvoir
 *
 */
public class ExcuteCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		// 必须先包装一下
		FutureTask<String> futureTask = new FutureTask<>(new ExcuteCallable());
		new Thread(futureTask).start();
		
		String result = futureTask.get();
		System.out.println(result);
		
	}
}
