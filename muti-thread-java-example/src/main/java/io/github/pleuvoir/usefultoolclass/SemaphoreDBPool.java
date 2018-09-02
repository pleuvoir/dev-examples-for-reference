package io.github.pleuvoir.usefultoolclass;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import io.github.pleuvoir.dbpool.Client.GetConnectionThread;
import io.github.pleuvoir.dbpool.helper.SleepTools;

public class SemaphoreDBPool {

	static int poolSize = 100;
	static Semaphore canuse, cannotuse;

	static LinkedList<Connection> pools = new LinkedList<>();

	
	public SemaphoreDBPool(){
		canuse = new Semaphore(poolSize); // 两者的和总是 poolSize
		cannotuse = new Semaphore(0);
	}
	
	static {
		for (int i = 0; i < poolSize; i++) {
			pools.add(DBConnection.fetchConnection());
		}
	}

	public Connection getConn() throws InterruptedException{
		System.out.println("当前连接池可用连接：" + canuse.availablePermits() + " 不可用：" + cannotuse.availablePermits());
		canuse.acquire();	// 从能用的里面要一个 能用数 -1
		Connection conn = null;
		synchronized (pools) {
			conn = pools.removeFirst();
		}
		cannotuse.release();	// 不能用的 + 1 
		return conn;
	}
	
	public void returnConn(Connection conn) throws InterruptedException{
		if(conn != null){	
			cannotuse.acquire();	// 归还连接， 
			synchronized (pools) {
				pools.addLast(conn);
			}
			canuse.release();	// 能用的加 1
			System.out.println("当前连接池可用连接：" + canuse.availablePermits() + " 不可用：" + cannotuse.availablePermits());
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		SemaphoreDBPool DBPool = new SemaphoreDBPool();
		
		for(int i = 0 ;i<50;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						long start = System.currentTimeMillis();
						Connection conn = DBPool.getConn();
						System.out.println("线程："  + Thread.currentThread().getName() + " 拿连接耗时：" + (System.currentTimeMillis() -start) + "ms");
						SleepTools.second(1 + new Random().nextInt(30));
						DBPool.returnConn(conn);
						System.out.println("归还连接。。。");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();;
		}
	
	}
	
}
