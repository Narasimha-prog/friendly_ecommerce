package com.lnreddy.friendlyecommerce.user.domain.port.out;

public interface IMailService {
    void sendMail(String to, String subject, String body);
}
