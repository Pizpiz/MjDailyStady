package myTest.test.token;

public class Token {
	  public enum TokenType {
	        LPAR, RPAR,
	        PLUS,
	        MINUS,
	        MULT,
	        DIV,
	        INT,
	        SPACE,
	        NONE,
	    }
	    public TokenType tokenType;
	    public Object value;

	    public Token(TokenType tt, Object v) {
	        this.tokenType = tt;
	        this.value = v;
	    }

		@Override
		public String toString() {
			return "Token [tokenType=" + tokenType + ", value=" + value + "]";
		}

}
