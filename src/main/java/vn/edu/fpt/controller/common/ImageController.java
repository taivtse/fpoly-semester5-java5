package vn.edu.fpt.controller.common;

import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.constant.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/resource/**")
public class ImageController {
    private final String prefixPath = "/resource/";

    @GetMapping
    @ResponseBody
    public ResponseEntity displayImage(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        String fileName = requestURI.substring(prefixPath.length());
        Path filePath = Paths.get(SystemConstant.BASE_UPLOAD_PATH, fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();

            return ResponseEntity.ok(response);
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
    }
}
