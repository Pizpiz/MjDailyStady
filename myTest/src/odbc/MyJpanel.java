package odbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyJpanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension defulteD;
	private Connection cnn = null;
	private static List<Map<String, String>> listW = new ArrayList<>();
	private static List<Map<String, String>> listE = new ArrayList<>();
	private static String unique = "";
	private static final JTextArea jta1 = new JTextArea("");
	private static final JTextArea jta2 = new JTextArea("");
	private static final JTextArea jta3 = new JTextArea("");
	private static final String url = "jdbc:odbc:CANYINASA;UID=dba;PWD=1AC83986619AA40E92";    
//	private static final String url = "jdbc:odbc:CANYINASA1;UID=dba;PWD=ZOa1pgkW";    
	
	public MyJpanel(Dimension defulteD) {
		super();
		this.defulteD = defulteD;
	}
	public MyJpanel() {
		// TODO Auto-generated constructor stub
	}
	public void initN(){
		setPreferredSize(defulteD);
	   	setLayout(new BorderLayout());
    	JLabel jl1 = new JLabel("请输入sql:");
    	add(jl1, BorderLayout.WEST);
    	add(jta1,BorderLayout.CENTER);
	}
	public void initW(){
		setPreferredSize(defulteD);
		setLayout(new BorderLayout());
    	JScrollPane jsb2 = new JScrollPane(jta2);
    	JButton jbt2 = new JButton("查询");
    	add(jbt2,BorderLayout.WEST);
    	add(jsb2,BorderLayout.CENTER);
    	jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try(
						Connection cnn = DriverManager.getConnection(url);
						Statement st = cnn.createStatement();
						ResultSet rs = st.executeQuery(jta1.getText());
						) {
					new Thread(new Runnable() {
						public void run() {
							MyJpanel.getJta2().setText("");
							MyJpanel.getJta2().repaint();
							System.out.println("+++++++++++++++++++++++++++++++++++++++");
						}
					}).start();
			    	unique = jta1.getText();
			    	unique = unique.substring((unique.indexOf("select") != -1 ? unique.indexOf("select"):unique.indexOf("SELECT"))+6, unique.indexOf(","));
			    	unique = unique.trim();
			    	MyJpanel.setUnique(unique);
					int i=1;
					List<Map<String, String>> reMapList = new ArrayList<>();
					Map<String, String> rsMap = new HashMap<String, String>();
					while(rs.next()){
						rsMap = new HashMap<String, String>();
						System.out.println("===rowNum"+i++);
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnC = rsmd.getColumnCount();
						for(int k = 1; k <= columnC ;k++){
							System.out.println("===colNum"+k);
							String name = rsmd.getColumnName(k);
							System.out.println("===ColumnName:"+name);
							String colType = rsmd.getColumnTypeName(k);
							System.out.println("===colType:"+colType);
							String result ="";
							String nn;
							if("long varchar".equalsIgnoreCase(colType)){
								result = (String) rs.getObject(k);
								System.out.println("===nn:"+result);
							}else{
								result = rs.getString(k);
								System.out.println("===result:"+result);
							}
							rsMap.put(name,result );
						}
						reMapList.add(rsMap);
						MyJpanel.setListW(reMapList);
					}
					System.out.println("===end");
					String column = "";
					String value = "";
					Set<Entry<String, String>> rsEntry = rsMap.entrySet();
					for (Entry<String,String> entry : rsEntry) {
						if(entry.getKey() != null){
							column+= String.format("%1$-15s", entry.getKey().substring(0, entry.getKey().length() > 15 ? 14 : entry.getKey().length()))+'|';
						}else{
							column+= String.format("%1$-15s", entry.getKey())+'|';
						}
					}
					for (Map<String,String> map : reMapList) {
						Set<Entry<String, String>> rsEntry1 = map.entrySet();
						for (Entry<String,String> entry : rsEntry1) {
							if(entry.getValue() != null){
								value+= String.format("%1$-15s", entry.getValue().substring(0, entry.getValue().length() > 15 ? 14 : entry.getValue().length()))+'|';
							}else{
								value+= String.format("%1$-15s", entry.getValue())+'|';
							}
						}
						value= value+" \r\n";
					}
					String finalR = column+" \r\n"+value+" \r\n"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					jta2.setFont(new Font("黑体", Font.BOLD, 15));
					jta2.setText(finalR);
				} catch (Exception e1) {
					System.out.println("=========cuowu");
					e1.printStackTrace();
				}
			}
		});
	}
	public void initE(){
		setPreferredSize(defulteD);
		setLayout(new BorderLayout());
		JButton jbt3 = new JButton("查询");
    	jbt3.setMaximumSize(new Dimension(50, 50));
    	JScrollPane jsb3 = new JScrollPane(jta3);
    	add(jbt3,BorderLayout.WEST);
    	add(jsb3,BorderLayout.CENTER);
    	jbt3.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				try(
    						Connection cnn = DriverManager.getConnection(url);
    						Statement st = cnn.createStatement();
    						ResultSet rs = st.executeQuery(jta1.getText());
    						) {
    					new Thread(new Runnable() {
    						public void run() {
    							MyJpanel.getJta3().setText("");
    							MyJpanel.getJta3().repaint();
    							System.out.println("+++++++++++++++++++++++++++++++++++++++");
    						}
    					}).start();
    			    	unique = jta1.getText();
    			    	unique = unique.substring((unique.indexOf("select") != -1 ? unique.indexOf("select"):unique.indexOf("SELECT"))+6, unique.indexOf(","));
    			    	unique = unique.trim();
    			    	MyJpanel.setUnique(unique);
    					int i=1;
    					List<Map<String, String>> reMapList = new ArrayList<>();
    					Map<String, String> rsMap = new HashMap<String, String>();
    					while(rs.next()){
    						rsMap = new HashMap<String, String>();
    						System.out.println("===rowNum"+i++);
    						ResultSetMetaData rsmd = rs.getMetaData();
    						int columnC = rsmd.getColumnCount();
    						for(int k = 1; k <= columnC ;k++){
    							System.out.println("===colNum"+k);
    							String name = rsmd.getColumnName(k);
    							System.out.println("===ColumnName:"+name);
    							String colType = rsmd.getColumnTypeName(k);
    							System.out.println("===colType:"+colType);
    							String result ="";
    							String nn;
    							if("long varchar".equalsIgnoreCase(colType)){
    								result = (String) rs.getObject(k);
    								System.out.println("===nn:"+result);
    							}else{
    								result = rs.getString(k);
    								System.out.println("===result:"+result);
    							}
    							rsMap.put(name,result );
    						}
    						reMapList.add(rsMap);
    						MyJpanel.setListE(reMapList);
    					}
    					System.out.println("===end");
    					String column = "";
    					String value = "";
    					Set<Entry<String, String>> rsEntry = rsMap.entrySet();
    					for (Entry<String,String> entry : rsEntry) {
    						if(entry.getKey() != null){
    							column+= String.format("%1$-15s", entry.getKey().substring(0, entry.getKey().length() > 15 ? 14 : entry.getKey().length()))+'|';
    						}else{
    							column+= String.format("%1$-15s", entry.getKey())+'|';
    						}
    					}
    					for (Map<String,String> map : reMapList) {
    						Set<Entry<String, String>> rsEntry1 = map.entrySet();
    						for (Entry<String,String> entry : rsEntry1) {
    							if(entry.getValue() != null){
    								value+= String.format("%1$-15s", entry.getValue().substring(0, entry.getValue().length() > 15 ? 14 : entry.getValue().length()))+'|';
    							}else{
    								value+= String.format("%1$-15s", entry.getValue())+'|';
    							}
    						}
    						value= value+" \r\n";
    					}
    					String finalR = column+" \r\n"+value+" \r\n"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    					jta3.setFont(new Font("黑体", Font.BOLD, 15));
    					System.out.println(listE);
    					jta3.setText(finalR);
    				} catch (Exception e1) {
    					System.out.println("=========cuowu");
    					e1.printStackTrace();
    				}
    			}
    		});
	}
	public void initS() {
		setPreferredSize(defulteD);
		setLayout(new BorderLayout());
		JButton jbt4 = new JButton("对比");
		jbt4.setMaximumSize(new Dimension(50, 50));
    	final JTextArea jta4 = new JTextArea("");
    	JScrollPane jsb4 = new JScrollPane(jta4);
/*    	jta4.setMaximumSize(new Dimension(400, 400));
    	jta4.setPreferredSize(new Dimension(400,400));*/
    	add(jbt4,BorderLayout.WEST);
    	add(jsb4,BorderLayout.CENTER);
    	jbt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("``````````````````````````"+unique);
				List<Map<String, String>> listW = MyJpanel.getListW();
				List<Map<String, String>> listE = MyJpanel.getListE();
				int lenth = listE.size() > listW.size()? listE.size() :listW.size();
				String changeStr = new String();
				for (int i = 0; i < lenth; i++) {
					Map<String, String> eMap = null;
					Map<String, String> wMap = null;
					if(i < listE.size()){
						eMap = listE.get(i);
					}
					if(i < listW.size()){
						wMap = listW.get(i);
					}
					StringBuffer change = new StringBuffer();
					change.append("{ \r\n");
					if(eMap == null || wMap == null){
						Map<String, String> insertMap = eMap != null ? eMap : wMap;
						for (Entry<String, String> iEntry : insertMap.entrySet()) {
							if(iEntry.getKey().trim().equals(MyJpanel.getUnique())){
								change.insert(4, "\t\""+iEntry.getKey()+"\":\""+iEntry.getValue()+"#\", \r\n");
							}
							change.append("\t\""+iEntry.getKey()+"\"").append(":").append("\"null").append("-->").append(iEntry.getValue()).append("\", \r\n");
						}
					}else {
						Iterator<Entry<String, String>> eIterator = eMap.entrySet().iterator();
						Iterator<Entry<String, String>> wIterator = wMap.entrySet().iterator();
						while(eIterator.hasNext() && wIterator.hasNext()){
							Entry<String, String> eEntry = eIterator.next();
							Entry<String, String> wEntry = wIterator.next();
							System.out.println(wEntry.getKey());
							System.out.println(wEntry.getKey());
							String eValue = eEntry.getValue();
							String wValue = wEntry.getValue();
							if(eEntry.getKey().trim().equals(MyJpanel.getUnique())){
								change.insert(4, "\t\""+wEntry.getKey()+"\":\""+wValue+"#\", \r\n");
							}
							if(eValue != null ? eValue.equals(wValue): (wValue != null ? wValue.equals(eValue) : true)){
								continue;
							}else{
								change.append("\t\""+eEntry.getKey()+"\"").append(":").append("\""+eValue).append("-->").append(wValue).append("\", \r\n");
							}
						}
					}
					change.replace(change.lastIndexOf(","), change.lastIndexOf(",")+1, "");
					change.append("} \r\n");
					if(change.lastIndexOf(",") !=-1 ){
						changeStr += change.append(" \r\n").toString();
					}
				}
				System.out.println("``````````````````````````");
				jta4.setFont(new Font("黑体", Font.BOLD, 15));
				jta4.setText(changeStr);
			}
		});
	}
	public static List<Map<String, String>> getListW() {
		return listW;
	}
	public static void setListW(List<Map<String, String>> listW) {
		MyJpanel.listW = listW;
	}
	public static List<Map<String, String>> getListE() {
		return listE;
	}
	public static void setListE(List<Map<String, String>> listE) {
		MyJpanel.listE = listE;
	}
	public static String getUnique() {
		return unique;
	}
	public static void setUnique(String unique) {
		MyJpanel.unique = unique;
	}
	public static JTextArea getJta2() {
		return jta2;
	}
	public static JTextArea getJta3() {
		return jta3;
	}


}
