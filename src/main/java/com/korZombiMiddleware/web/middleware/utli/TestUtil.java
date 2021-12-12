package com.korZombiMiddleware.web.middleware.utli;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.SortedMap;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.korZombiMiddleware.web.middleware.entity.TestEntity;

public class TestUtil {

	
	public String readCsv(String path, String encodeType ) throws FileNotFoundException, IOException {
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		String result = null;
		for (String charSet : charsets.keySet()) {
			try {
				result = new String( Files.readString(Paths.get(path)));//, charsets.get(charSet)) );
				System.out.println("kjh >>> " + Pattern.matches("^[ㄱ-ㅎ가-힣]*$", result));
				if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", result)) {
					
					//break;
				}else {
					throw new MalformedInputException(0);
				}
			}catch(Exception e) {
				System.out.println(charSet);
			}
		}
		return result;
	}
	
	public Map<String, Object> callApi(String targetUrl, String method, Hashtable<String, String> header, String body) throws IOException, ParseException {
		URL url = null;
		HttpURLConnection conn = null;
	    BufferedReader bufferedReader = null;
	    OutputStreamWriter streamWriter = null;
	    StringBuilder stringBuilder = new StringBuilder();
    	InputStreamReader tagetInputStream = null;
	    String charset = "UTF-8";
	    
	    if(targetUrl == null || targetUrl.isEmpty() == true)
	    {
	    	throw new IllegalArgumentException("Parameter targetUrl is null.");
	    }
	    
	    try 
	    {
	    	url = new URL(targetUrl);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);	// POST, PUT
	        conn.setDoInput(true);	// GET
	        
	        // header 설정
	        if(header != null)
	        {
	        	Enumeration<String> headerKeys = header.keys();
		        while (headerKeys.hasMoreElements())
		        {
		        	String key = headerKeys.nextElement();
		        	conn.setRequestProperty(key, header.get(key));
		        }
	        }
	        /*
	        if (header == null || header.get("Content-Type") == null) 
	        {
	        	 conn.setRequestProperty("Content-Type", "application/json");
	        }
	        
	        if (header == null || header.get("Accept") == null) 
	        {
	        	conn.setRequestProperty("Accept", "application/json");
	        }
	        
	        if (header == null || header.get("Cache-Control") == null) 
	        {
	        	conn.setRequestProperty("Cache-Control", "no-cache");
	        }
	        */
	        if(method == null || method.isEmpty() == true)
	        {
	        	method = "GET";
	        }
	        
	        conn.setRequestMethod(method);
	        
	        // method가 POST인 경우에만 실행
	        // POST인 경우에 대해서만  Request Body에 전송하고자 하는 데이터를 실어서 보낼 수 있음 (HTTP 규약)
        	if (method.equalsIgnoreCase("POST") && body != null && body.isEmpty() == false)
        	{
        		streamWriter = new OutputStreamWriter(conn.getOutputStream(), charset);
        		streamWriter.write(body);
        		streamWriter.flush();
        	}
        	
        	// 해당 url로 연결이 성공 할 경우
        	if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) 
        	{
        		// 해당 url에서 데이터 스트림을 읽어온다.
        		tagetInputStream = new InputStreamReader(conn.getInputStream(),charset);
        	}
        	// 해당 url로 연결에 실패 할 경우
        	else 
        	{
        		// 실패 후 출력된 데이터 스트림을 error 스트림 객체로 읽어온다.
        		tagetInputStream = new InputStreamReader(conn.getErrorStream(), charset);
        	}
        	
        	// 해당 url로 읽어온 데이터 스트림을 읽는다.
        	bufferedReader = new BufferedReader(tagetInputStream);
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) 
            {
            	// 읽어온 결과 값을 line단위로 읽어서 문자열로 append 한다.
                stringBuilder.append(tempString + "\n");
            }
            
            // 서버로부터 받은 response 객체의 값들 중 code, message, responseBody만 저장하여 반환한다.
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("responseCode", conn.getResponseCode());
            response.put("responseMessage", conn.getResponseMessage());
            response.put("responseBody", (JSONObject) new JSONParser().parse(stringBuilder.toString() ));
            
            return response;
	    }
	    finally
	    {
	    	if(tagetInputStream != null) {
	    		tagetInputStream.close();
	    	}
	    	
	    	if(bufferedReader != null)
	    	{
	    		bufferedReader.close();
	    	}
            
	    	if(streamWriter != null)
	    	{
	    		streamWriter.close();
	    	}
	    	
            if (conn != null) 
            {
            	conn.disconnect();
            }
	    }
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try {
			System.out.println(new TestUtil().readCsv("src/main/resources/kor_area_name.csv","UTF-8"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//public  appendNumber(long teagetNumber, int needZero, int digit) {
		
	//}
}
