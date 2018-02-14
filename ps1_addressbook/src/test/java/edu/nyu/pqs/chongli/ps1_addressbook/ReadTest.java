package edu.nyu.pqs.chongli.ps1_addressbook;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import junit.framework.TestCase;

public class ReadTest extends TestCase{
  public static void main(String[] args) throws Exception {
    String path = "./testRW.json";
    AddressBook<AddressEntryV1> book = new AddressBook<AddressEntryV1>();
    FileReader fr = new FileReader(path);
    book.readFromFile(fr, AddressEntryV1.class);
    fr.close();
    assertEquals(book.getSize() , 2);
    
    AddressEntryV1 entry2 = new AddressEntryV1.Builder("Watson", "Watson@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    Map<String,String> kvpairs= AddressEntryBaseClass.deserializeToMap(entry2.serialize() ) ; 
    TestCase.assertNotNull(book.searchEntry(kvpairs));
    
    FileReader fr2 = new FileReader(path);
    book.readFromFile(fr2, AddressEntryV1.class);
    fr2.close();
    assertEquals(book.addressBook.size(), 2);
  }
}