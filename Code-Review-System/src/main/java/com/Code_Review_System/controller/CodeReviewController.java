package com.Code_Review_System.controller;


import com.Code_Review_System.Service.RequestService;
import com.Code_Review_System.iO.AIRequest;
import com.Code_Review_System.iO.AIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
@CrossOrigin("http://localhost:5173/")
public class CodeReviewController {

    @Autowired
    private RequestService service;



    @PostMapping("/code")
    public String giveRequest(@RequestBody AIRequest request){

        String forword = service.forword(request);
        return  forword;
    }



}

