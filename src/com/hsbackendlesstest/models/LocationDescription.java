package com.hsbackendlesstest.models;

import com.backendless.Backendless;

public class LocationDescription
{
  private java.util.Date created;
  private String description;
  private String ownerId;
  private String objectId;
  private java.util.Date updated;

  public java.util.Date getCreated()
  {
    return this.created;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public String getObjectId()
  {
    return this.objectId;
  }

  public java.util.Date getUpdated()
  {
    return this.updated;
  }


  public void setCreated( java.util.Date created )
  {
    this.created = created;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public void setOwnerId( String ownerId )
  {
    this.ownerId = ownerId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public void setUpdated( java.util.Date updated )
  {
    this.updated = updated;
  }

  public LocationDescription save()
  {
    return Backendless.Data.of( LocationDescription.class ).save( this );
  }

  public Long remove()
  {
    return Backendless.Data.of( LocationDescription.class ).remove( this );
  }

  public static LocationDescription findById( String id )
  {
    return Backendless.Data.of( LocationDescription.class ).findById( id );
  }

  public static LocationDescription findFirst()
  {
    return Backendless.Data.of( LocationDescription.class ).findFirst();
  }

  public static LocationDescription findLast()
  {
    return Backendless.Data.of( LocationDescription.class ).findLast();
  }
}