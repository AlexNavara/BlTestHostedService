package com.hsbackendlesstest;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoPoint;
import com.backendless.geo.Units;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.backendless.servercode.BackendlessConfig;
import com.backendless.servercode.IBackendlessService;
import com.hsbackendlesstest.helpers.Helper;
import com.hsbackendlesstest.models.*;
import sun.java2d.pipe.SpanShapeRenderer;

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
  // configurable section
  @BackendlessConfig( order = 1,
          displayName = "STRING",
          required = true,
          tooltip = "String configuration item" )
  public String strConfItem = "Hello!";

  @BackendlessConfig( order = 3, required = true, tooltip = "Integer configuration item", displayName = "INTEGER" )
  public int intConfigurationItem;

  @BackendlessConfig( required = true, tooltip = "Date configuration item", displayName = "DATE", order = 2)
  Date dateConfItem;

  @BackendlessConfig( required = true, tooltip = "Boolean configuration item", displayName = "BOOLEAN", order = 0 )
  boolean boolConfItem;

  @BackendlessConfig( options = {"First", "Next", "Last"},
          tooltip = "Choice configuration item",
          displayName = "CHOICE",
          required = true )
  String choiceConfItem;

  @BackendlessConfig
  String itemWithoutProperties;

  public String getStrConfItem()
  {
    return strConfItem;
  }

  public int getIntConfigurationItem()
  {
    return intConfigurationItem;
  }

  public Date getDateConfItem()
  {
    return dateConfItem;
  }

  public boolean isBoolConfItem()
  {
    return boolConfItem;
  }

  public String getChoiceConfItem()
  {
    return choiceConfItem;
  }

  public String getItemWithoutProperties()
  {
    return itemWithoutProperties;
  }



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
    return booleanValue;
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

  public void doSleep( long delay ) throws InterruptedException
  {
    Thread.sleep( delay );
  }



  // complex section
  public BackendlessUser getFirstUser()
  {
    return Backendless.Data.of( BackendlessUser.class ).findFirst();
  }

  public BackendlessUser loginUser( final String login, final String password )
  {
    return loginUser( login, password, false );
  }

  public BackendlessUser loginUser( final String login, final String password, final Boolean stayLoggedIn )
  {
    return Backendless.UserService.login( login, password, stayLoggedIn );
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
  public Map<String,Integer> loadData( int saveQuantity, int findQuantity, int findByIdQuantity, int deleteQuantity ) throws Exception
  {
    if ( deleteQuantity > 0 && findQuantity == 0 )
      throw new Exception( "Cannot invoke deletion without find" );

    if ( findByIdQuantity > 0 && findQuantity == 0 )
      throw new Exception( "Cannot invoke findById without find" );

    Map<String,Integer> result = new HashMap<>();
    result.put( "saved", 0 );
    result.put( "found", 0 );
    result.put( "foundById", 0 );
    result.put( "deleted", 0 );

    String whereClause = "objectId LIKE '%A%'";
    QueryOptions options = new QueryOptions();
    options.setRelationsDepth( 10 );
    options.setPageSize( 100 );
    options.setOffset( 0 );
    BackendlessDataQuery query = new BackendlessDataQuery( whereClause );
    query.setQueryOptions( options );
    BackendlessCollection<SimpleEntity> collection = new BackendlessCollection<>();

    for ( int i = 0; i < saveQuantity; i++ )
    {
      Backendless.Data.of( SimpleEntity.class ).save( Helper.createSimpleEntity() );
      result.put( "saved", result.get( "saved" ) + 1 );
    }

    for ( int i = 0; i < findQuantity; i++ )
    {
      collection = Backendless.Data.of( SimpleEntity.class ).find( query );
      result.put( "found", result.get( "found" ) + 1 );
    }

    if ( collection.getTotalObjects() == 0 )
      throw new Exception( "FindById quantity cannot be called on empty collection" );

    for ( int i = 0; i < findByIdQuantity; i++ )
    {
      String id = collection.getCurrentPage().get( new Random().nextInt( collection.getData().size() ) ).getObjectId();
      Backendless.Data.of( SimpleEntity.class ).findById( id );

      result.put( "foundById", result.get( "foundById" ) + 1 );
    }

    if ( deleteQuantity > collection.getCurrentPage().size() )
      throw new Exception( "DeleteQuantity exceeds current page size" );

    Iterator iterator = collection.getCurrentPage().iterator();

    for ( int i = 0; i < deleteQuantity; i++ )
    {
      Backendless.Data.of( SimpleEntity.class ).remove( (SimpleEntity) iterator.next() );
      result.put( "deleted", result.get( "deleted" ) + 1 );
    }

    return result;
  }
}
