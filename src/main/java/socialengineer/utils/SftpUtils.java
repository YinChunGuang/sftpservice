package  socialengineer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import  java.lang.Iterable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

  

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
 

public class SftpUtils {

	public SftpUtils(){
	}
	public SftpUtils(String host,int port,String username,String password){
		this.username = username;
		this.host = host;
		this.port = port;
		this.password = password;
	}


	public static Session session = null;
	public static ChannelSftp sftpChannel = null;

	public static String host = null;
	public static int port ;
	public static String username = null;
	public static String password = null;


	// get channel
	public  ChannelSftp getSftpChannel() {
		try {

			JSch jsch = new JSch();
			session = jsch.getSession(username, host,  port);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config); 
			session.setTimeout(60000); 
			session.connect();
			Channel channel = session.openChannel("sftp");  
			channel.connect();  
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.setFilenameEncoding("UTF-8");

			return sftpChannel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public  boolean downloadFile(String source, String destDir) throws IOException{
		ChannelSftp sftpChannel2 = getSftpChannel();
		InputStream inputStream = null;
		 try {
			 inputStream = sftpChannel2.get(source);
			 FileOutputStream fileOutputStream = new FileOutputStream("src/b.txt");
			 byte[] buff = new byte[2048*256];
	 
			 int len = 0;
			 while((len = inputStream.read(buff)) !=-1){
				 fileOutputStream.write(buff, 0, len);
			 }
			 fileOutputStream.close();
			 close();
		} catch (SftpException e) {  
			e.printStackTrace();
		}
		
		
		return false;
	} 

	public boolean isDir(String dir){

		try {
		 return getSftpChannel().ls(dir).size()!=1;
		}catch (SftpException e){
			e.printStackTrace();
		}
		return  false;
	}
	public List<String> getFileList(String dir){
        List<String> list = null;
		try {
            dir = getFilePath(dir);
			Vector<ChannelSftp.LsEntry> lsEntries =  getSftpChannel().ls(dir) ;
			java.util.Iterator<ChannelSftp.LsEntry> iterable = lsEntries.iterator();
			while(iterable.hasNext()){
                ChannelSftp.LsEntry entry = iterable.next();
                if (!entry.getAttrs().isDir())
                list.add(dir + File.separator+ entry.getFilename());
			}

		}catch (SftpException e){
			e.printStackTrace();
		}
		return  list;
	}

	public  boolean uploadFile(InputStream inputStream, String fileName ) {
		try {
			sftpChannel = getSftpChannel();
			 
			String dist =   fileName;
			dist = getFilePath(dist);
			sftpChannel.put(inputStream, dist);

			close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
   
	public  void close() {
		if (sftpChannel != null) {
			sftpChannel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}

	public  String getFilePath(String filePath) {
		filePath = filePath.replaceAll("//", "/");
		filePath = filePath.replaceAll("/./", "/");
		return filePath;
	}


 
	
 
}
