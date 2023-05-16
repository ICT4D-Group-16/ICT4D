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

    @ApiOperation("Form API. Add one translate audio for a product.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsAudio> create(@RequestParam("productId") Long productId,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam("language") String language,
                                         HttpServletRequest request) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return CommonResult.failed("Recording file is empty!");
        }
        String filePath;
        try {
            filePath = amazonS3Service.putObject(file);
            PmsAudio audio = pmsAudioService.create(productId, language, filePath);
            if (audio != null) {
                return CommonResult.success(audio, "Audio upload successfully!");
            } else {
                return CommonResult.failed("Audio upload failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("Audio upload failed!");
        }
    }

}

