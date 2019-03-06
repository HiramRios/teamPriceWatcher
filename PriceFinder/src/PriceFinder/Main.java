/*
 * Hiram Rios 80552404 
 * the purpose of this homework is to get familiar with and back into the pace of using java and warming up with objects and classes 
 * we are designing a price watcher that will check the price of a item and let you go to the website as well
 */




package PriceFinder;
import java.net.*;

import PriceWatcher.base.noapplet;

import java.awt.Desktop;
import java.io.*;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;


public class Main extends noapplet  {
	public Main() {
    }
	
    public Main(String[] params) {
	super(params);
    }

	

	public static void main(String[] args)/*throws IOException, URISyntaxException*/ {
		
		
		new Main(args).run();
		String url = ("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#");
         
		try {
			new Main().run(url);
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void run(String url) throws IOException, URISyntaxException {
		Item item = new Item();
		double gen =13.0;
		
		ConsoleUi ui = new ConsoleUi(item);
		ui.showWelcome(); 
		
		//repeat until user quit , print how item , prompt the user 
		int request = -1;
		do {
			ui.showItem(url /*gen*/);
			
			
			request = ui.promptUser();
			switch (request) {
			case 1:
				ui.changePrice(url, gen);
				gen = item.getItemPrice(/*url*/);
				break;
			case 2:
				ui.toBrowse();
				
			}
		}while(request != -1);
	
	
	}
		

}
