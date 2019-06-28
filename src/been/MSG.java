package been;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Williyam
 * 
 */
public class MSG implements Serializable {

	private static final long serialVersionUID = 1L;

	private int message_id;
	private String message_type;
	private String message;
	private int sender_user_id;
	private int receiver_user_id;
	private Date sending_date;
	private Date receiving_date;
	private int is_send;
	private int is_received;
	private int is_seen;

	public int getMessage_id() {
		return message_id;
	}

	/**
	 * @param message_id
	 *            the message_id to set
	 */
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getMessage_type() {
		return message_type;
	}

	/**
	 * @param message_type
	 *            the message_type to set
	 */
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public int getSender_user_id() {
		return sender_user_id;
	}

	/**
	 * @param sender_user_id
	 *            the sender_user_id to set
	 */
	public void setSender_user_id(int sender_user_id) {
		this.sender_user_id = sender_user_id;
	}

	public Date getSending_date() {
		return sending_date;
	}

	/**
	 * @param sending_date
	 *            the sending_date to set
	 */
	public void setSending_date(Date sending_date) {
		this.sending_date = sending_date;
	}

	public Date getReceiving_date() {
		return receiving_date;
	}

	/**
	 * @param receiving_date
	 *            the receiving_date to set
	 */
	public void setReceiving_date(Date receiving_date) {
		this.receiving_date = receiving_date;
	}

	public int getIs_received() {
		return is_received;
	}

	/**
	 * @param is_received
	 *            the is_received to set
	 */
	public void setIs_received(int is_received) {
		this.is_received = is_received;
	}

	public int getIs_seen() {
		return is_seen;
	}

	/**
	 * @param is_seen
	 *            the is_seen to set
	 */
	public void setIs_seen(int is_seen) {
		this.is_seen = is_seen;
	}

	public int getReceiver_user_id() {
		return receiver_user_id;
	}

	/**
	 * @param receiver_user_id
	 *            the receiver_user_id to set
	 */
	public void setReceiver_user_id(int receiver_user_id) {
		this.receiver_user_id = receiver_user_id;
	}

	public int getIs_send() {
		return is_send;
	}

	/**
	 * @param is_send
	 *            the is_send to set
	 */
	public void setIs_send(int is_send) {
		this.is_send = is_send;
	}

}
