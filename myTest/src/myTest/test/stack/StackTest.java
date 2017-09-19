package myTest.test.stack;

import java.io.IOException;

import myTest.test.token.MyTokenStream;
import myTest.test.token.Token;
import myTest.test.token.TokenStream;

public class StackTest {
    public static void main(String args[]) throws Exception {
    	TokenStream ts = new MyTokenStream(System.in); 
        Stack<Token> express = new Stack<Token>(128);
		Token next = ts.getToken();
		while (next.tokenType != Token.TokenType.NONE) {
			System.out.print(next);
			if(next.tokenType == Token.TokenType.INT){
				express.push(next);
			}else if (next.tokenType != Token.TokenType.SPACE){
				int value = (int) express.pop().value;
				int value1 = (int) express.pop().value;
				Token token = new Token(Token.TokenType.INT, 0);
				switch (next.tokenType) {
				case PLUS:
					int result = value+value1;
					token.value = result;
					break;
				case MINUS:
					int result1 = value-value1;
					token.value = result1;
					break;
				case MULT:
					int result2 = value*value1;
					token.value = result2;
					break;
				case DIV:
					int result3 = value/value1;
					token.value = result3;
					break;
				default:
					throw new Exception();
				}
				express.push(token);
			}
			ts.consumeToken();
			next = ts.getToken();
		}
		System.out.println("");
		System.out.println("===============================");
		System.out.println("result:"+express.pop());
    }

	private static void matchingToken() throws IOException, Exception {
		byte[] buf = new byte[12800];
        int length = System.in.read(buf);

        Stack<Byte> s = new Stack<Byte>(128);

        for (int i = 0; i < length; i++) {
            if (buf[i] == '(' || buf[i] == '[' || buf[i] == '{') {
                s.push(buf[i]);
            }   
            else if (buf[i] == ')') {
                if (s.getTop() != null && s.getTop() == '(')
                    s.pop();
                else {
                    System.out.println("1 unmatch!");
                    System.exit(1);
                }   
            }   
            else if (buf[i] == ']') {
                if (s.getTop() != null && s.getTop() == '[')
                    s.pop();
                else {
                    System.out.println("2 unmatch!");
                    System.exit(1);
                }   
           }
           else if (buf[i] == '}') {
                if (s.getTop() != null && s.getTop() == '{')
                    s.pop();
                else {
                    System.out.println("3 unmatch!");
                    System.exit(1);
                }
            }
        }

        if (!s.isEmpty())
            System.out.println("4 unmatch!");
        else
            System.out.println("matched~");
	}
}
