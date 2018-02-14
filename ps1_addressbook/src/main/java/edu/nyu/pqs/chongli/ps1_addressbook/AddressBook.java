package edu.nyu.pqs.chongli.ps1_addressbook;

import edu.nyu.pqs.chongli.ps1_addressbook.AddressEntryBaseClass;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.Gson;


/**
* <h1> AddressBook </h1>
* This class is for storing a list of AdressEntreis. Users can add,remove and search an entry from this book.
* And users can read from a json file, whose non-repetitive address entries will be added to the address book.
* Users can also save the address entries to a json file.
*
* @author  Chong Li
* @version 1.0
*/
public class AddressBook<T extends AddressEntryBaseClass> {
  private int id_generator;
  List<T> addressBook;
  
  public AddressBook() {
    addressBook = new ArrayList<T>();
    id_generator = 0;
  }
  

  public void clear() {
    addressBook.clear();
    id_generator = 0;
  }
  /**
  * Add an entry to the address book if it is not repetitive
  */
  public void addEntry(T entry) {  
    Map<String,String> properties = AddressEntryBaseClass.deserializeToMap(entry.serialize());
    if (this.searchEntry(properties) == null) {
      entry.setId(id_generator++);
      addressBook.add(entry); 
    }
  }
  /**
  * Remove an entry from address book and return it if it is in the book, else return null.
  */
  public T removeEntry(T entry) {
    T res = null;
    for( int i = 0; i<addressBook.size(); i++) {
      if(entry.equals(addressBook.get(i)));
      res = addressBook.remove(i);
      break;
    }
    return res;
  }
  /**
  * Search an entry according to properties, if found, return it, Otherwise return null.
  */
  public T searchEntry(Map<String , String> properties) {
    for(T entry: addressBook) {
      Map<String, String> map = AddressEntryBaseClass.deserializeToMap(entry.serialize());
      int count = 0;
      for(String k: properties.keySet()) {
        if( map.get(k).equals( properties.get(k) ) )
          count++;
        else 
          break;
      }
      if (count == properties.size()) return entry;
    }
    return null;
  }
  /**
   * Return address book's size
   */
  public int getSize() {
    return addressBook.size();
  }
  
  @Override
  public String toString() {
    String[] entries = addressBook.stream().map(T::serialize).toArray(String[]::new);
    String res = String.join("\n", entries);
    return res;
  }
  /**
   * Save address book to a file represented by a FileWriter
   */
  public void saveToFile(FileWriter fw) {
    try {
      BufferedWriter bw = new BufferedWriter(fw);
      for (T entry: this.addressBook) {
        bw.write(entry.serialize());
        bw.newLine();
      }
      bw.flush();
      bw.close();
    }catch(Exception e) {}
    System.out.println("save to file success");
  }
  /**
   * read from a file(represented by a FileReader), whose non-repetitive address entries will be added to the address book.
   */
  public void readFromFile(FileReader fr, Class<T> classOfT) {
    Gson gson = new Gson();
    BufferedReader br = new BufferedReader(fr);
    String json = null;
    List<T> res = new ArrayList<>();
    try {
      while( (json=br.readLine()) !=null) {
        T entry = gson.fromJson(json, (Type) classOfT);
        res.add(entry);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    for(T entry: res) {
      Map<String,String> properties = AddressEntryBaseClass.deserializeToMap(entry.serialize());
      if (this.searchEntry(properties) == null) {
        this.addEntry(entry);
      }
    }
  }
}
