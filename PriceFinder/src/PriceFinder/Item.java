
//import java.net.*;
package PriceFinder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import java.util.*;
//import java.awt.Desktop;
import java.io.*;
import java.text.DecimalFormat;
//import java.math.BigDecimal;

//this is the class item that will provide information of the item like price and name 
public class Item {
	
		
	// method will connect to the url and get the price of the item 
	public  double getItemPrice(/*String url*/) throws IOException {
		DecimalFormat numFormat = new DecimalFormat("#.00");		//final Document document = Jsoup.connect(url).get();
        //#pdp-cur-price is the pathway adress to the actual 
        //String answerers = document.select("#pdp-cur-price").text();
        //answerers = answerers.substring(0, 0) + answerers.substring(0 + 1);
        //double price = Double.parseDouble(answerers);
		
		double price = Math.random();
		price = price*100;
        
        
        return price;
         
	}
	
	
	
	
	
	// this method will open the browser and get the title of the item 
	public void getItemInfo(String url)throws IOException {
		
		final Document document = Jsoup.connect(url).get();
		
		//#pdp-header-info>1 is the pathway adress to get the title from the browser 
		String title = document.select("#pdp-header-info > h1").text();
		System.out.println("Name: " + title );
		System.out.println("URL: " +url);
	}
	

	
		
}
