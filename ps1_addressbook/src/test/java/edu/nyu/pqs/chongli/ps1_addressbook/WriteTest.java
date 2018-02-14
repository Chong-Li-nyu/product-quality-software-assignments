

package edu.nyu.pqs.chongli.ps1_addressbook;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import junit.framework.TestCase;

public class WriteTest extends TestCase{
  public static void main(String[] args) throws Exception {
    String path = "./testRW.json";
    
    AddressBook<AddressEntryV1> book = new AddressBook<AddressEntryV1>();
    AddressEntryV1 entry = new AddressEntryV1.Builder("John", "john@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    AddressEntryV1 entry2 = new AddressEntryV1.Builder("Watson", "Watson@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    book.addEntry(entry);
    book.addEntry(entry2);
    try {
      FileWriter fw = new FileWriter(path);
      book.saveToFile(fw);
      fw.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
//    FileReader fr = new FileReader(path);
//    List<AddressEntryV1> res = book.readFromFile(fr);
////    assertEquals(res.size(), 2);
//    String[] resarr = res.stream().map(AddressEntryV1::serialize).toArray(String[]::new);
//    System.out.println(String.join("\n", resarr));
//    
  }
}
