package io.github.pleuvoir.email;

import org.apache.commons.mail.EmailException;

public class SimpleQQEmailSenderTest {

	public static void main(String[] args) throws EmailException {
		EmailSender.sendTextToMe("主题", "你好 - -:)");
	}
}
