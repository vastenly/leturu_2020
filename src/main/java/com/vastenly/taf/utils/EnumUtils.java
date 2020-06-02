package com.vastenly.taf.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumUtils extends org.apache.commons.lang3.EnumUtils {
	
	/**
	 * Getting enum value for provided enum by their enum attribute
	 * @param enumClass
	 * @param enumAttr
	 * @return enumValue
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E getValueBy(Class<E> enumClass, Object enumAttr) {
		for (Enum<E> item : enumClass.getEnumConstants()) {
			for (Method method : item.getDeclaringClass().getDeclaredMethods()) {
				try {
					if (method.invoke(item).equals(enumAttr))
						return (E) item;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					/*e.printStackTrace();*/
					continue;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		throw new IllegalArgumentException("Illegal <" +enumClass.getSimpleName()+ "> enum attribute value provided!");
	}

}
