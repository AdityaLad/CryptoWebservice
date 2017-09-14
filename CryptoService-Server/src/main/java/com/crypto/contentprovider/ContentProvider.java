package com.crypto.contentprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.dto.ResponseDTO;
import com.crypto.pojo.Request;
import com.crypto.service.AESEncryptionHelper;

@Service
public class ContentProvider implements IContentProvider {

    @Autowired
    AESEncryptionHelper symmetricEncryptionHelper;

    /* (non-Javadoc)
     * @see com.crypto.contentprovider.IContentProvider#doEncrypt(com.crypto.pojo.Message)
     */
    @Override
    public ResponseDTO doEncrypt(Request message) {
        ResponseDTO response = (ResponseDTO) symmetricEncryptionHelper.encrypt(message.getContent());
        return response;
    }

    /* (non-Javadoc)
     * @see com.crypto.contentprovider.IContentProvider#doDecrypt(com.crypto.pojo.Message)
     */
    @Override
    public ResponseDTO doDecrypt(Request message) {
        ResponseDTO response = (ResponseDTO) symmetricEncryptionHelper.decrypt(message.getContent());
        return response;
    }
}
