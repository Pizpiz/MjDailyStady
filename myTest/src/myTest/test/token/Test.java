package myTest.test.token;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {

//		Scanner scanner = new  Scanner(System.in);
		TokenStream ts = new MyTokenStream(System.in); // 标准输入的适配器
		Token next = ts.getToken();
		while (next.tokenType != Token.TokenType.NONE) {
			System.out.print(next);
			ts.consumeToken();
			next = ts.getToken();
		}

	}
}
