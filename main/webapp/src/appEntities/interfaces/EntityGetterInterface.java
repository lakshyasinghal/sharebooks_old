package com.sharebooks.appEntities.interfaces;


import java.sql.*;
import java.util.*;
import com.sharebooks.appEntities.entities.Entity;


public interface EntityGetterInterface {

	public List<Entity> getEntitiesFromResultSet(String table , ResultSet rs) throws Exception;

	public Entity getEntity(String table , ResultSet rs) throws Exception;
}