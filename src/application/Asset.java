package application;

import java.time.LocalDate;

public class Asset {
	private String name;
	private String category;
	private String location;
	
	private String value = null;
	private String description = null;
	private LocalDate date = null;
	private LocalDate warranty = null;
	
	public Asset(String n, String c, String l)
	{
		name = n;
		category = c;
		location = l;
	}
	
	public void addName(String n){
		name = n;
	}
	
	public void addCategory(String c){
		category = c;
	}
	
	public void addLocation(String l){
		location = l;
	}

	public void addValue(String v){
		value = v;
	}
	
	public void addDescription(String d){
		description = d;
	}
	
	public void addDate(LocalDate d){
		date = d;
	}
	
	public void addWarranty(LocalDate w){
		warranty = w;
	}

	
	@Override
    public String toString() {  //Print Asset
		
		return name + ":" + category + ":" + location + ":" + value + ":" + description + ":" + date + ":" + warranty;
		
	}
}
