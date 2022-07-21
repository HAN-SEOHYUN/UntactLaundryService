package com.ez.launer.user.model;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DmailSender {
	private final JavaMailSender mailSender;
	
	public void sendDmail(DriverAllVO dvo, String randomPwd) throws MessagingException {
		
		String receiver=dvo.getEmail();
		String subject="Launer에 요청하신 "+dvo.getName()+" 기사님의 임시비밀번호 입니다.";
		String content = "안녕하세요 "+dvo.getName()+" 기사님!\n\n"
						+"비밀번호찾기 요청에 따라 임시비밀번호를 발급해 드립니다.\n"
						+"임시비밀번호는  "+randomPwd+"  입니다.\n"
						+"임시비밀번호로 로그인하시고 마이페이지에서 비밀번호 수정 부탁드립니다.\n\n"
						+"--------------------------------------------------------------------\n"
						+"항상 열심히 노력해 주셔서 감사합니다! 오늘도 화이팅!\n"
						+ "(주)런드리어벤져스";
		String sender = "testlauner@naver.com"; 
		
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			msg.setSubject(subject);
			msg.setText(content);
			msg.setRecipient(RecipientType.TO, new InternetAddress(receiver));
			msg.setFrom(new InternetAddress(sender));
			mailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("메일발송 실패 : " + e);
		}
	}

}
