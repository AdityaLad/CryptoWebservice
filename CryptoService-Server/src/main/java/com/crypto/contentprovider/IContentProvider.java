package com.crypto.contentprovider;

import org.springframework.stereotype.Service;

import com.crypto.dto.ResponseDTO;
import com.crypto.pojo.Request;

@Service
public interface IContentProvider {

    ResponseDTO doEncrypt(Request message);

    ResponseDTO doDecrypt(Request message);

}
