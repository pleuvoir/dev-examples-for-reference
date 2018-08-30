package io.github.pleuvoir.dbpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import io.github.pleuvoir.kit.SleepUtil;

public class Client {
	
	static DBPool dbPool = new DBPool(1);
	static CountDownLatch countDownLatch = new CountDownLatch(1000);
	
	public static class GetConnectionThread implements Runnable{

		@Override
		public void run() {
			Connection conn = null;
			try {
				
				 conn = dbPool.fetchConn(1000);
				if( conn != null){
					// 执行某些操作
					try {
						conn.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						dbPool.releaseConn(conn);
					}
					System.out.println(Thread.currentThread().getName() + " 获得了连接。。");
				}else{
					System.out.println(Thread.currentThread().getName() + " 失败。。");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			countDownLatch.countDown();
		}
	}
	
	

	public static void main(String[] args) throws InterruptedException {
		int count = 1000;
		
		for(int i = 0;i<count;i++){
			new Thread(new GetConnectionThread()).start();
		}
		
		countDownLatch.await();
		
		System.out.println("main thread start");
		
	}
}
