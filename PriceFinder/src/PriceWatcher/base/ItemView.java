package PriceWatcher.base;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import PriceFinder.ConsoleUi;
import PriceFinder.Item;


/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
	
	Item item = new Item();
	Random rand = new Random();
	double gen = 13.0;
	ConsoleUi ui = new ConsoleUi(item);
	String url = ("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#");
    
	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
        
	/** View-page clicking listener. */
    private ClickListener listener;
    
    /** Create a new instance. */
    public ItemView() {
    	setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    
    /** Overridden here to display the details of the item. */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        //Dimension dim = getSize();
        
        //--
        //-- WRITE YOUR CODE HERE!
        //--
        int x = 20, y = 20;
        
        //isViewPageClicked( x, y);
        //g.drawImage(setIconImage(Image "hello"), x, y, this);
        //g.drawImage(getImage( "rabbit.jpg"), x, y, this);
        y+= 10;
       
     
        //g.drawString(getImage("image/rabbit.jpg"), x, y);
        
        g.drawString("Welcome to price watch here are your item details......", x, y);
        y += 20;
        
        g.drawString("Url:"+ url, x, y);
        y+=20;
        Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//#pdp-header-info>1 is the pathway adress to get the title from the browser 
		String title = document.select("#pdp-header-info > h1").text();
		g.drawString("Title:" + title, x, y);
		y+=20;
		
		
		
		g.drawString("Previous price:" + String.valueOf(gen), x, y);
		y+= 20;
		
		
		
		
        //g.drawString(ui.showItem("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#"), x, y);
    }
    
   

	//private void setIconImage(image drawImage) {
	//	 super.setIconImage(image);
		
//	}

	/** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	
    	
    	try {
			ui.toBrowse();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//--
    	return new Rectangle(5, 5, 5, 5).contains(x,  y);
    	
    	
    	
    	
    }
        
    /** Return the image stored in the given file. */
    public Image getImage(String file) {
    	
    	
        try {
        	URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
