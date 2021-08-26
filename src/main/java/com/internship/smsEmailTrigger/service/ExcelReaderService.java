package com.internship.smsEmailTrigger.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.internship.smsEmailTrigger.model.Sms;
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

@Service
@RequiredArgsConstructor
public class ExcelReaderService {
    @Autowired
    private final ISmsRepository smsRepository;
    public void ReadExcel(MultipartFile file, String type) throws IOException, InvalidFormatException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .build();
        List<Sms> smsList = Poiji.fromExcel(convert(file), Sms.class,options);
        List<Long> idList = new ArrayList<Long>();
        System.out.println(smsList);
        for (Sms u: smsList
        ) {
            sendSMSWithNOC(u.getId());
            //idList.add(u.getId());

//            if(type=="SMS"){
//                sendSMSWithNOC(u.getId());
//            }
//            else if (type=="Email"){
//                sendEmailWithNOC(u.getId());
//            }
        }
        smsRepository.saveAll(smsList);
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
                = restTemplate.exchange(smsUrl, HttpMethod.GET, getHeader(),String.class);
        return response;
    }
    public ResponseEntity<String> sendEmailWithNOC(Long id){
        RestTemplate restTemplate = new RestTemplate();
        String smsUrl
                = "https://bildirimdev.anadolusigorta.com.tr/noc/restful/message/user/resendmessage/email/" + id;
        ResponseEntity<String> response
                = restTemplate.exchange(smsUrl, HttpMethod.GET, getHeader(),String.class);
        return response;
    }

    public HttpEntity<String> getHeader() {
        HttpHeaders header = new HttpHeaders();
        header.add("Cookie","_ga=GA1.3.1446988686.1629199566; _ga_CDVH4VH813=GS1.1.1629199564.1.0.1629199567.0; per=!w17EsPFwv7Qk/SwlKjHYmNxVRpzUaY73gEEkiPqXooYjE+x4fG/sWoST3TpqUlabWnzcmXZUXVVJMg==");
        return new HttpEntity<String>(header);
    }
    // EMAIL TARAFI KODLANACAK // TYPE DÖNDÜRÜLECEK // COOKIE SORULACAK
}
