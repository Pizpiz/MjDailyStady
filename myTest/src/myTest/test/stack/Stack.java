package myTest.test.stack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stack<T> {
    private List<T> data;
    private int size;
    private int top = 0; // 指向栈的顶部

    public Stack(int size) {
        this.size = size;
        this.data = new ArrayList<T>(size);
    }
    
    public void push(T num) throws Exception {
    	if(top < size){
    		data.add(num);
    		top++;
    	}else{
    		throw new Exception("空间已满");
    	}
    }
    public T pop() {
    	if(top > 0){
    		T result = data.remove(top-1);
    		top--;
    		return result;
    	}else {
    		return null;
		}
    }
    
    public T getTop() {
    	if(top > 0){
    		T result = data.get(top-1);
    		return result;
    	}else {
    		return null;
		}
    }
    
    public boolean isEmpty() {
        return top == 0;
    }
}
