package nlp;

import java.io.Serializable;
import java.util.ArrayList;

public class Type implements Serializable{

	public String name;
	public static int id =-1;
	
	public ArrayList<String> attributes;
	
	public Type() {
		id++;
	}
	public Type(String nam) {
		id++;
		name = nam;
	}
	
	public ArrayList<String> getTitles(){
		return attributes;
	}
	
	public void addAttr( String title){
		attributes.add(title);
	}
	
	public String getName(){
		return name;
	}
	
	//public static Type 
	
	public static int getNumTypes(){
		return Type.id;
	}
	
	public int getNumOfAttr(){
		return attributes.size();
	}

}
