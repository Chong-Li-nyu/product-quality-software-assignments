package edu.nyu.pqs.chongli.ps1_addressbook;
import java.lang.reflect.Type;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
* An abstract class of address entry, apply Builder pattern to create an instance 
*/

public abstract class AddressEntryBaseClass{
  final static Gson gson = new Gson();
  private Integer id = null;
  private String name;
  private String email;
  private String address;
  int getId() {
    return id;
  }
  void setId(int id) {
    this.id = id;
  }
  String getName() {
    return name;
  }

  String getEmail() {
    return email;
  }

  String getAddress() {
    return address;
  }

  abstract static class Builder<T extends Builder<T>>{
    String name;
    String email;
    String address;
    protected abstract T setName(String name) throws NullPointerException;
    protected abstract T setEmail(String email) throws Exception;
    protected abstract T setAddress(String address) throws NullPointerException;
    abstract AddressEntryBaseClass build();
    protected abstract T self();
  }
//  protected abstract String serialize();
  protected String serialize(){
    return gson.toJson(this);
  }
  
  /**
  * Deserialize from json to object of Class T
  */
  public static <T extends AddressEntryBaseClass> T deserializeToObject(String json, Class<T> classOfT) {
    T entry = gson.fromJson(json, (Type) classOfT);
    return entry;
  }
  /**
  * Deserialize from json to a Map, retrieving the object's members as a string.
  */
  public static Map<String, String> deserializeToMap(String json){
    Type typeOfMap =  new TypeToken<Map<String, String>>() {}.getType();
    Map<String, String> ret = gson.fromJson(json, typeOfMap);
    return ret;
  }
  AddressEntryBaseClass(Builder<?> builder){
    this.name = builder.name;
    this.email = builder.email;
    this.address = builder.address;
  }
  /**
  * If two entry's serilization results are equal, then returns true, otherwise false
  */
  public <T extends AddressEntryBaseClass> boolean equals(T entry2) {
    String json = this.serialize();
    String json2 = entry2.serialize();
    return json.equals(json2);
  }
}