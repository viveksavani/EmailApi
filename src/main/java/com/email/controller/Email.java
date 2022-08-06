package com.email.controller;

import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Email {

    @Autowired
    EmailService emailService;

    @GetMapping("/test")
    public String test(){

        return "this is test api";
    }

    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email){

        System.out.println(email);
       boolean result =  emailService.sendEmail(email.getSubject(),email.getMessage(),email.getTo());

       if(result){
           return ResponseEntity.ok("Done....");
       }else{

           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
       }


    }

}
