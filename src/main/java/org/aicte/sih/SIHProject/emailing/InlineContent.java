package org.aicte.sih.SIHProject.emailing;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class InlineContent {
    private String cid;
    private File file;

    public InlineContent(String cid, File file)
    {
        this.cid = cid;
        this.file = file;
    }
}
