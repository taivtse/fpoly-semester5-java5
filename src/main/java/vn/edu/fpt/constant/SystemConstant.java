package vn.edu.fpt.constant;

import java.io.File;

public class SystemConstant {
    public static final String BASE_UPLOAD_PATH = "/Users/vothanhtai/Documents/WorkSpace/Java/FPT-Polytechnic/Java5-SpringMVC-Assignment/resource".replace("/", File.separator);
    public static final String STAFF_UPLOAD_PATH = "staff";

    public static final String REDIRECT_URL = "redirect:/";
    public static final String COMMAND = "command";
    public static final String SORT_ASC = "1";
    public static final String SORT_DESC = "2";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String PNOTIFY = "pNotify";
    public static final Integer PASSWORD_GENERATE_LENGTH = 10;

    public static final String SESSION_USER = "session_user";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
}
