package my201706.StatmentM;

import java.util.ArrayList;
import java.util.List;

public class StatmentM {
	static String regStr = "\\w" ;
	static String regSpa = "[ ]" ;
	static String regqout = "[\\pP‘’“”]" ;
	static List<String>  inputStr = new ArrayList<>();
	static String input = "I have a good news :\"hello world\"";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int index = 0;
		while(index < input.length()){
			char aa = input.charAt(index);
			if(new String(new char[]{aa}).matches(regStr)){
				index = getRegex(input,index,regStr);
			}else if (new String(new char[]{aa}).matches(regSpa)){
				index = getRegex(input,index,regSpa);
			}else if(new String(new char[]{aa}).matches(regqout)){
				index = getRegex(input,index,regqout);
			}else {
				throw new Exception();
			}
			if(index == input.length()){
				break;
			}
		}
		for (String string : inputStr) {
			System.out.println(string);
		}
	}

	private static int getRegex(String input, int index,String regex) {
		String result = "";
		while(index < input.length()){
			char aa = input.charAt(index++);
			if(new String(new char[]{aa}).matches(regqout)){
				result += aa;
			}else{
				index--;
				break;
			}
		}
		inputStr.add(result);
		return index;
	}
}
