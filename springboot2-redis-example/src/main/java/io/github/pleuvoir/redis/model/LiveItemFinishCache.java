package io.github.pleuvoir.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

/**
 * 专场已结拍拍品，出价时检查该缓存
 */
@RedisHash("liveItemFinish")
public class LiveItemFinishCache {

	@Id
	private String itemId;		//专场拍品编号

	private String liveId;		//专场编号

	private String expireAt;		//专场拍品结拍时间 yyyy-MM-dd HH:mm:ss

	@TimeToLive(unit = TimeUnit.HOURS)
	private Long ttl = 24L * 5;		//缓存有效时间（5天）

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}

	public String getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}

	public Long getTtl() {
		return ttl;
	}

	public void setTtl(Long ttl) {
		this.ttl = ttl;
	}
}
