package com.internship.smsEmailTrigger.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.internship.smsEmailTrigger.model.Email;
import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.model.Type;
import com.internship.smsEmailTrigger.repository.EmailRepository;
import com.internship.smsEmailTrigger.repository.ISmsRepository;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExcelReaderService {
    @Autowired
    private final ISmsRepository smsRepository;
    @Autowired
    private final EmailRepository emailRepository;

    public void ReadExcel(MultipartFile file, Type type) throws IOException, InvalidFormatException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .build();
        if (Objects.equals(type.getType(), "SMS")) {
            List<Sms> smsList = Poiji.fromExcel(convert(file), Sms.class, options);
            for (Sms u : smsList
            ) {
                System.out.println("sms atildi");
                smsRepository.saveAll(smsList);
                sendSMSWithNOC(u.getId());
            }
        } else if (Objects.equals(type.getType(), "Email")) {
            List<Email> emailList = Poiji.fromExcel(convert(file), Email.class, options);
            for (Email u : emailList
            ) {
                System.out.println("email atildi");
                emailRepository.saveAll(emailList);
                sendEmailWithNOC(u.getId());
            }
        }
    }


    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public ResponseEntity<String> sendSMSWithNOC(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String smsUrl
                = "https://bildirimdev.anadolusigorta.com.tr/noc/restful/message/user/resendmessage/sms/" + id;
        ResponseEntity<String> response
                = restTemplate.exchange(smsUrl, HttpMethod.GET, getHeader(), String.class);
        return response;
    }

    public ResponseEntity<String> sendEmailWithNOC(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String smsUrl
                = "https://bildirimdev.anadolusigorta.com.tr/noc/restful/message/user/resendmessage/email/" + id;
        ResponseEntity<String> response
                = restTemplate.exchange(smsUrl, HttpMethod.GET, getHeader(), String.class);
        return response;
    }

    public HttpEntity<String> getHeader() {
        HttpHeaders header = new HttpHeaders();
        header.add("Cookie", "JSESSIONID=0739AA1F265DC3E9F25FEA62184ED206; _ga_CDVH4VH813=GS1.1.1629273790.3.1.1629275405.0; _ga=GA1.1.786201284.1627889555; per=!nzuQdaGuU387aVolKjHYmNxVRpzUacFF9rX8nDDUhhn5OHIIpF2cidUIY8q1VDLbf5jrWYK/cpU69g==");
        return new HttpEntity<String>(header);
    }
    // EMAIL TARAFI KODLANACAK // TYPE DÖNDÜRÜLECEK // COOKIE SORULACAK
}
