package io.github.pleuvoir;

import com.google.common.collect.Lists;
import io.github.pleuvoir.model.PaymentOrderPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class PaymentItemReader implements ItemReader<List> {

	@Resource(name = Const.JDBC_TEMPLATE_NAME_MASTER)
	private JdbcTemplate master;

	@Resource(name = Const.JDBC_TEMPLATE_NAME_SLAVE)
	private JdbcTemplate slave;


	@Override
	public List read() {


		String cnt = "select count(1) from t_payment_order_1 ";


		Long count = master.queryForObject(cnt, Long.class);
		log.info("共有{}条记录",count);


		//		master.query(cnt, null,null,new RowCountCallbackHandler(){
//			@Override
//			protected void processRow(ResultSet rs, int rowNum) throws SQLException {
//
//			}
//		});

		String sql = "select * from t_payment_order_1 ";
//
//		List<PaymentOrderPO> rows = (List<PaymentOrderPO>) master
//				.queryForObject(sql, new RowMapperImpl());
//
//	//	ArrayList<PaymentOrderPO> rows = excuteSql(sql);
//
//		log.info("获取到{}条记录", rows.size());

		//from db
		ArrayList<Object> result = Lists.newArrayList();
		result.add("payment-1");
		result.add("payment-2");

		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (ThreadLocalRandom.current().nextBoolean()) {
			throw new RuntimeException("订单获取数据运行时异常");
		}
		return result;
	}


	public static class RowMapperImpl implements RowMapper<PaymentOrderPO> {

		AtomicLong a = new AtomicLong();

		PaymentOrderPO po = new PaymentOrderPO();

		@Override
		public PaymentOrderPO mapRow(ResultSet rs, int rowNum) throws SQLException {
			po.setPayId(rs.getString("pay_id"));
		//	log.info("list add .. {},{} ",a.getAndIncrement(),po);
			return null;
		}
	}


	protected ArrayList<PaymentOrderPO> excuteSql(String sql) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = this.master.getDataSource().getConnection();
			ps = (PreparedStatement) connection.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			ps.setFetchSize(Integer.MIN_VALUE);

			ps.setFetchDirection(ResultSet.FETCH_REVERSE);

			log.info("executeQuery before");
			rs = ps.executeQuery();
			log.info("executeQuery after");

			ArrayList<PaymentOrderPO> list = Lists.newArrayList();

			while (rs.next()){
				PaymentOrderPO po = new PaymentOrderPO();
				po.setPayId(rs.getString("pay_id"));
				po.setPayConfigId(rs.getString("pay_config_id"));
				list.add(po);
				log.info("list add ..");
			}

			return list;


		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		return null;
	}
}
