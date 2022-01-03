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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.BeanUtils;
import org.hamcrest.core.IsInstanceOf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;

import com.korZombiMiddleware.web.middleware.entity.AreaNameEntity;
import com.korZombiMiddleware.web.middleware.entity.AreaSizeEntity;

import ch.qos.logback.classic.Level;

public class CommonUtil {

	private Map<String, String[]> category = new HashMap<String, String[]>(){{
		put("금융 및 기타 행정기관", 		("은행, 우체국, 의회, 시청, 청사, 군청, 세무서, 농협").trim().split(","));
		put("교육기관", 				("학교, 전문대, 대학, 대학원, 학원, 수련원, 수련회").trim().split(","));
		put("기타 상업시설", 			("마트, 홈플러스, 신세계, 시장, 상가, 웨딩, 식당, 빌딩").trim().split(","));
		put("주거지 및 주거 대여 서비스", 	("아파트, 타운, 맨션, 타워, 주차장, 호텔, 모텔").trim().split(","));
		put("법정기관", 				("법원, 검찰").trim().split(","));
		put("기타 지역서비스", 			("도서관, 체육관, 사회복지관, 예술회관, 복지센터, 아트센타, 사무소, 대피시설, 대피소, 역").trim().split(","));
		put("의료서비스", 				("병원, 보건소").trim().split(","));
		put("인명 및 치안", 			("경찰, 소방").trim().split(","));
		put("종교시설", 				("교회, 성당, 불교").trim().split(","));
		put("방송국", 				("방송국").trim().split(","));
	}};

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
        BufferedReader reader = null;//new BufferedReader(new InputStreamReader(new FileInputStream(path), encodeType))
        
