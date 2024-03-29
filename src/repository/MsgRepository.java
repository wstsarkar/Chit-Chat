package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import been.MSG;
import utility.DateUtils;

/**
 * 
 * @author Williyam
 * 
 */
public class MsgRepository {

	
	private MSG Get(ResultSet rs) throws SQLException{
		MSG msg = new MSG();
		
		msg.setMessage_type(rs.getString("message_type"));
		msg.setMessage(rs.getString("message"));
		msg.setMessage_id(rs.getInt("message_id"));
		msg.setReceiver_user_id(rs.getInt("receiver_user_id"));
		msg.setReceiving_date(DateUtils.parse(rs.getString("receiving_date")));
		msg.setIs_received(rs.getInt("is_received"));
		msg.setSender_user_id(rs.getInt("sender_user_id"));
		msg.setSending_date(DateUtils.parse(rs.getString("sending_date")));
		msg.setIs_send(rs.getInt("is_send"));
		msg.setIs_seen(rs.getInt("is_seen"));
		return msg;
	}
	
	private List<MSG> list(String query){
		ResultSet rs = DataBaseHelper.executeSelectQuery(query);	

		List<MSG> list = new ArrayList<MSG>();
		try {
			MSG msg;
			while (rs.next()) {
				msg = Get(rs);
				list.add(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
/*	private MSG msg(String query){
		ResultSet rs = DataBaseHelper.executeSelectQuery(query);	
		MSG msg = null;
		try {
			if (rs.next()) {
				msg = Get(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	*/
	

	public int createMSG(MSG msg) {
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO MSGs (message_type,message,sender_user_id,receiver_user_id,sending_date,");
		query.append(" receiving_date,is_send,is_received,is_seen) ");
		query.append(" VALUES('"+msg.getMessage_type()+"','"+msg.getMessage()+"',"+msg.getSender_user_id()+",");
		query.append(" "+msg.getReceiver_user_id()+",'"+ DateUtils.format(msg.getSending_date())+"', ");
		query.append(" '"+ DateUtils.format(msg.getReceiving_date())+"',"+msg.getIs_send()+", ");
		query.append(" "+msg.getIs_received()+","+msg.getIs_seen()+");");
		
		int id = DataBaseHelper.executeQuery(query.toString());	
		return id;
	}

	public int updateMSG(MSG msg) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE MSGs SET ");
		query.append(" receiving_date='" +  DateUtils.format(msg.getReceiving_date())+"',");
		query.append(" is_send=" + msg.getIs_send()+",");
		query.append(" is_received=" + msg.getIs_received()+",");
		query.append(" is_seen=" + msg.getIs_seen()+" ");
		query.append(" WHERE message_id = "+msg.getMessage_id()+" ;");
		
		int id = DataBaseHelper.executeQuery(query.toString());	
		return id;
	}

	public List<MSG> getAllMyMsg(int userId, int chatingUserId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM MSGs ");
		query.append(" WHERE ((sender_user_id = " + userId+" AND receiver_user_id = " + chatingUserId + ") ");
		query.append(" OR (sender_user_id = " + chatingUserId +" AND receiver_user_id = " + userId+ " AND is_send = 1 )) ");
		query.append(" ORDER BY message_id ASC ;");
		return list(query.toString());
	}

	public List<MSG> getAllMySendMsg(int userId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM MSGs ");
		query.append(" WHERE sender_user_id = " + userId+" ");
		query.append(" AND is_send = 1 ");
		query.append(" ORDER BY message_id ASC ;");
		return list(query.toString());
	}

	public List<MSG> getAllMyReceiveMsg(int userId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM MSGs ");
		query.append(" WHERE receiver_user_id = " + userId+" ");
		query.append(" AND is_send = 1 ");
		query.append(" ORDER BY message_id ASC ;");
		return list(query.toString());
	}

	public List<MSG> getAllUnsendMSG(int userId) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM MSGs ");
		query.append(" WHERE receiver_user_id = " + userId+" ");
		query.append(" AND is_send = 0 ");
		query.append(" ORDER BY message_id ASC ;");
		return list(query.toString());
	}

}
