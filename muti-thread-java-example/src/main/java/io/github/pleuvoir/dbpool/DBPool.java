package io.github.pleuvoir.dbpool;

import java.sql.Connection;
import java.util.LinkedList;

public class DBPool {

	private static LinkedList<Connection> pool = new LinkedList<>();
	
	
	public DBPool(int initial){
		for (int i = 0; i < initial; i++) {
			pool.addLast(DBConnection.get());
		}
	}
	
	// 如果 小于 0 则代表无限等待
	public Connection fetchConn(long timeout) throws InterruptedException {
		synchronized (pool) {
			if (timeout < 0) {
				while (pool.isEmpty()) {
					this.wait();
				}
				return pool.removeFirst();

			} else {
				
				// 超时时间
				long overtime = System.currentTimeMillis()+timeout;
				// 倒计时时间
				long remain = timeout;
				
				// 当没有资源 且倒计时大于零时一直等待
				while (remain > 0 && pool.isEmpty()) {
					// 等待几秒
					this.wait(remain);
					// 如果在等待过程中 收到通知 则重新计算剩余等待时间
					remain = overtime - System.currentTimeMillis();
				}
				// 超时了，或者拿到连接了
				if(!pool.isEmpty()){
					return pool.removeFirst();
				}else{
					return null;
				}
			}
		}
	}
	
	
	
	// 释放连接
	public void releaseConn(Connection conn){
		if(conn != null){
			synchronized (pool) {
				pool.addLast(conn);
			//	System.out.println(this);
			//	System.out.println(pool);
				pool.notifyAll();	// 告知所有持有连接池锁对象的线程 可以抢锁了
			}
		}
	}
	
}
