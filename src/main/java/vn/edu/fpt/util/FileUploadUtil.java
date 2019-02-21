package vn.edu.fpt.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.fpt.constant.SystemConstant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileUploadUtil {
    private static FileUploadUtil fileUploadUtil;
    private Set<String> fileExtensionSet;

    public FileUploadUtil() {
        this.fileExtensionSet = new HashSet<>();
        this.setUploadFileIsImage();
    }

    public static FileUploadUtil getInstance() {
        if (fileUploadUtil == null) {
            fileUploadUtil = new FileUploadUtil();
        }
        return fileUploadUtil;
    }

    public FileUploadUtil setUploadFileIsImage() {
        fileExtensionSet.clear();
        fileExtensionSet.add("png");
        fileExtensionSet.add("jpg");
        fileExtensionSet.add("jpeg");
        fileExtensionSet.add("gif");
        return this;
    }

    public FileUploadUtil setUploadFileIsExcel() {
        fileExtensionSet.clear();
        fileExtensionSet.add("xls");
        fileExtensionSet.add("xlsx");
        return this;
    }

    public FileUploadUtil addUploadFileExtension(String extension) {
        fileExtensionSet.add(extension);
        return this;
    }

    public String write(MultipartFile multipartFile, String parentFolder, String savedName) throws IOException {
        String fileName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if (savedName == null) {
//        Convert to ascii string
            fileName = StringUtil.covertUnicodeToASCIIString(fileName);

//        remove all special characters and whitespace
            fileName = fileName.replaceAll("([^A-Za-z0-9]|\\s)", "");
            fileName = fileName.concat(".").concat(fileExtension);
        }else{
            fileName = savedName.concat(".").concat(fileExtension);
        }

        if (fileExtensionSet.contains(fileExtension)) {
            Path uploadParentPath = Paths.get(SystemConstant.BASE_UPLOAD_PATH, parentFolder);
            Path uploadFilePath = Paths.get(SystemConstant.BASE_UPLOAD_PATH, parentFolder, fileName);

//        check and create folder if not exist
            this.createFolderIfNotExisted(uploadParentPath);

            multipartFile.transferTo(uploadFilePath.toFile());
        } else {
            throw new UnsupportedOperationException();
        }

        return Paths.get(parentFolder, fileName).toString();
    }

    private void createFolderIfNotExisted(Path path) {
        File folder = path.toFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
