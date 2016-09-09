package  socialengineer.sftp;

import  socialengineer.utils.PropertiesUtils;
import  socialengineer.utils.SftpUtils;
import com.jcraft.jsch.ChannelSftp;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by chunguang.yin on 9/8/2016.
 */
public class FileUtils implements FileInterface {
    public final static ResourceBundle rb = ResourceBundle.getBundle("check", Locale.getDefault());
    private SftpUtils utils = null;
    public final static String host = "sftp.host";
    public final static String port = "sftp.port" ;
    public final static String username = "sftp.username";
    public final static String password = "sftp.password";

    public FileUtils(){
        this.utils = new SftpUtils(PropertiesUtils.getStringValue(host),
                PropertiesUtils.getIntValue(port),PropertiesUtils.getStringValue(username),
                PropertiesUtils.getStringValue(password));
    }

    public void download(String source,String dest)throws IOException{
        if (!utils.isDir(dest)){
            throw new RuntimeException("dest is not a dir");
        }
        if (utils.isDir(source)){
            List<String> list =  utils.getFileList(source);
//            utils.downloadFile();

        }else{
            utils.downloadFile(source,dest);
        }

    }

    /**
     *
     * @param source
     * @param dest
     */

    public void upload(String source,String dest)throws IOException{

    }

}
