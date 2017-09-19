package treeTest;

import myTest.test.stack.Stack;

public class DLR {
	private static Stack<Object> st = new Stack<>(100);
	private static Stack<Object> stR = new Stack<>(100);
	private static Stack<Object> stC = new Stack<>(100);
	
public static void main(String[] args) throws Exception {
	   TreeNode a = new TreeNode(Integer.valueOf(1));
       TreeNode b = new TreeNode(Integer.valueOf(2));
       TreeNode c = new TreeNode(Integer.valueOf(3));
       TreeNode d = new TreeNode(Integer.valueOf(4));
       TreeNode e = new TreeNode(Integer.valueOf(5));
       TreeNode f = new TreeNode(Integer.valueOf(6));
       TreeNode g = new TreeNode(Integer.valueOf(7));
       TreeNode h = new TreeNode(Integer.valueOf(8));
       TreeNode i = new TreeNode(Integer.valueOf(9));
       TreeNode j = new TreeNode(Integer.valueOf(10));
       
       a.setLeft(b);
       a.setRight(c);
       c.setRight(d);
       c.setLeft(e);
       e.setRight(f);
       f.setLeft(g);
       f.setRight(h);
       d.setLeft(i);
       d.setRight(j);
       
       st.push(a);
       for(TreeNode currentNode = (TreeNode) st.getTop();!st.isEmpty();currentNode = (TreeNode) st.getTop()){
    	   if(!(currentNode.isLeftPush() || currentNode.isRightPush())){
    		   stR.push(currentNode);
//    		   System.out.println(currentNode.getData());
    	   }
    	   if(currentNode.getLeft() != null && !currentNode.isLeftPush()){
    		   currentNode.setLeftPush(true);
    		   currentNode = (TreeNode) currentNode.getLeft();
    		   st.push(currentNode);
    		   continue;
    	   }else if(currentNode.getRight() != null && !currentNode.isRightPush()){
    		   currentNode.setRightPush(true);
    		   currentNode = (TreeNode) currentNode.getRight();
    		   st.push(currentNode);
    		   continue;
    	   }
    	   st.pop();
       }
     while(!stR.isEmpty()){
    	 stC.push((TreeNode) stR.pop());
     }
     while(!stC.isEmpty()){
    	 System.out.println(((TreeNode)stC.pop()).getData());
     }
}
}
class TreeNode extends Node{
	public TreeNode(Object data) {
		super(data);
	}
	private boolean rightPush = false;
	private boolean leftPush = false;
	public boolean isRightPush() {
		return rightPush;
	}
	public void setRightPush(boolean rightPush) {
		this.rightPush = rightPush;
	}
	public boolean isLeftPush() {
		return leftPush;
	}
	public void setLeftPush(boolean leftPush) {
		this.leftPush = leftPush;
	}

}
