package org.aicte.sih.SIHProject.emailing;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageEmail {
    private String subject;
    private String body;
    private boolean isHtml;
    private List<String> to = new ArrayList<>();
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private List<File> attachments = new ArrayList<>();
    private List<InlineContent> inlineContents = new ArrayList<>();

    public MessageEmail() {

    }

    public MessageEmail(MessageEmail email) {
        to = email.to;
        cc = email.cc;
        bcc = email.bcc;
        subject = email.subject;
        body = email.body;
        isHtml = email.isHtml;
        attachments = email.attachments;
        inlineContents = email.inlineContents;
    }
}