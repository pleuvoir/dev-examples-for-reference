package io.github.pleuvoir.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 专场当前拍品
 */
@RedisHash(value = "liveCurrentItem", timeToLive = 24L * 3) //都可以设置，下面的 ttl 也是可以的，两个一般只设置一个
public class LiveCurrentItemCache implements Serializable{

	private static final long serialVersionUID = -7776767138520912990L;

	@Id
	private String liveId;		//专场编号

	private String itemId;		//专场当前拍品编号

	private String lot;			//本专场拍品号

	private String itemExpireAt;		//专场拍品结拍时间

	@TimeToLive(unit = TimeUnit.HOURS)
	private Long ttl = 24L * 3;			//缓存存在时间（3天）

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemExpireAt() {
		return itemExpireAt;
	}

	public void setItemExpireAt(String itemExpireAt) {
		this.itemExpireAt = itemExpireAt;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Long getTtl() {
		return ttl;
	}

	public void setTtl(Long ttl) {
		this.ttl = ttl;
	}
}
