package com.hsbackendlesstest.models;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class Person
{
  private String objectId;
  private Address address;
  private java.util.Date created;
  private String name;
  private String ownerId;
  private java.util.Date updated;
  private BackendlessUser relatedUser;

  public String getObjectId()
  {
    return this.objectId;
  }

  public Address getAddress()
  {
    return this.address;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getName()
  {
    return this.name;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public BackendlessUser getRelatedUser()
  {
    return this.relatedUser;
  }


  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setAddress( Address address )
  {
    this.address = address;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setRelatedUser( BackendlessUser relatedUser )
  {
    this.relatedUser = relatedUser;
  }

  public Person save()
  {
    return Backendless.Data.of( Person.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( Person.class ).remove( this );
  }

  public static Person findById( String id )
  {
    return Backendless.Data.of( Person.class ).findById( id );
  }

  public static Person findFirst()
  {
    return Backendless.Data.of( Person.class ).findFirst();
  }

  public static Person findLast()
  {
    return Backendless.Data.of( Person.class ).findLast();
  }
}