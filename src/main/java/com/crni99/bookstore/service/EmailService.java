package com.crni99.bookstore.service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//import com.crni99.bookstore.model.Book;

import com.crni99.bookstore.model.Book;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	 public void sendEmail(String toEmail, String subject, List<Book> books, BigDecimal total) {
        StringBuilder sb = new StringBuilder();
        sb.append("Thank you for your order!\n\n");
        sb.append("Here is your order summary:\n\n");

        for (Book book : books) {
            sb.append("- ").append(book.getName())
              .append(" by ").append(book.getAuthors())
              .append(" (Price: ₹").append(book.getPrice()).append(")\n");
        }

        sb.append("\nTotal: ₹").append(total).append("\n\n");
        sb.append("We appreciate your business!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(sb.toString());

        mailSender.send(message);
    }

}
