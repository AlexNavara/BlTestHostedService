package com.hsbackendlesstest;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.exceptions.BackendlessException;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoPoint;
import com.backendless.geo.Units;
import com.backendless.servercode.IBackendlessService;
import com.hsbackendlesstest.helpers.Helper;
import com.hsbackendlesstest.models.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by alex on 3/3/16.
 */
public class Service implements IBackendlessService
{
  // primitives section
  private int intValue = 24;
  private double doubleValue = 123.456;
  private Date dateValue = new Date( 0 );
  private String stringValue = "default";
  private boolean booleanValue = false;

  public void getVoid()
  {

  }

  public boolean getBool()
  {
    return true;
  }

  public Object getNull()
  {
    return null;
  }

  public int getIntValue()
  {
    return intValue;
  }

  public void setIntValue( int intValue )
  {
    this.intValue = intValue;
  }

  public double getDoubleValue()
  {
    return doubleValue;
  }

  public void setDoubleValue( double doubleValue )
  {
    this.doubleValue = doubleValue;
  }

  public Date getDateValue()
  {
    return dateValue;
  }

  public void setDateValue( Date dateValue )
  {
    this.dateValue = dateValue;
  }

  public String getStringValue()
  {
    return stringValue;
  }

  public void setStringValue( String stringValue )
  {
    this.stringValue = stringValue;
  }

  public boolean isBooleanValue()
  {
    return booleanValue;
  }

  public void setBooleanValue( boolean booleanValue )
  {
    this.booleanValue = booleanValue;
  }

  public TestEntity returnIt( TestEntity it )
  {
    return it;
  }



  // complex section
  public BackendlessUser getFirstUser()
  {
    return Backendless.Data.of( BackendlessUser.class ).findFirst();
  }

  public BackendlessUser createUser( final BackendlessUser user )
  {
    return Backendless.UserService.register( user );
  }

  public GeoPoint getGeoPoint()
  {
    Backendless.Geo.savePoint( new GeoPoint( 0., 0. ) );
    BackendlessGeoQuery query = new BackendlessGeoQuery();
    query.setLatitude( 0. );
    query.setLongitude( 0. );
    query.setRadius( 10. );
    query.setUnits( Units.KILOMETERS );
    return Backendless.Geo.getPoints( query ).getData().get( 0 );
  }

  public GeoPoint createGeoPoint( final GeoPoint point )
  {
    if( point != null )
      return Backendless.Geo.savePoint( point );

    return null;
  }

  public SimpleEntity createDefaultSimpleEntity()
  {
    SimpleEntity entity = Helper.createSimpleEntity();
    return entity.save();
  }

  public SimpleEntity createSimpleEntity( final SimpleEntity entity )
  {
    return entity.save();
  }

  public List<SimpleEntity> getListOfSimpleEntities( final int listLength, final boolean createEntitiesOnServer )
  {
    List<SimpleEntity> result = new ArrayList<>();

    for( int i = 0; i < listLength; i++ )
    {
      SimpleEntity entity = Helper.createSimpleEntity( "default" + i, i, i % 2 == 0, new Date(), 987.014 + i );

      if( createEntitiesOnServer )
        entity = Backendless.Data.save( entity );

      result.add( entity );
    }

    return result;
  }

  public Map<String, SimpleEntity> getMapOfSimpleEntities( final int mapSize, final boolean createEntitiesOnServer )
  {
    Map<String, SimpleEntity> result = new HashMap<>();

    for( int i = 0; i < mapSize; i++ )
    {
      String name = "mapped" + i;
      SimpleEntity entity = Helper.createSimpleEntity( name, i, i % 2 == 0, new Date(), 987.014 + i );

      if( createEntitiesOnServer )
        entity = Backendless.Data.save( entity );

      result.put( name, entity );
    }

    return result;
  }

  public BackendlessCollection<SimpleEntity> getBackendlessCollection()
  {
    return Backendless.Data.of( SimpleEntity.class ).find();
  }

  public Person createAndGetPerson( final Person person, final BackendlessUser relatedUser, final Address address,
                                    final GeoPoint location, final String geoMeta,
                                    final LocationDescription locationDescription )
  {
    person.setAddress( address );
    BackendlessUser savedUser = null;

    try
    {
      savedUser = Backendless.Data.of( BackendlessUser.class ).findById( relatedUser );
    }
    catch( BackendlessException e )
    {
      savedUser = Backendless.Data.of( BackendlessUser.class ).findFirst();
    }

    person.setRelatedUser( savedUser );
    address.setLocation( location );
    location.addMetadata( "stringMeta", geoMeta );
    location.addMetadata( "description", locationDescription );

    return Backendless.Data.of( Person.class ).save( person );
  }

  public void throwExceptionMethod() throws Exception
  {
    throw new Exception( "Test exception" );
  }

  public void loginUserWithInvalidEmail()
  {
    Backendless.UserService.login( "ABC", "DEF" );
  }

  public void createNewThread()
  {
    Thread extra = new Thread( new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          Thread.sleep( 5000 );
        }
        catch( InterruptedException e )
        {
          e.printStackTrace();
        }
      }
    } );

    extra.start();
  }

  public String callExternalHost( final String host ) throws Exception
  {
    URL url = new URL( host );
    URLConnection connection = url.openConnection();
    BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
    String nextString = null;
    StringBuilder result = new StringBuilder();

    while( (nextString = in.readLine()) != null )
    {
      result.append( nextString );
    }

    in.close();

    return result.toString();
  }

  public SimpleEntity getEntity( final SimpleEntity entity )
  {
    if( entity == null )
      return null;

    return entity;
  }

  // load section
}
