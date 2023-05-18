package com.ict4d_16.dos.modules.pms.controller;


import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.common.service.AmazonS3Service;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.service.PmsAudioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  Product Management System. Translation Audio controller
 * </p>
 *
 * @author par
 * @since 2023-05-15
 */
@RestController
@RequestMapping("/pms/audio")
public class PmsAudioController {
    @Autowired
    private PmsAudioService pmsAudioService;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @ApiOperation("Form API. Add one name translate audio for a product.")
    @RequestMapping(value = "/createName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsAudio> createName(@RequestParam("productId") Long productId,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("language") String language) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return CommonResult.failed("Recording file is empty!");
        }
        String filePath;
        try {
            filePath = amazonS3Service.putObject(file);
            PmsAudio audio = new PmsAudio();
            audio.setProductId(productId);
            audio.setLanguage(language);
            audio.setCategory(0);
            audio.setUrl(filePath);
            audio = pmsAudioService.create(audio);
            if (audio == null) {
                return CommonResult.failed("Name translation audio create failed!");
            }
            return CommonResult.success(audio, "Name translation audio create successfully!");
        } catch (Exception e) {
            return CommonResult.failed("Name translation audio create failed! " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @ApiOperation("Form API. Add one description translate audio for a product.")
    @RequestMapping(value = "/createDescription", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsAudio> createDescription(@RequestParam("productId") Long productId,
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam("language") String language) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return CommonResult.failed("Recording file is empty!");
        }
        String filePath;
        try {
            filePath = amazonS3Service.putObject(file);
            PmsAudio audio = new PmsAudio();
            audio.setProductId(productId);
            audio.setLanguage(language);
            audio.setCategory(1);
            audio.setUrl(filePath);
            audio = pmsAudioService.create(audio);
            if (audio == null) {
                return CommonResult.failed("Description translation audio create failed!");
            }
            return CommonResult.success(audio, "Description translation audio create successfully!");
        } catch (Exception e) {
            return CommonResult.failed("Description translation audio create failed! " + e.getMessage());
        }
    }
}

