package com.internship.smsEmailTrigger.controller;

import com.internship.smsEmailTrigger.model.Sms;
import com.internship.smsEmailTrigger.service.ExcelReaderService;
import com.internship.smsEmailTrigger.service.SmsService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ExcelReaderController {
    private static String UPLOADED_FOLDER = "C://test//";

    @Autowired
    private final ExcelReaderService excelReaderService;
    @Autowired
    private final SmsService smsService;

    public ExcelReaderController(ExcelReaderService excelReaderService, SmsService smsService) {
        this.excelReaderService = excelReaderService;
        this.smsService = smsService;
    }


    @GetMapping("/upload")
    public String index() {
        return "form";
    }
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model) throws IOException, InvalidFormatException {


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            //model.addAttribute("type", sms);
            excelReaderService.ReadExcel(file);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/status";
    }
    @GetMapping("/status")
    public String uploadStatus() {
        return "status";
    }

}
