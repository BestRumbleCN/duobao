package team.wuxie.crowdfunding.controller.base;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import team.wuxie.crowdfunding.domain.CurrentUser;
import team.wuxie.crowdfunding.exception.FileUploadException;
import team.wuxie.crowdfunding.util.qiniu.QiniuSimpleUpload;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 基本的Controller
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 17:03
 */
@SuppressWarnings("unused")
public class BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * 单个文件上传的最大容量：5000 * 1024byte = 200KB
     */
    private static final long MAX_UPLOAD_SIZE = 1024 * 5000;
    /**
     * 文件上传路径
     */
    private static final String UPLOAD_DIR_PATH = "c:\\upload\\";
    //public static final String UPLOAD_DIR_PATH = "/data/upload";
    /**
     * 文件上传日期格式
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmm";

    /** 默认的 404 视图。 */
    protected static final String DEFAULT_PAGE_NOT_FOUND_VIEW = "/404";

    @Resource
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;
    @Resource
    public HttpSession session;

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public CurrentUser getCurrentUser() {
        return (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 重定向至地址 url
     *
     * @param url 请求地址
     * @return
     */
    protected String redirectTo(String url) {
        return String.format("redirect:%s", url);
    }

    protected String redirect404() {
        return redirectTo(DEFAULT_PAGE_NOT_FOUND_VIEW);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(true);
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 检查上传文件的大小、文件扩展名是否正确
     *
     * @param file 上传的文件
     * @return
     * @throws FileUploadException
     */
    private boolean isCorrectFormat(MultipartFile file) throws FileUploadException {
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            if (file.getSize() > MAX_UPLOAD_SIZE)
                throw new FileUploadException("upload.file_size_max_limit");
            String fileType = file.getContentType();
            if (!fileType.contains("png")
                    && !fileType.contains("gif")
                    && !fileType.contains("bmp")
                    && !fileType.contains("jpeg")
                    && !fileType.contains("jpg"))
                throw new FileUploadException("upload.file_type_not_support");
            return true;
        } else {
            LOGGER.error("******************You failed to upload" + fileName + ", because the file was empty.******************");
            throw new FileUploadException("upload.multipart_file_not_found");
        }
    }

    /**
     * 上传单个文件
     *
     * @param file 上传的文件
     * @return
     * @throws FileUploadException
     */
    @SuppressWarnings("ResultOfMethodCallIgnored unused")
    protected String uploadFileHandler(MultipartFile file) throws FileUploadException {
        if (file == null) throw new FileUploadException("upload.multipart_file_not_found");
        String fileName = DateFormatUtils.format(new Date(), DATE_FORMAT) + file.getOriginalFilename();
        if (!file.isEmpty() && isCorrectFormat(file)) {
            try {
                String path = QiniuSimpleUpload.upload(file.getBytes(), fileName);
                LOGGER.info("******************You successfully uploaded file=" + fileName + "******************");
                return path;
            } catch (IOException e) {
                LOGGER.error("******************You failed to upload" + fileName + ", because the file was empty.******************");
                throw new FileUploadException("upload.failure");
            }
        } else {
            LOGGER.error("******************You failed to upload" + fileName + ", because the file was empty.******************");
            throw new FileUploadException("upload.multipart_file_not_found");
        }
    }

    /**
     * 上传多个文件
     *
     * @param files 上传的文件数组
     * @return
     * @throws FileUploadException
     */
    @SuppressWarnings("ResultOfMethodCallIgnored unused")
    protected String uploadMultipleFileHandler(MultipartFile[] files) throws FileUploadException {
        String filePaths = "";
        for (MultipartFile file : files) {
            String serverFile = uploadFileHandler(file);
            if (!filePaths.isEmpty()) filePaths = filePaths + ",";
            filePaths = filePaths + serverFile;
        }
        return filePaths;
    }
}