        try {
        	result = new ArrayList<T>();
        	reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), encodeType));
	        String line;
	        if(columnList != null) {
	        	reader.readLine();
	        }else {
	        	columnList = Arrays.asList( reader.readLine().split(separator+"(?=([^\"]*\"[^\"]*\")*[^\"]*$*)"))
				        			.stream()
									.map(x -> x.trim().replaceAll("[\\(\\)\\[\\]]", ""))
									//.map(x -> x.replaceAll("[\\(\\)\\[\\]]", ""))
									.collect(Collectors.toList());
	        	//System.out.print(columnList);
	        }
	        
	        while((line=reader.readLine()) != null) {
	            T item = targetClass.getDeclaredConstructor().newInstance();
	        	Map<String, Object> map = new HashMap<String, Object>();
	        	if(needRemoveThisText != null) {
	        		line = line.replace(needRemoveThisText, "");
	        	//마지막 문자열이 ,일 경우 인덱스 맞추기 위해 빈 문자열 넣기
	        	}
	        	//"\",\"" ""안에 들어간 separator은 무시
	        	String[] lineText = line.split(separator+"(?=([^\"]*\"[^\"]*\")*[^\"]*$*)");
	        	
	        	for(int i = 0 ; i < lineText.length ; i++) {
	        		lineText[i] = lineText[i].trim();
	        		lineText[i] = lineText[i].replaceAll("[\\(\\)\\[\\]]", "");
	        		lineText[i] = lineText[i].replaceAll("\\^m", "\\,");
	        		
	        		if(columnList.size() == lineText.length) { 
	        			map.put(columnList.get(i), lineText[i]);
	        		}
	        		else {
	        			System.out.println(columnList);
	        			System.out.println(Arrays.asList(lineText));
	        			throw new Exception("column 매핑 실패 : targetColumnList Size는 "+ columnList.size() + " 이나 csv의 column size는 " + lineText.length + " 입니다."
	        								+ columnList.get(columnList.size()-1) + "=======" + lineText[lineText.length-1]);
	        		}
	        	}
	        	//System.out.println(item instanceof HashMap);
	        	//break;
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
	
	/*
		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		logger.setLevel(Level.toLevel("error"));
	*/
	public static void main(String[] args) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
		logger.setLevel(Level.toLevel("error"));
		List<HashMap> data_list = new CommonUtil().readCsv("src/main/resources/kor_area_shelter.csv", "UTF-8", ",", "\"", null, HashMap.class);
    	//소재지전체 주소
    	/*
    	 * 은행(농협 포함), 마트 및 마켓(농협파머스마켓), 주차장, 우체국, 
    	 * 식당, 아파트 및 타운 및 맨션 및 타워, 전문대 및 대학 및 대학원, 학원 
    	 * 군청, 수련회 및 수련원, 사회복지관 및 예술회관 및 센터 및 센타, 
    	 * 사무소, 대피시설 및 대피소, 역, 교회, 성당, 웨딩,
    	 * 상가 및 빌딩, 홈플러스 및 롯데마트 및 이마트 및 신세계 및 하나로 , 세무서, 체육관, 보건소, 도서관, 법원, 경찰, 
    	 * 검찰, 호텔, 시장, 병원, 학교
    	 * */
    	//사업장명
    	//좌표정보x
    	//좌표정보y
    	//개방자치단체코드
    	//소재지면적
		/*
		String te = "tetete";
		String tete = """
						SELECT	* 
						FROM %s
						WHERE TEST = 'test';
						""";
		System.out.println(tete.formatted(te));
		*/
		int count = 0;
    	for(HashMap data : data_list) {
    		//System.out.println(data.get("소재지면적"));
    		//System.out.println(data.get("소재지전체주소"));
    		String test = String.join(" ", Arrays.asList(data.get("소재지전체주소").toString().split(" ") ).subList(0, 2));
    		//System.out.println(test);
    		//System.out.println(data.get("좌표정보x"));
    		//System.out.println(data.get("좌표정보y"));
    		//System.out.println(data.get("개방자치단체코드"));
    		//String test_2 = data.get("사업장명").toString();
    		//String test_3 = test_2.lastIndexOf("센타") > 0 ? test_2:"false";
    		//System.out.println(data.get("사업장명"));
    		//System.out.println(test_3);
    		if(test( data.get("사업장명").toString()).isEmpty()) {
    			count ++;
    		}
    		//System.out.println(test_2.matches(".*[^주][^차][^장][아][파][트]*")); //아파트
    		//System.out.println(test_2.matches(".*[주][차][장]*")); //주차장
    		
    		
    				/*Arrays.asList( data.get("소재지전체주소").toString().split(" ") )
    						.stream()
    						.map(x -> ).collect(Collectors.toList());*/
    		//for(String te : test) {
    		//	System.out.println(te);
    		//}
    		
    	}
    	System.out.println(count);
	}
	//금응 및 기타 행정기관//교육기관//마트//주거지//법정기관//기타 지역서비스//인명 및 치안//의료기관//
	//주거 대여 서비스//종교//방송
	/*
	금응				 	 : 은행,세무서,농협,금고,보험,
	행정기관				 : 교육청,우체국,의회,시청,청사,군청,구청,
	교육기관 			 	 : 학교,전문대,대학,대학원,학원,수련원,수련회,교육원,유치원
	기타 상업시설		 	 : 마트,홈플러스,신세계,시장,상가,웨딩,식당,빌딩,백화점
	주거지 및 주거 대여 서비스	 : 아파트,타운,맨션,타워,주차장,호텔,모텔,단지,주택,빌라,오피스텔,리조트
	법정기관			 	 : 법원,검찰
	기타 지역서비스		 	 : 도서관,체육관,사회복지관,예술회관,복지센터,아트센타,사무소,대피시설,대피소,역,주민센터,회관,문화센터,체육센터,
	의료서비스				 : 병원,보건소,의료,요양,복지,약국,약품,한의원
	인명 및 치안			 : 경찰,소방
	종교시설				 : 교회,성당,불교,
	방송				 	 : 방송국
	연구기관				 : 연구소, 연구원
	*/
	public static String test(String name) {
		String arr[] =  """
						은행,세무서,농협,금고,보험,금융,
						교육청,우체국,의회,시청,청사,군청,구청,
						학교,전문대,대학,대학원,학원,수련원,수련회,교육원,유치원,수련관,학습관,
						마트,홈플러스,신세계,시장,상가,웨딩,식당,빌딩,백화점,쇼핑,
						아파트,타운,맨션,타워,주차장,호텔,모텔,단지,주택,빌라,오피스텔,리조트,푸르지오,그린빌,e편한세상,e-편한,
						법원,검찰,
						도서관,체육관,사회복지관,예술회관,복지센터,아트센타,사무소,대피시설,대피소,역,주민센터,회관,문화센터,체육센터,지원센터,지원청,
						병원,보건소,의료,요양,복지,약국,약품,한의원,메디컬,
						경찰,소방,
						교회,성당,불교,
						방송국,전화국,KT,LG,LGU,SK,sk,kt,lg,
						연구소,연구원,연구센터
						""".replaceAll("\\n", "").trim().split(",");
		int prevIndex = -1;
		int targetArrIndex = -1;
		String result = "";
		for(int i = 0 ; i < arr.length ; i++) {
			int nextIndex = name.lastIndexOf(arr[i]);
			if(prevIndex < nextIndex && nextIndex != -1) {
				prevIndex = nextIndex;
				targetArrIndex = i;
			}
		}
		result = targetArrIndex != -1 ? arr[targetArrIndex] : "";
		if(targetArrIndex == -1) {
			System.out.println(name);
		}
		//System.out.println(result);
		return result;
	}
}
