package odbc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

/**
 * ODBC 数据库对比器
 *
 */
public class SwingMain 
{
	private static JDialog myDialog = new JDialog();
    public static void main( String[] args )
    {
    	Container cn = myDialog.getContentPane();
    	cn.setLayout(new BorderLayout(10,10));
    	
    	MyJpanel jpn1 = new MyJpanel(new Dimension(400, 90));
    	jpn1.initN();
    	cn.add(jpn1, BorderLayout.NORTH);
    	
    	
    	MyJpanel jpw2 = new MyJpanel(new Dimension(590, 50));
    	jpw2.initW();
    	cn.add(jpw2, BorderLayout.WEST);
    	
    	
    	MyJpanel jpe3 = new MyJpanel(new Dimension(590, 50));
    	jpe3.initE();
    	cn.add(jpe3, BorderLayout.EAST);
    	
    	MyJpanel jps4 = new MyJpanel(new Dimension(1200, 400));
    	jps4.initS();
    	cn.add(jps4, BorderLayout.SOUTH);
    	
    	myDialog.setSize(1200, 850);
    	myDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//    	myDialog.pack();
    	myDialog.setVisible(true);
    	
    	
    }
}
