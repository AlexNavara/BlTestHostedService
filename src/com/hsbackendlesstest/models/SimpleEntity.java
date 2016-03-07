package com.hsbackendlesstest.models;

import com.backendless.Backendless;

public class SimpleEntity
{
  private String ownerId;
  private java.util.Date dateValue;
  private Integer intValue;
  private Boolean booleanValue;
  private String objectId;
  private Double doubleValue;
  private java.util.Date updated;
  private String stringValue;
  private java.util.Date created;

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public java.util.Date getDateValue()
  {
    return this.dateValue;
  }

  public Integer getIntValue()
  {
    return this.intValue;
  }

  public Boolean getBooleanValue()
  {
    return this.booleanValue;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public Double getDoubleValue()
  {
    return this.doubleValue;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }

  public String getStringValue()
  {
    return this.stringValue;
  }

  public java.util.Date getCreated()
  {
    return this.created;
  }


  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setDateValue( java.util.Date dateValue )
  {
    this.dateValue = dateValue;
  }

  public void setIntValue( Integer intValue )
  {
    this.intValue = intValue;
  }

  public void setBooleanValue( Boolean booleanValue )
  {
    this.booleanValue = booleanValue;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setDoubleValue( Double doubleValue )
  {
    this.doubleValue = doubleValue;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public void setStringValue( String stringValue )
  {
    this.stringValue = stringValue;
  }

  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public SimpleEntity save()
  {
    return Backendless.Data.of( SimpleEntity.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( SimpleEntity.class ).remove( this );
  }

  public static SimpleEntity findById( String id )
  {
    return Backendless.Data.of( SimpleEntity.class ).findById( id );
  }

  public static SimpleEntity findFirst()
  {
    return Backendless.Data.of( SimpleEntity.class ).findFirst();
  }

  public static SimpleEntity findLast()
  {
    return Backendless.Data.of( SimpleEntity.class ).findLast();
  }
}