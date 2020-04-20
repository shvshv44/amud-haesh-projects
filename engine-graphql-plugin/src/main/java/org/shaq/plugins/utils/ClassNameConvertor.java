package org.shaq.plugins.utils;

public class ClassNameConvertor {

    public String convertToEnumValueStandart (String name) {
        if (name == null || name.length() == 0)
            return name;

        String enumName = "";
        boolean isPrevCharLowerCase = false;
        for (char currChar : name.toCharArray()) {
            boolean isUpperCase = Character.isUpperCase(currChar);
            if (isUpperCase && isPrevCharLowerCase)
                enumName = enumName + "_" + currChar;
            else
                enumName = enumName + Character.toUpperCase(currChar);

            isPrevCharLowerCase = Character.isLowerCase(currChar);
        }

        return enumName;
    }

    public String convertToFieldStandart(String name) {
        //TODO: rest
        return "";
    }

    public String convertToClassStandart(String name) {
        if (name == null || name.length() == 0)
            return name;

        String className = convertToFieldStandart(name);
        char firstChar = className.toCharArray()[0];
        String restOfTheString = className.substring(1);
        return Character.toUpperCase(firstChar) + restOfTheString;
    }

}
