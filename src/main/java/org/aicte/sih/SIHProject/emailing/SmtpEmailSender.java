package org.aicte.sih.SIHProject.emailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import javax.mail.MessagingException;

@Service
public class SmtpEmailSender {

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private MailSender mailSender;

    private Boolean sendEmail(MessageEmail email) throws MessagingException {
        MimeMessage msg = ((JavaMailSender) mailSender).createMimeMessage();
        MimeMessageHelper helper = (CollectionUtils.isEmpty(email.getAttachments())
                && CollectionUtils.isEmpty(email.getInlineContents()))
                ? new MimeMessageHelper(msg)
                : new MimeMessageHelper(msg, true);
        helper.setFrom(from);
        helper.setSubject(email.getSubject() == null ? "" : email.getSubject());
        helper.setText(email.getBody(), email.isHtml());
        //Adding to,cc and bcc

        if (!CollectionUtils.isEmpty(email.getTo())) {
            helper.setTo(email.getTo().toArray(new String[]{}));
        }
        if (!CollectionUtils.isEmpty(email.getCc())) {
            helper.setCc(email.getCc().toArray(new String[]{}));
        }
        if (!CollectionUtils.isEmpty(email.getBcc())) {
            helper.setBcc(email.getBcc().toArray(new String[]{}));
        }

        //Adding attachments if exist
        if (!CollectionUtils.isEmpty(email.getAttachments())) {
            String s = "Attachment files: ";
            for (File file : email.getAttachments()) {
                s = s.concat(file.getName() + ", ");
                helper.addAttachment(file.getName(), file);
            }
        }

        //Adding inLine Content
        if (!CollectionUtils.isEmpty(email.getInlineContents())) {
            String s = "Inline Images: ";
            for (InlineContent inlineContent : email.getInlineContents()) {
                s = s.concat(inlineContent.getCid() + ": " + inlineContent.getFile().getName() + ", ");
                helper.addInline(inlineContent.getCid(), inlineContent.getFile());
            }
        }
        ((JavaMailSender) mailSender).send(msg);
        return true;
    }

    @Async
    public Boolean sendMessage(MessageEmail message) {
        try {
            sendEmail(message);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException("Error sending email, "+ ex.getLocalizedMessage());
        }
    }
}
