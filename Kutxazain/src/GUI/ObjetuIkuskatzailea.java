package GUI;

// Class izena: 	banku_kutxazain.ObjetuIkuskatzailea.java
// Function:	This is a utility class of ObjetuIkuskatzailea which reflects the internals for the toString() 


import java.lang.reflect.*;

public class ObjetuIkuskatzailea
{
	// Reflection  erabiltzen da toStrig generic bat sortzeko
	// Informazio gehiago Core Java 2 -ko 213 orrialdean

public static String toString(Object object) throws Exception
{
	// Deklarazioak
	Class myClass, mySuperClass;
	String className, superClassName, classDescription;
	Field classFields[], field;
	int i;
	Object fieldValue;
	// Hasieraketa
	field = null;
	myClass = object.getClass();
	className = myClass.getName();
	mySuperClass = myClass.getSuperclass();
	superClassName = mySuperClass.getName();
	// String-a sortu
	classDescription = "(Class = " + className;
	// super klasea sartu, egon ezkero
	if (!mySuperClass.equals(Object.class))
	{
		classDescription = classDescription + " (Super Class = " + superClassName;
		classFields = mySuperClass.getDeclaredFields();
		for (i = 0; i < classFields.length; i++)
		{
			field = classFields[i];
			field.setAccessible(true);
			// get  izena
			classDescription += " | ";
			classDescription += field.getName() + " = ";
			// get edukinak
			fieldValue = field.get(object);
			classDescription += fieldValue.toString();
		}
		classDescription += ")";
	}
	// Klasea string bihurtzen jarraitu
	classFields = myClass.getDeclaredFields();
	for (i = 0; i < classFields.length; i++)
	{
		field = classFields[i];
		field.setAccessible(true);
		// get  izena
		classDescription += " | ";
		classDescription += field.getName() + " = ";
		// get edukinak
		fieldValue = field.get(object);
		classDescription += fieldValue.toString();
	}
	classDescription += ")";
	return classDescription;
}
}
