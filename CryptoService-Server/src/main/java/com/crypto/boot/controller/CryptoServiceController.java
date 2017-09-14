package com.crypto.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.contentprovider.IContentProvider;
import com.crypto.dto.ResponseDTO;
import com.crypto.pojo.Request;
import com.crypto.pojo.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableAsync
@Slf4j
public class CryptoServiceController {

    @Autowired
    IContentProvider provider;

    @RequestMapping(path = "/encrypt", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public Response encryptMessage(@RequestBody Request jsonRequest) {
        ResponseDTO dto = provider.doEncrypt(jsonRequest);
        if (dto.hasError()) {
            return new Response(null, null, dto.getErrorResponse());
        }
        log.info("Message encrypted - " + jsonRequest.getId());
        return dto.getResponse();
    }

    @RequestMapping(path = "/decrypt", method = RequestMethod.POST, consumes = "application/json",
            produces = "application/json")
    public Response decryptMessage(@RequestBody Request jsonRequest) {
        ResponseDTO dto = provider.doDecrypt(jsonRequest);
        if (dto.hasError()) {
            return new Response(null, null, dto.getErrorResponse());
        }
        log.info("Message decrypted - " + jsonRequest.getId());
        return dto.getResponse();
    }

}