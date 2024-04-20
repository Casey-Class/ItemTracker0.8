package application;

public class Location {
	
private String name;
private String description;
	
	//Creates Location Object
	public Location(String s)
	{
		name = s;
		description = null;
	}
	
	public Location(String s, String d) {
		name = s;
		description = d;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setName(String s)
	{
		name = s;
	}
	
	public void setDescription(String d)
	{
		description = d;
	}
	
	public static Location toLocation(String line) {
	    String[] parts = line.split(":", 2); // Split the line at the first ":" into two parts

	    if (parts.length == 2) { // Check if the line has both name and description
	        String name = parts[0].trim(); // Trim to remove any leading or trailing whitespace
	        String description = parts[1].trim(); // Trim to remove any leading or trailing whitespace

	        return new Location(name, description);
	    } else if (parts.length == 1) { // If no description is provided
	        String name = parts[0].trim(); // Trim to remove any leading or trailing whitespace

	        return new Location(name);
	    } else {
	        throw new IllegalArgumentException("Invalid format: " + line);
	    }
	}
	
	@Override
    public String toString() {
		if(description.isEmpty())
		{
			description = null;
			return name + ":" + description;
		}
		return name + ":" + description;
    }

}
