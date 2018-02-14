package edu.nyu.pqs.chongli.ps1_addressbook;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

import com.google.gson.reflect.TypeToken;

public class AddressEntryV1 extends AddressEntryBaseClass{
  private String phone;
  private String note;
  String getPhone() {
    return phone;
  }
  String getNote() {
    return note;
  }
  /**
  * A builder used to create a new AddressEntryV1 instance by invoking its build method
  */
  public static class Builder extends AddressEntryBaseClass.Builder<Builder>{
    private String phone = null;
    private String note = null;
    public Builder(String name, String email, String address) throws Exception{
      setName(name);
      setEmail(email);
      setAddress(address);
      
    }
    @Override
    protected Builder setName(String name) throws NullPointerException{
      this.name = Objects.requireNonNull(name);
      return this;
    }
    @Override
    protected Builder setEmail(String email) throws Exception{
      Objects.requireNonNull(email);
      if(!email.contains("@")) 
        throw new Exception(String.format("Invalid email address: %s", email));
      this.email = email;
      return this;
    }
    @Override
    protected Builder setAddress(String address) throws NullPointerException{
      this.address = Objects.requireNonNull(address);
      return this;
    }   
    
    public Builder setPhone(String phone) {
      this.phone = phone;
      return this;
    }
    public Builder setNote(String note) {
      this.note = note;
      return this;
    }  
    @Override
    AddressEntryV1 build() {
      return new AddressEntryV1(this);
    }
    @Override 
    protected Builder self() {
      return this;
    }
  }

  private AddressEntryV1(Builder builder) {
    super(builder);
    this.phone = builder.phone;
    this.note = builder.note;
  }

}
