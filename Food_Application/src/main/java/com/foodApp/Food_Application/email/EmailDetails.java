package com.foodApp.Food_Application.email;

import java.util.List;

import com.foodApp.Food_Application.dto.FoodOrder;
import com.foodApp.Food_Application.dto.Items;

public class EmailDetails {
	private String recipient;
	private String itemsMsg ;;
	private String msgBody;

	private String subject;
	private String attachment;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getItemsMsg() {
		return itemsMsg;
	}

	public void setItemsMsg(String itemsMsg) {
		this.itemsMsg = itemsMsg;
	}

	public String setEmail(List<Items> items, FoodOrder order) {
		String item = "Hello " + order.getCustomerName() + " ,\r\n"
				+ "Thank you for placing an order on Food App. "
				+ "We’re glad to inform you that we’ve received your order and will process it very soon.\r\n"
				+
				"Here are your Order Details Along with Food Product\r\n" + "\r\n";

		for (Items it : items) {
			item += "\r\n Food Name :" + it.getName() + ",\r\n" + "Food Price :" + it.getPrice() + ",\r\n"
					+ "Type of Food :" + it.getType() + ",\r\n" + "Details of your Food :" + it.getDescription()
					+ "\r\n";
		}
		item += "Your Order Status : " + order.getStatus() + "\r\n" + "Total Amount : " + order.getTotalAmount()
				+ "\r\n" + "for any support please Contact to given email:" + order.getStaff().getEmail() + "\r\n"
				+ "Thank you again for choosing Food App for your purchase.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "Team Food App";

		return item;
	}
	// premarkertemplate;
}
