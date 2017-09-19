package myTest.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) throws IOException {
/*		try{
			// TODO Auto-generated method stub
			Long aa = (long) 0x56;
			System.out.println(aa);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

	/*	try
		{ 
		System.out.println("本机的IP = " + InetAddress.getLocalHost());
		} catch (UnknownHostException e)
		{ 
		e.printStackTrace();
		}*/
		
		
//		System.out.println(getV4IP());
		
		
		
/*		File aaa = new File("D:\\22222\\");
		System.out.println(aaa.isDirectory());
		for (File string : aaa.listFiles()) {
			string.delete();
		}
		
		aaa.delete();*/
/*		
		FileReader aaa = null ;
		try {
			aaa = new FileReader("D:/3333.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!aaa.ready())
			System.out.println("mmm");
		BufferedReader bbb = new BufferedReader(aaa);
		System.out.println("`````"+bbb.readLine());
		*/
		String aa = "20170909166666661";
		System.out.println(aa.substring(8));
	}
	
	
	public static String getV4IP(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";
		
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			//System.out.println(inputLine.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
			//System.out.println(ipstr);
		}
//		return ip;
		return inputLine.toString();
	}

}