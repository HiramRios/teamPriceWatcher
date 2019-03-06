package PriceWatcher.base;


import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.sound.sampled.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import PriceFinder.ConsoleUi;
import PriceFinder.Item;


import java.net.*;
import java.awt.Desktop;
import java.io.*;

/**
* A dialog for tracking the price of an item.
*
* @author Yoonsik Cheon
*/	
@SuppressWarnings("serial")
public class Main extends JFrame {
	
	Item item = new Item();
	ConsoleUi ui = new ConsoleUi(item);
	Random rand = new Random();
	double gen = 13.0;
	String url = ("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#");
	//String musicFile = ("/Users/hiramrios/eclipse-workspace/PriceFinder/src/PriceFinder/nazgul_scream");
	
//	public void AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
//		AudioInputStream stream = AudioSystem.getAudioInputStream(new File(musicFile).getAbsoluteFile());
//		Clip clip = AudioSystem.getClip();
//		clip.open(stream);
//		clip.start();
//	}


    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(400, 300);
	private static final String AudioPlayer = null;
      
    /** Special panel to display the watched item. */
    private ItemView itemView;
      
    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create a new dialog. 
     * @throws URISyntaxException */
    public Main() throws URISyntaxException {
    	this(DEFAULT_SIZE);
    }
    
    /** Create a new dialog of the given screen dimension. 
     * @throws URISyntaxException */
    public Main(Dimension dim) throws URISyntaxException {
        super("Price Watcher");
        setSize(dim);
        
        configureUI();
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
        showMessage("Welcome!");
    }
  
    /** Callback to be invoked when the refresh button is clicked. 
     * Find the current price of the watched item and display it 
     * along with a percentage price change. */
    private void refreshButtonClicked(ActionEvent event) {
    	//play(getCodeBase(), "hello");
    	try {
			String test = ui.changePrice(url, gen);
			showMessage(test);
			
	        try
	        { 
	        	String audioFile = ("/Users/hiramrios/eclipse-workspace/PriceFinder/src/PriceWatcher/base/nazgul_scream.wav"); 
	            AudioPlayer ap =  new AudioPlayer(); 
	            ap.play(); 	 
	            }  
	          
	        catch (Exception ex)  
	        { 
	            System.out.println("Error with playing sound."); 
	            ex.printStackTrace(); 
	          
	          } 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked(java.awt.event.ActionEvent event ) {
    	
    	
    	try {
			ui.toBrowse();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	showMessage("View clicked!");
    }
        
    /** Configure UI. 
     * @throws URISyntaxException */
    private void configureUI() throws URISyntaxException {
        setLayout(new BorderLayout());
        JPanel control = makeControlPanel();
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16)); 
        add(control, BorderLayout.NORTH);
        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createEmptyBorder(10,16,0,16),
        		BorderFactory.createLineBorder(Color.GRAY)));
        board.setLayout(new GridLayout(1,1));
        itemView = new ItemView();
        itemView.setClickListener(()-> {});
        board.add(itemView);
        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }
      
    /** Create a control panel consisting of a refresh button. 
    @throws URISyntaxException
    @throws IOException
    */
    private JPanel makeControlPanel() throws URISyntaxException {
    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    	JButton refreshButton = new JButton("Refresh");
    	JButton webPage = new JButton("URL");
    	refreshButton.setFocusPainted(false);
    	webPage.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        webPage.addActionListener(proceedWeb ->{
        	viewPageClicked(proceedWeb);
        		
        });
        panel.add(refreshButton);
        panel.add(webPage);
        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
        	try {
				Thread.sleep(3 * 1000); // 3 seconds
			} catch (InterruptedException e) {
			}
        	if (msg.equals(msgBar.getText())) {
        		SwingUtilities.invokeLater(() -> msgBar.setText(" "));
        	}
        }).start();
    }
    
    public static void main(String[] args) throws URISyntaxException {
        new Main();
    }

}
