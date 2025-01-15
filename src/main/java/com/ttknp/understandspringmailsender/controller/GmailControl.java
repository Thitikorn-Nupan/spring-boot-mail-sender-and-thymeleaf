package com.ttknp.understandspringmailsender.controller;

import com.ttknp.understandspringmailsender.entity.Information;
import com.ttknp.understandspringmailsender.service.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${main.prefix.controller}")
public class GmailControl {

    private final GmailService gmailService;

    @Autowired
    public GmailControl(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @PostMapping(value = "/send/v1")
    private ResponseEntity<Boolean> sendGmailHTMLString(@RequestBody Information information) {
        return ResponseEntity
                .status(202)
                .body(gmailService.sendEmailContentAsHTMLString(information.subject,information.receiptEmail,information.content));
    }

    @PostMapping(value = "/send/v2")
    private ResponseEntity<Boolean> sendGmailHTMLFile(@RequestBody Information information) {
        return ResponseEntity
                .status(202)
                .body(gmailService.sendEmailContentAsHTMLFile(information.subject,information.receiptEmail,information.p0,information.p1,information.p2,information.p3,information.p4));
    }

}
