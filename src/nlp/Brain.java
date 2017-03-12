package nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.security.auth.x500.X500Principal;

public class Brain implements Serializable{

	private static final long serialVersionUID = 1L;
	static ArrayList<Rule> patterns;
	static ArrayList<Type> types;
	static ArrayList<Neuron> memories;
	
	public Brain() {
		
		
	}

	public static void main(String[] args) {
		//Brain.wipe();
		//types.add(new Type("Word"));
		
		try{
			memories = (ArrayList<Neuron>)Brain.read();
		} catch (Exception e){
			memories = new ArrayList<Neuron>();
		}
		Scanner in = new Scanner(System.in);
		System.out.println(memories.toString()+"\nword:");
		String x = in.next();
		if(checkCmd(x))
			return;
		else if(memories.contains(new Neuron(x)))
			System.out.println("The "+memories.get(memories.indexOf(new Neuron(x))).getDescription().toString()+"?");

		else{
			
			try{
				Thesaurus t = new Thesaurus(x);
				memories.add(new Neuron(t.getName(),t.getPartOfSpeech()));
			} catch( Exception e){
				System.out.println("What is "+x+"?");
				ArrayList<String> d = new ArrayList<String>();
				String[] s = in.next().split(", ");
				for(String i:s)
					d.add(i);
				memories.add(new Neuron(x,d));
			}
		}
		Brain.write(memories);
	}
	
	public static void write(Object o)
    {
        try
        {
            FileOutputStream file = new FileOutputStream("Memory.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(o);
            out.close();
            file.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
	public static Object read() throws IOException, ClassNotFoundException {
		Object t =null;
            FileInputStream file = new FileInputStream("Memory.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            t = in.readObject();
            in.close();
            file.close();
		return t;
	}
	
	public static void wipe(){
		File f = new File("Memory.ser");
		f.delete();
	}
	public static boolean checkCmd(String s){
		if(s.toLowerCase().equals("/compare")){
			Scanner in = new Scanner(System.in);
			System.out.println("First Word:");
			String s1 = in.next();
			System.out.println("Second Word: ");
			String s2 = in.next();
			ArrayList<ArrayList<String>> a = Neuron.compare(memories.get(memories.indexOf(new Neuron(s1))), memories.get(memories.indexOf(new Neuron(s2))));
			System.out.println("Sim: "+a.get(0).toString());
			System.out.println("Dif: "+a.get(1).toString());
			return true;
		} else if(s.toLowerCase().equals("/define")){
			Scanner in = new Scanner(System.in);
			System.out.println("Word to Define:");
			String s1 = in.next();
			try {
				Thesaurus t = new Thesaurus(s1);
				for(String def: t.getDefinitions())
					System.out.println(def);
			} catch (Exception e) {
				System.out.println("I don't know that word. Sorry.");
			}
			
			return true;
		} else if(s.toLowerCase().equals("/partofspeech")){
			Scanner in = new Scanner(System.in);
			System.out.println("Word to Define:");
			String s1 = in.next();
			try {
				Thesaurus t = new Thesaurus(s1);
				for(String def: t.getPartOfSpeech())
					System.out.println(def);
			} catch (Exception e) {
				System.out.println("I don't know that word. Sorry.");
			}
			
			return true;
		}
		return false;
			
	}
}
