package com.hsbackendlesstest.models;

import com.backendless.Backendless;
import com.backendless.geo.GeoPoint;

public class Address
{
  private String objectId;
  private GeoPoint location;
  private java.util.Date created;
  private java.util.Date updated;
  private String address;
  private String ownerId;

  public String getObjectId()
  {
    return this.objectId;
  }

  public GeoPoint getLocation()
  {
    return this.location;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setLocation( GeoPoint location )
  {
    this.location = location;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public Address save()
  {
    return Backendless.Data.of( Address.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( Address.class ).remove( this );
  }

  public static Address findById( String id )
  {
    return Backendless.Data.of( Address.class ).findById( id );
  }

  public static Address findFirst()
  {
    return Backendless.Data.of( Address.class ).findFirst();
  }

  public static Address findLast()
  {
    return Backendless.Data.of( Address.class ).findLast();
  }
}