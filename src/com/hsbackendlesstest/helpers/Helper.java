package com.hsbackendlesstest.helpers;

import com.hsbackendlesstest.models.SimpleEntity;

import java.util.Date;

/**
 * Created by alex on 1/28/16.
 */
public class Helper
{

  public static SimpleEntity createSimpleEntity( final String stringValue, final int intValue,
                                                 final boolean booleanValue, final Date dateValue,
                                                 final double doubleValue )
  {
    SimpleEntity entity = new SimpleEntity();
    entity.setStringValue( stringValue );
    entity.setIntValue( intValue );
    entity.setDateValue( dateValue );
    entity.setBooleanValue( booleanValue );
    entity.setDoubleValue( doubleValue );
    return entity;
  }

  public static SimpleEntity createSimpleEntity()
  {
    return createSimpleEntity( "default", 42, false, new Date( 0 ), 12345.06789 );
  }
}
