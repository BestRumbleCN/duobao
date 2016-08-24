package team.wuxie.crowdfunding.util.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

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

    //密钥配置
    private Auth auth = Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.SECRET_KEY);
    //创建上传对象
    private UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken() {
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
            Response response = uploadManager.put(filePath, fileName, getUpToken());
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
