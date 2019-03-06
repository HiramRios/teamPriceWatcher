

// this class will get information from item and then display it through the main 
package PriceFinder;

import java.io.IOException;
import java.util.*;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;

import java.awt.Desktop;
//import java.io.*;
//import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
//import java.util.Random;
import java.text.DecimalFormat;




public class ConsoleUi {
	private Item item;
	
	public ConsoleUi(Item item) {
		this.item = item;
		
	}

	
	public void showWelcome() {
		
		System.out.println("Welcome to price watcher");
	}
	
	
	
	// shows random price before actually getting the price 
	public void showItem(String url /*double n*/) throws IOException {
		
		System.out.println("Here're item details.....");
		
		item.getItemInfo(url);
		
		//System.out.println("Generated prcie: " + n);
		
		
		
	}
	
	// prompt user for input 
	public int promptUser() {
		System.out.println("Enter 1 (to check price), 2 (to view page), or -1 to quit?");
		Scanner scan = new Scanner(System.in);
		int myLine = scan.nextInt();
		return myLine;
		
		
	}
	
	//method will get the price from the item and compare it with the old price and see if there is a change 
	public String  changePrice(String url, double n) throws IOException {
		DecimalFormat numFormat = new DecimalFormat("#.00");
		String ring = "";
		String hello = "";
		String result = "";
		double recent = item.getItemPrice(/*url*/);
		 ring ="$" + numFormat.format(recent) + " is the price.";
		double change = ((Math.abs(n-recent))/(n))*100;
		hello = "   change of price   %" + numFormat.format(change);
		result = ring + hello;
		return result;
	
	}
	
	// method will open the browser 
	public void toBrowse() throws IOException, URISyntaxException{
		Desktop open = Desktop.getDesktop();
		open.browse(new URI("https://www.barnesandnoble.com/w/thrawn-timothy-zahn/1127203904?ean=9781984817617#"));
		
	}

}
