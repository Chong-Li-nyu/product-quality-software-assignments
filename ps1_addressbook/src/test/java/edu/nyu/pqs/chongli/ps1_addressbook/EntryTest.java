package edu.nyu.pqs.chongli.ps1_addressbook;

import junit.framework.TestCase;
import edu.nyu.pqs.chongli.ps1_addressbook.AddressEntryV1;

public class EntryTest extends TestCase{
  public static void main(String[] args) throws Exception {
    AddressEntryV1 entry = new AddressEntryV1.Builder("John", "john@gmail.com", "washington street").setPhone("222-123-4567").build();
    TestCase.assertEquals(entry.getName(), "John");
    TestCase.assertEquals(entry.getEmail(), "john@gmail.com");
    TestCase.assertEquals(entry.getAddress(), "washington street");
    TestCase.assertEquals(entry.getPhone(), "222-123-4567");
    TestCase.assertNull(entry.getNote());
    System.out.println("passed");
  }
}
