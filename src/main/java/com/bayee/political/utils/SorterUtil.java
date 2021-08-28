/**
 * 
 */
package com.bayee.political.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author seanguo
 *
 */
public class SorterUtil {
	 
	public ArrayList sortList(ArrayList objList) {
		 Collections.sort(objList, comparator);
		 return objList;
	}

	Comparator comparator = new Comparator() {
		@Override
		public int compare(Object object1, Object o2)
		{
			Date o1Date = getDateFromMethod(object1);
			Date o2Date = getDateFromMethod(o2);
			if (o1Date != null && o2Date != null)
			{
				if (o1Date.after(o2Date))
					return -1;
				else if (o1Date.before(o2Date))
					return 1;
				else
					return 0;
			}
			else
				return -1;
		}
	};

	public Date getDateFromMethod(Object obj) {
		Date date = null;
		String getDate = "getDate";
		try
		{
			Method method = obj.getClass().getMethod(getDate, (Class[]) null);
			if (method != null)
				date = (Date) method.invoke(obj, (Object[]) null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Date getDateFromProperty(Object obj) {
		Date date = null;
		try
		{
			Field dateField = obj.getClass().getField("date");
			if (dateField != null)
				date = (Date) dateField.get(obj);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return date;
	}
}
