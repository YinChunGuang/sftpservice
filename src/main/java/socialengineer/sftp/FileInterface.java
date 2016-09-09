package com.accenture.socialengineer.sftp;

import java.io.IOException;

/**
 * Created by chunguang.yin on 9/8/2016.
 */
 interface FileInterface {

    public void download(String source,String dest)throws IOException;


    public void upload(String source,String dest)throws IOException;




}
