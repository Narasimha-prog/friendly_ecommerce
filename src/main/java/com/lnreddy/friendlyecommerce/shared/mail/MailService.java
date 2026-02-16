package com.lnreddy.friendlyecommerce.shared.mail;

public interface MailService {
    void sendMail(String to, String subject, String body);
}
