package edu.nyu.pqs.chongli.ps1_addressbook;

import junit.framework.TestCase;
import edu.nyu.pqs.chongli.ps1_addressbook.AddressEntryV1;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class GSONTest extends TestCase{
  public static void main(String[] args) throws Exception {
    AddressEntryV1 entry = new AddressEntryV1.Builder("John", "john@gmail.com", "washington street")
        .setPhone("222-123-4567").build();
    String json = entry.serialize();
    AddressEntryV1 entry2  = AddressEntryBaseClass.deserializeToObject(json, AddressEntryV1.class );
    assertEquals(entry2.serialize(), json);
    Map<String,String> map = AddressEntryBaseClass.deserializeToMap(json);
    assertEquals(map.get("name"), "John");
    assertNull(map.get("note"));
    
  }
}
