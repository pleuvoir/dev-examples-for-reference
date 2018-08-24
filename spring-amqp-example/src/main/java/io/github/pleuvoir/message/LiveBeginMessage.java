package io.github.pleuvoir.message;

import java.time.LocalDateTime;

import io.github.pleuvoir.kit.ToJSON;

public class LiveBeginMessage implements ToJSON {

	/**
	 * 专场编号
	 */
	private String liveId;
	
	/**
	 * 专场开始时间
	 */
	private LocalDateTime beginTime;

	// getter and setter
	
	public LocalDateTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalDateTime beginTime) {
		this.beginTime = beginTime;
	}

	public String getLiveId() {
		return liveId;
	}

	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}
	
}
