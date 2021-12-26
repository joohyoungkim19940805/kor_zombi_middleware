package com.korZombiMiddleware.web.middleware.utli;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.korZombiMiddleware.web.middleware.entity.AreaNameEntity;
import com.korZombiMiddleware.web.middleware.entity.AreaSizeEntity;

public class CommonUtil {


	/*
	public <T> T transEntity(Class<T> targetClass, List<?> data) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		//Constructor<?> constructor = targetClass.getClass().getDeclaredConstructor();
		//constructor.newInstance();
		T result = targetClass.newInstance();
		BeanUtils.populate(result, (Map<String, ? extends Object>) data);
		return result;
	}
	*/
	//public ArrayList<Map<String, ?>> readCsv(String path, String encodeType, String separator, String needRemoveThisText) throws FileNotFoundException, IOException {
		//return readCsv(path, encodeType, separator, needRemoveThisText, null);
	//}
	public <T> ArrayList<T> readCsv(String path, String encodeType, String separator, String needRemoveThisText, List<String> columnList, Class<T> targetClass) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException {

		ArrayList<T> result = null;//new ArrayList<>();
        BufferedReader reader = null;//new BufferedReader(new InputStreamReader(new FileInputStream(path), encodeType));

        
        try {
        	result = new ArrayList<T>();
        	reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encodeType));
	        String line;
	        if(columnList != null) {
	        	reader.readLine();
	        }
	        
	        while((line=reader.readLine()) != null) {
	            T item = targetClass.getDeclaredConstructor().newInstance();
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	//System.out.println(line);
	        	if(needRemoveThisText != null) {
	        		line = line.replace(needRemoveThisText, "");
	        	}
	        	//"\",\"" ""안에 들어간 separator은 무시
	        	String[] lineText = line.split(separator+"(?=([^\"]*\"[^\"]*\")*[^\"]*$*)");
	        	
	        	for(int i = 0 ; i < lineText.length ; i++) {
	        		lineText[i] = lineText[i].trim();
	        		if(columnList.size() == lineText.length) { 
	        			map.put(columnList.get(i), lineText[i]);
	        		}
	        		else {
	        			throw new Exception("column 매핑 실패 : " + " targetColumnList Size는 "+ columnList.size() + " 이나 csv의 column size는 " + lineText.length + " 입니다.");
	        		}
	        	}
	        	BeanUtils.populate(item, map);
	        	result.add(item);											
	        	//result.add(Arrays.asList(lineText));
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	try {
	        	if(reader != null) {
	        		reader.close();
	        	}
        	}finally {
				reader = null;
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
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String test = "test";
		ArrayList<AreaSizeEntity> test1 = new CommonUtil().readCsv("src/main/resources/kor_area_size.csv","UTF-8", ",", null, new AreaSizeEntity().columnNameList(), AreaSizeEntity.class);
		//ArrayList<List<String>> test1 = new TestUtil().readCsv("src/main/resources/my_test.csv","UTF-8");
		for(AreaSizeEntity test2 : test1) {
			System.out.println(test2);
			System.out.println(test2.getArea_name());
			System.out.println(test2.getArea_size());
		}
		
		//AreaEntity areaEntity = new CommonUtil().transEntity(AreaEntity.class, test1);
		//System.out.println(areaEntity.getArea());
	}

	//public  appendNumber(long teagetNumber, int needZero, int digit) {
		
	//}
}
