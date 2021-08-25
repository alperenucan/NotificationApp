package com.internship.smsEmailTrigger.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.repository.ISmsRepository;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void ReadExcel(MultipartFile file) throws IOException, InvalidFormatException {
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings()
                .build();
        List<Sms> smsList = Poiji.fromExcel(convert(file), Sms.class,options);
        List<Long> idList = new ArrayList<Long>();
        System.out.println(smsList);
        for (Sms u: smsList
        ) {
            idList.add(u.getId());
        }
        smsRepository.saveAll(smsList);
        //return idList;
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
