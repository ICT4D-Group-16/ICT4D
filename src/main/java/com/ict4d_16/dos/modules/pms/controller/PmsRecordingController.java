package com.ict4d_16.dos.modules.pms.controller;

import com.ict4d_16.dos.common.api.CommonResult;
import com.ict4d_16.dos.common.service.AmazonS3Service;
import com.ict4d_16.dos.modules.pms.model.PmsAudio;
import com.ict4d_16.dos.modules.pms.model.PmsRecording;
import com.ict4d_16.dos.modules.pms.service.PmsRecordingService;
import com.ict4d_16.dos.modules.ums.model.UmsAdmin;
import com.ict4d_16.dos.modules.ums.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Recording Management
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "PmsRecordingController")
@Tag(name = "PmsRecordingController", description = "Recording Management")
@RequestMapping("/pms/recording")
public class PmsRecordingController {

    @Autowired
    private PmsRecordingService recordingService;
    @Autowired
    private AmazonS3Service amazonS3Service;

    @ApiOperation("Form API. User leave a recording.")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PmsRecording> create(@RequestParam("phone") String phone,
                            @RequestParam("file") MultipartFile file,
                            HttpServletRequest request) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return CommonResult.failed("Recording file is empty!");
        }
        String filePath;
        try {
            filePath = amazonS3Service.putObject(file);
            PmsRecording recording = new PmsRecording();
            recording.setPhone(phone);
            recording.setUrl(filePath);
            recording.setCreateTime(new Date());
            recording.setStatus(0);
            boolean success = recordingService.save(recording);
            if (success) {
                return CommonResult.success(recording, "Recording upload successfully!");
            } else {
                return CommonResult.failed("Recording upload failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("Recording upload failed!");
        }
    }

    @ApiOperation("Get all recordings.")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsRecording>> list() {
        List<PmsRecording> recordingList = recordingService.list();
        return CommonResult.success(recordingList);
    }
}
