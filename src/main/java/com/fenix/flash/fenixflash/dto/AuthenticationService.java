package com.fenix.flash.fenixflash.dto;

import com.fenix.flash.fenixflash.model.Teacher;
import com.fenix.flash.fenixflash.service.FileUploadService;
import com.fenix.flash.fenixflash.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final FileUploadService fileService;
    private final TeacherRegistration.Mapper teacherMapper;
    private final TeacherService teacherService;

    public AuthenticationService(FileUploadService fileService, TeacherRegistration.Mapper teacherMapper, TeacherService teacherService) {
        this.fileService = fileService;
        this.teacherMapper = teacherMapper;
        this.teacherService = teacherService;
    }

    public void registerTeacher(TeacherRegistration request) {
        String cvFilename = fileService.store(request.cvFile(), "teacher");
        String idFilename = fileService.store(request.idFile(), "teacher");
        String certificateFilename = fileService.store(request.certificateFiles(), "teacher");
        String videoFilename = fileService.store(request.videoFile(), "teacher");
        Teacher teacher = teacherMapper.apply(request);
        teacher.setCvFile(cvFilename);
        teacher.setIdFile(idFilename);
        teacher.setVideoFile(videoFilename);
        teacherService.upsert(teacher);
    }

}
