package myTest.test.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import myTest.test.token.Token.TokenType;

public class MyTokenStream extends InputStream implements TokenStream{
	BufferedReader in;
	String context;
	int current ;
	
	public MyTokenStream(InputStream in) {
		super();
		this.in = new BufferedReader(new InputStreamReader(in));
	}

	@Override
	public Token getToken() throws IOException {
		int[] aa= new int[]{1,2,3};
		
		if(context == null || context.length() == 0){
			context = in.readLine();
		}
		char currentStr = ' ';
		try{
			currentStr = context.charAt(current);
			switch (currentStr) {
			case '(':
				return new Token(TokenType.LPAR, currentStr);
			case ')':
				return new Token(TokenType.RPAR, currentStr);
			case '+':
				return new Token(TokenType.PLUS, currentStr);
			case '-':
				return new Token(TokenType.MINUS, currentStr);
			case '*':
				return new Token(TokenType.MULT, currentStr);
			case '/':
				return new Token(TokenType.DIV, currentStr);
			case ' ':
				return new Token(TokenType.SPACE, currentStr);
			default:
				String aaString = new String(new char[]{currentStr});
				Integer intt = Integer.parseInt(aaString);
				for(;;){
					consumeToken();
					Token nextToken = getToken();
					if(nextToken.tokenType != Token.TokenType.INT || nextToken.tokenType == Token.TokenType.NONE){
						current--;
						break;
					}
					intt = intt*10+(int)nextToken.value;
				}
				return new Token(TokenType.INT, intt);
			}
		}catch (Exception e) {
			return new Token(TokenType.NONE, currentStr);
		}
	}

	@Override
	public void consumeToken() {
		current++;
		
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	
}
