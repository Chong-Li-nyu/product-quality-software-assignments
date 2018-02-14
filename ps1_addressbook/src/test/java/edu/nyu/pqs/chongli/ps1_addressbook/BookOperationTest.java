package edu.nyu.pqs.chongli.ps1_addressbook;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class BookOperationTest extends TestCase{
  public static void main(String[] args) throws Exception {
    AddressBook<AddressEntryV1> book = new AddressBook<AddressEntryV1>();
    AddressEntryV1 entry = new AddressEntryV1.Builder("John", "john@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    AddressEntryV1 entry2 = new AddressEntryV1.Builder("Watson", "Watson@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    book.addEntry(entry);
    System.out.println(book.toString());
    System.out.println("-----------");
    book.addEntry(entry2);
    System.out.println(book.toString());
    Map<String,String> kvs = new HashMap<String,String>();
    kvs.put("name","Watson");
    kvs.put("email","Watson@gmail.com");
    AddressEntryV1 foundEntry = book.searchEntry(kvs);
    assertEquals(foundEntry, entry2);
    book.removeEntry(foundEntry);
    assertEquals(book.addressBook.size(), 1);
    foundEntry.serialize();
  }
}
