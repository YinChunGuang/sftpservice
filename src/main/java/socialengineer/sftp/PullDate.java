package  socialengineer.sftp;

import socialengineer.utils.SftpUtils;

/**
 * Created by chunguang.yin on 9/8/2016.
 */
public class PullDate  {



    public static  void main (String[] args) throws  Exception{


        System.out.println("hello ftp");


        SftpUtils utils =  new SftpUtils("192.168.44.128",22,"root","1");
        utils.downloadFile("/root/data","C:\\testdata");




    }


}
