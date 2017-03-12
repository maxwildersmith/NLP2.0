package nlp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Neuron implements Serializable{

	private static int id=0;
	String name;
	ArrayList<String> desc;
	ArrayList<ArrayList<String>> attributes;
	Type type;
	
	public Neuron(String n, ArrayList<String> description) {
		id++;
		name = n;
		desc = description;
	}
	public Neuron(String n, ArrayList<String> description, Type type) {
		id++;
		name = n;
		desc = description;
		this.type = type;
	}
	public Neuron(String n) {
		id++;
		name = n;
		desc = new ArrayList<String>();
	}
	public void setDescription(ArrayList<String> d){
		desc = d;
	}
	public ArrayList<String> getDescription(){
		return desc;
	}
	/**
	 * compare order:
	 * 1. type(word vs person, type defines attributes)
	 * 2. value("I", "am")
	 * 3. part of speech(noun, verb)
	 * 4. 
	 */
	public static ArrayList<ArrayList<String>> compare(Neuron n, Neuron n0){
		ArrayList<ArrayList<String>> changes = new ArrayList<ArrayList<String>>();
		ArrayList<String> sim = new ArrayList<String>();
		ArrayList<String> diff = new ArrayList<String>();
		for(String s:n.getDescription()){
			for(String x: n0.getDescription())
				if(s.toLowerCase().equals(x.toLowerCase()))
					sim.add(s);
				else{
					diff.add(s);
					diff.add(x);
				}
						
		}
		for(int i = diff.size()-1; i>=0;i--)
			if(sim.contains(diff.get(i)))
				diff.remove(i);
		diff = Neuron.clean(diff);
		sim = Neuron.clean(sim);
		changes.add(sim);
		changes.add(diff);		
		return changes;
	}
	
	public static ArrayList<String> clean(ArrayList<String> s){
		for(int i=0;i<s.size();i++)
			while(s.lastIndexOf(s.get(i))>i)
				s.remove(s.lastIndexOf(s.get(i)));
		return s;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((Neuron)obj).name.equals(name);
	}

}
