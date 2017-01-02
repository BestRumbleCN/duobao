package team.wuxie.crowdfunding.util.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 * 简单上传
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 11:54
 */
public class QiniuSimpleUpload {

	private final static Logger LOGGER = LoggerFactory.getLogger(QiniuSimpleUpload.class);
    //密钥配置
    private static Auth auth = Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.SECRET_KEY);
    //创建上传对象
    private static UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken() {
        return auth.uploadToken(QiniuConfig.BUCKET_NAME);
    }

    /**
     * 简单上传
     *
     * @param filePath 上传文件的路径
     * @param fileName 上传到七牛后保存的文件名
     * @return
     * @throws IOException
     */
    public String upload(String filePath, String fileName) throws IOException {
        try {
            uploadManager.put(filePath, fileName, getUpToken());
            return QiniuConfig.BASE_URL + fileName;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            LOGGER.error(r.toString());
        }
        return null;
    }
    
    /**
     * 简单上传
     * @author fly
     * @param imgBytes 图片字节
     * @param fileName 上传到七牛后保存的文件名
     * @return  
     * @since
     */
    public static String upload(byte[] imgBytes, String fileName){
    	try {
            Response response = uploadManager.put(imgBytes, fileName, getUpToken());
            return fileName;
           // return QiniuConfig.BASE_URL + fileName;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            LOGGER.error(r.toString());
        }
        return null;
    }
}
