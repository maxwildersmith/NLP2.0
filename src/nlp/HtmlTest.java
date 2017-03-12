package nlp;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class HtmlTest extends JFrame {

	
	private JLabel label;
	public static void main(String[] args) {
		new HtmlTest();
	}
	
	public HtmlTest() throws HeadlessException {
		// TODO Auto-generated constructor stub
		
		setSize(200,200);
		setTitle("TEST");
		
		label = new JLabel();
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(label);
		add(pane);
		
		URL url;
		try {
			url = new URL("https://images.search.yahoo.com/search/images;_ylt=AwrSbghS1b1YG6sA79pXNyoA;_ylu=X3oDMTE0Y2gzM2FzBGNvbG8DZ3ExBHBvcwMxBHZ0aWQDQjMwNjBfMQRzZWMDcGl2cw--?p=images&fr2=piv-web&fr=yfp-t");
	        URLConnection con = url.openConnection();
	        InputStream is =con.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        String line = null;
	        String s="<html>";

	        while ((line = br.readLine()) != null) {
	        	s+=line;
	        }
	        label.setText(s);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
		setVisible(true);
	}

	public HtmlTest(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public HtmlTest(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public HtmlTest(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
