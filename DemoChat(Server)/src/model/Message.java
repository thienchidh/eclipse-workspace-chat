
package model;

import java.io.*;
import java.util.*;

public class Message implements Serializable {

	String	owner;
	String	content;
	Date	dateSend;

	public Message(String owner, String content) {

		super();
		this.owner = owner;
		this.content = content;
		this.dateSend = Calendar.getInstance().getTime();
	}

	public Message(String owner, String content, Date dateSend) {

		super();
		this.owner = owner;
		this.content = content;
		this.dateSend = dateSend;
	}

	/**
	 * @return the content
	 */
	public String getContent() {

		return content;
	}

	/**
	 * @return the dateSend
	 */
	public Date getDateSend() {

		return dateSend;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {

		return owner;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {

		this.content = content;
	}

	/**
	 * @param dateSend
	 *            the dateSend to set
	 */
	public void setDateSend(Date dateSend) {

		this.dateSend = dateSend;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {

		this.owner = owner;
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() */
	@Override
	public String toString() {

		return "Message [owner=" + owner + ", content=" + content + ", dateSend=" + dateSend + "]";
	}

}
