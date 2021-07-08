package space.androma.auction.communication.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import space.androma.auction.communication.api.utils.IEmailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailSender implements IEmailSender {

        private static final String ADMIN_EMAIL_ADDRESS = "andromanko@gmail.com";

/*        @Autowired
        private VelocityEngine velocityEngine;*/

        @Autowired
        private JavaMailSender mailSender;

        /*@Async
        @Override
        public void sendEmailToAdmin(UserDto dto, int status) throws Exception {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String statusValue = (status == 1) ? "activate" : "deactivate";
            String text = prepareActivateRequestEmail(dto, statusValue);
            String subjext = new StringBuilder("EMAil sent automatically from WebScada by Roma for ").append(statusValue)
                    .append(" the User.").toString();
            configureMimeMessageHelper(helper, dto.getEmail(), ADMIN_EMAIL_ADDRESS, text, subjext);
            mailSender.send(message);
        }*/

        @Async
        @Override
        public void sendEmailToUser(String email, int status) throws Exception {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            String text = "this is notification from auction-communications";
            configureMimeMessageHelper(helper, ADMIN_EMAIL_ADDRESS, email, text,
                    "This is automatic-sent letter from auction/communication system by Roma");
            mailSender.send(message);

            //TODO executor service!!!

        }

        private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
                                                String mailSubject) throws MessagingException {
            helper.setFrom(mailFrom);
            helper.setTo(mailTo);
            helper.setText(mailText, true);
            helper.setSubject(mailSubject);
        }

/*        private String prepareStatusChangingMailText(String text, int status) {
            String statusValue = (status == 1) ? "activated" : "deactivated";
            VelocityContext context = createVelocityContextWithBasicParameters(text, statusValue);
            StringWriter stringWriter = new StringWriter();
            velocityEngine.mergeTemplate("mailtemplates/statusChange.vm", "UTF-8", context, stringWriter);
            return stringWriter.toString();
        }*/

/*        private String prepareActivateRequestEmail(UserDto userDto, String statusValue) {
            VelocityContext context = createVelocityContextWithBasicParameters(userDto, statusValue);
            StringWriter stringWriter = new StringWriter();
            velocityEngine.mergeTemplate("mailtemplates/activate.vm", "UTF-8", context, stringWriter);
            return stringWriter.toString();
        }*/

/*        private VelocityContext createVelocityContextWithBasicParameters(UserDto userDto, String status) {
            VelocityContext context = new VelocityContext();
            context.put("userName", userDto.getLogin());
            context.put("status", status);
            return context;
        }*/
    }
