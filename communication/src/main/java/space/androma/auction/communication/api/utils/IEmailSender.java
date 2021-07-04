package space.androma.auction.communication.api.utils;

import org.springframework.stereotype.Service;

@Service
public interface IEmailSender {

	void sendEmailToUser(String email, int status) throws Exception;
}
