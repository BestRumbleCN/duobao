package team.wuxie.crowdfunding.util.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * <p>
 * 覆盖上传
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 12:05
 */
public class QiniuOverrideUpload {

    //密钥配置
    private Auth auth = Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.SECRET_KEY);
    //创建上传对象
    private UploadManager uploadManager = new UploadManager();

    //覆盖上传
    private String getUpToken(String fileName) {
        //<bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        return auth.uploadToken(QiniuConfig.BUCKET_NAME, fileName);
    }

    /**
     * 覆盖上传
     *
     * @param filePath 上传文件的路径
     * @param fileName 上传到七牛后保存的文件名
     * @return
     * @throws IOException
     */
    public String upload(String filePath, String fileName) throws IOException {
        try {
            Response response = uploadManager.put(filePath, fileName, getUpToken(fileName));
            return response.bodyString();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }
}
