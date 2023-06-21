package kr.ac.kopo.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {

	private Map<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<>();
		Properties prop = new Properties();
		
		try {
			InputStream is= new FileInputStream("C:\\Users\\User\\eclipse-workspaceJSP\\MyBanking\\bean.properties");
			prop.load(is); //파일읽어옴
			
			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
				String className = prop.getProperty(key.toString());
				//System.out.println(key + " : " + className);
				//mappings.put(key, className);	
				
				Class<?> clz = Class.forName(className);
				Constructor<?> cons = clz.getConstructor();
				
				mappings.put(key.toString(), (Controller)cons.newInstance());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//mappings.put("/login.do", new LoginController());
		//mappings.put("/member/mypage.do", new MyPageController());
	}
	
	public HandlerMapping(String propLoc) {
		mappings = new HashMap<>();
		Properties prop = new Properties();
		
		try {
			InputStream is= new FileInputStream(propLoc);
			prop.load(is); //파일읽어옴
			
			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
				String className = prop.getProperty(key.toString());
				//System.out.println(key + " : " + className);
				//mappings.put(key, className);	
				
				Class<?> clz = Class.forName(className);
				Constructor<?> cons = clz.getConstructor();
				
				mappings.put(key.toString(), (Controller)cons.newInstance());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//mappings.put("/login.do", new LoginController());
		//mappings.put("/member/mypage.do", new MyPageController());
	}
	
	public Controller getController(String uri) {
		return mappings.get(uri);
	}
	
	/*
	public static void main(String args[]) throws Exception{
		//java.util.Random r = new java.util.Random();
		//System.out.println(r.nextInt());
		
		Class<?> clz =  Class.forName("java.util.Random");
		Constructor<?> constructor = clz.getConstructor();
		Object obj = constructor.newInstance();
		System.out.println(((java.util.Random)obj).nextInt());
	}*/
}
