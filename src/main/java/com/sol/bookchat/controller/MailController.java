package com.sol.bookchat.controller;

import com.sol.bookchat.dto.MailDto;
import com.sol.bookchat.dto.Response;
import com.sol.bookchat.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

private final MailService mailService;
    @PostMapping
    public ResponseEntity<MailDto> sendEmail(@RequestBody @Valid MailDto mailDto){
        try {
        mailService.sendSimpleMail(mailDto);
        return ResponseEntity.ok(mailDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.status(500).body(mailDto);
    }
}
