package com.faceproject.service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.net.ssl.SSLException;
import javax.print.attribute.standard.RequestingUserName;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

/*
 * 添加注册信息：faceAdd(id, filenameORbase64)
 * 第一个参数是改员工的ID，第二个参数是base64编码后的图片信息
 * 返回值为：Boolean，表示是否添加成功
 * 
 * 打卡信息：matchFace(filenameORbase64)
 * 该参数是base64编码后的图片信息
 * 返回值是该员工的ID，String类型，若匹配失败则为null
 * 
 */


public class FaceDealService {
	
	public static void main(String []args) throws Exception{
		FaceDealService fds = new FaceDealService();
		//fds.createFaceSet();
		//fds.faceAdd(1, "D:\\3.jpg");
		//fds.faceAdd(2, "D:\\4.jpg");
		fds.matchFace("D:\\5.jpg");
		fds.displayFaceset(outerid);
	}

	public String getID(String str) {
		String pattern = "(?<=\"user_id\": \")\\d*(?=\")";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(str);
		if(matcher.find()) {
			System.out.println(matcher.group(0));
			return matcher.group(0);
		}
		return null;
	}
	
	public String matchFace(String filenameORbase64) throws Exception{
		//File file = new File(filename);
		//byte[] buff = getBytesFromFile(file);
		String url = "https://api-cn.faceplusplus.com/facepp/v3/search";
		HashMap<String, String> map = new HashMap<>();
		//HashMap<String, byte[]> byteMap = new HashMap<>();
		map.put("api_key", apikey);
		map.put("api_secret", secret);
		map.put("outer_id", outerid);
		map.put("image_base64", filenameORbase64);
		//byteMap.put("image_file", buff);
		try {
			byte[] bacd = post(url, map, null);
			String str = new String(bacd);
			System.out.println(str);
			if(withoutError(str) != null) {
				return null;
			}
			return getID(str);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public boolean createFaceSet() throws Exception{
		String url = "https://api-cn.faceplusplus.com/facepp/v3/faceset/create";
		HashMap<String, String> map = new HashMap<>();
		map.put("api_key", apikey);
		map.put("api_secret", secret);
		map.put("outer_id", outerid);
		map.put("face_tokens","3db3721bfd229f1d465d5af467d98cf6");
		try {
			byte[] bacd = post(url, map, null);
            String str = new String(bacd);
            System.out.println(str);
            if(withoutError(str) == null) {
            	return true;
            }
            //System.out.println(str);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public void displayFaceset(String outid) {
		String url = "https://api-cn.faceplusplus.com/facepp/v3/faceset/getdetail";
		HashMap<String, String> map = new HashMap<>();
		map.put("api_key", apikey);
		map.put("api_secret", secret);
		map.put("outer_id", outid);
		try {
			byte[] bacd = post(url, map, null);
			String str = new String(bacd);
			System.out.println(str);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String withoutError(String msg) {
		String pattern = "(?<=\"error_message\": \").*(?=\")";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(msg);
		if(matcher.find()) {
			//System.out.println(matcher.group(0));
			System.out.println(matcher.group());
			return matcher.group();
		}
		return null;
	}
	public String dealResult(String json) {
		String pattern = "(?<=\"face_token\": \").*(?=\")";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher(json);
		if(matcher.find()) {
			System.out.println(matcher.group(0));
			return matcher.group(0);
		}
		return null;
	}
	
	public String getFaceToken(String filenameORbase64) throws Exception{
		//File file = new File(filename);
		//byte[] buff = getBytesFromFile(file);
		//当传入的是base64编码的图片时生效或直接使用此字符串
		//byte[] buff = filename.getBytes();
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        //HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", apikey);
        map.put("api_secret", secret);
		//map.put("return_landmark", "1");
        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        map.put("image_base64", filenameORbase64);
        //byteMap.put("image_file", buff);
        //当传入的是base64编码的图片时生效
        //或在map中增加“image_base64”参数并使post第三个值为空
        //byteMap.put("image_base64", buff);
        try{
            byte[] bacd = post(url, map, null);
            String str = new String(bacd);
            if(withoutError(str) != null) {
            	return null;
            }
            System.out.println("getFaceToken::str: " + str);
            String result = dealResult(str);
            System.out.println("result: " + result);
            if(result != null) {
            	return result;
            }
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return null;
	}
	
	public boolean setUserid(Integer userid, String token) {
		String url = "https://api-cn.faceplusplus.com/facepp/v3/face/setuserid";
		HashMap<String, String> map = new HashMap<>();
		map.put("api_key", apikey);
		map.put("api_secret", secret);
		map.put("face_token", token);
		map.put("user_id", String.valueOf(userid));
		try {
			byte[] bacd = post(url, map, null);
			String str = new String(bacd);
			System.out.println(str);
			if(withoutError(str) == null) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean faceAdd(Integer userid, String filenameORbase64) throws Exception{
		
        String token = getFaceToken(filenameORbase64);
        if(token == null) {
        	return false;
        }
        if(!setUserid(userid, token)) {
        	return false;
        }
		String url = "https://api-cn.faceplusplus.com/facepp/v3/faceset/addface";
        HashMap<String, String> map = new HashMap<>();
        //HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", apikey);
        map.put("api_secret", secret);
        map.put("outer_id", outerid);
        map.put("face_tokens", token);
		//map.put("return_landmark", "1");
        //map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        //byteMap.put("image_file", buff);
        try{
            byte[] bacd = post(url, map, null);
            String str = new String(bacd);
            System.out.println(str);
            if(withoutError(str) != null) {
            	return false;
            }
            return true;
        }catch (Exception e) {
        	e.printStackTrace();
		}
        return false;
	}
	
	private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private final static String apikey= "L75l-nZs78PODI-Y5ZOI5lbAdouhlgwK";
    private final static String secret= "xnX7bmLfivIQ4SSXRdhaGfeuFCoqWDjB";
    private final static String outerid = "myfaceset";
    private static String boundaryString = getBoundary();
    protected static byte[] post(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if(fileMap != null && fileMap.size() > 0){
            Iterator fileIter = fileMap.entrySet().iterator();
            while(fileIter.hasNext()){
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try{
            if(code == 200){
                ins = conne.getInputStream();
            }else{
                ins = conne.getErrorStream();
            }
        }catch (SSLException e){
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while((len = ins.read(buff)) != -1){
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }
    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
        }
        return sb.toString();
    }
    private static String encode(String value) throws Exception{
        return URLEncoder.encode(value, "UTF-8");
    }
    
    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
}