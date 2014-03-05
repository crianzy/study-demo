package com.app.utils.util;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("all")
public class ComparatorMap implements Comparator{
	public int compare(Object arg0, Object arg1) {
		   Map obj0=(Map)arg0;
		   Map obj1=(Map)arg1;
		  return Integer.parseInt(obj0.get("sort").toString())- Integer.parseInt(obj1.get("sort").toString()) ; 
		  }
}
