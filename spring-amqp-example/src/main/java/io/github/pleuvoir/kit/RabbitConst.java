package io.github.pleuvoir.kit;

public class RabbitConst {

	/** 专场开始 */
	public static class LiveBegin{

		public static final String EXCHANGE = "auction.x.liveBegin";

		public static final String QUEUE = "auction.q.liveBegin";

		public static final String ROUTING_KEY = "r.live.begin";
	}

	/** 到达专场开始时间 */
	public static class LiveBeginExpire{

		public static final String EXCHANGE = "auction.x.liveBeginExpire";

		public static final String QUEUE = "auction.q.liveBeginExpire";

		public static final String ROUTING_KEY = "r.live.beginExpire";
	}

}
