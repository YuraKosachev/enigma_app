package Extensions;


//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

//public class JsonConverter {
//    public static <T> T deserialize(String json, Class<T> genericClass ) {
//        if (json.isEmpty())
//            return null;
//        try {
//            ObjectMapper<T> mapper = new ObjectMapper();
//            T obj = mapper.readValue(json, genericClass);
//            return obj;
//        } catch (JsonProcessingException ex) {
//            return null;
//        }
//    }
//}

// Importing required classes
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// Defining constants for json parsers
enum CONSTANTS {

    CURLY_OPEN_BRACKETS('{'),
    CURLY_CLOSE_BRACKETS('}'),
    SQUARE_OPEN_BRACKETS('['),
    SQUARE_CLOSE_BRACKETS(']'),
    COLON(':'),
    COMMA(','),
    SPECIAL('|');

    private final char constant;

    // Constructor
    CONSTANTS(char constant) { this.constant = constant; }

    // Method
    // Overriding exiting toString() method
    @Override public String toString()
    {
        return String.valueOf(constant);
    }
}

// Class 1
// To parse json object
public class JsonConverter {

    private final static char specialChar;
    private final static char commaChar;
    private HashMap<String, String> objects;

    static
    {
        specialChar = String.valueOf(CONSTANTS.SPECIAL)
                .toCharArray()[0];
        commaChar = String.valueOf(CONSTANTS.COMMA)
                .toCharArray()[0];
    }

    // Constructor if this class
    public JsonConverter(String arg) { getJSONObjects(arg); }

    // Method 1
    // Storing json objects as key value pair in hash map
    public void getJSONObjects(String arg)
    {

        objects = new HashMap<String, String>();

        if (arg.startsWith(String.valueOf(
                CONSTANTS.CURLY_OPEN_BRACKETS))
                && arg.endsWith(String.valueOf(
                CONSTANTS.CURLY_CLOSE_BRACKETS))) {

            StringBuilder builder = new StringBuilder(arg);
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);
            builder = replaceCOMMA(builder);

            for (String objects : builder.toString().split(
                    String.valueOf(CONSTANTS.COMMA))) {

                String[] objectValue = objects.split(
                        String.valueOf(CONSTANTS.COLON), 2);

                if (objectValue.length == 2)
                    this.objects.put(
                            objectValue[0]
                                    .replace("'", "")
                                    .replace("\"", ""),
                            objectValue[1]
                                    .replace("'", "")
                                    .replace("\"", ""));
            }
        }
    }

    // Method 2
    public StringBuilder replaceCOMMA(StringBuilder arg)
    {

        boolean isJsonArray = false;

        for (int i = 0; i < arg.length(); i++) {
            char a = arg.charAt(i);

            if (isJsonArray) {

                if (String.valueOf(a).compareTo(
                        String.valueOf(CONSTANTS.COMMA))
                        == 0) {
                    arg.setCharAt(i, specialChar);
                }
            }

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.SQUARE_OPEN_BRACKETS))
                    == 0)
                isJsonArray = true;
            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.SQUARE_CLOSE_BRACKETS))
                    == 0)
                isJsonArray = false;
        }

        return arg;
    }

    // Method 3
    // Getting json object value by key from hash map
    public String getValue(String key)
    {
        if (objects != null) {
            return objects.get(key).replace(specialChar,
                    commaChar);
        }
        return null;
    }

    // Method 4
    // Getting json array by key from hash map
    public JSONArray getJSONArray(String key)
    {
        if (objects != null)
            return new JSONArray(
                    objects.get(key).replace('|', ','));
        return null;
    }
}

// Class 2
// To parse json array
class JSONArray {

    private final static char specialChar;
    private final static char commaChar;

    private ArrayList<String> objects;

    static
    {
        specialChar = String.valueOf(CONSTANTS.SPECIAL)
                .toCharArray()[0];
        commaChar = String.valueOf(CONSTANTS.COMMA)
                .toCharArray()[0];
    }

    // Constructor of this class
    public JSONArray(String arg) { getJSONObjects(arg); }

    // Method 1
    // Storing json objects in array list
    public void getJSONObjects(String arg)
    {

        objects = new ArrayList<String>();

        if (arg.startsWith(String.valueOf(
                CONSTANTS.SQUARE_OPEN_BRACKETS))
                && arg.endsWith(String.valueOf(
                CONSTANTS.SQUARE_CLOSE_BRACKETS))) {

            StringBuilder builder = new StringBuilder(arg);

            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);

            builder = replaceCOMMA(builder);

            // Adding all elements
            // using addAll() method of Collections class
            Collections.addAll(
                    objects,
                    builder.toString().split(
                            String.valueOf(CONSTANTS.COMMA)));
        }
    }

    // Method 2
    public StringBuilder replaceCOMMA(StringBuilder arg)
    {
        boolean isArray = false;

        for (int i = 0; i < arg.length(); i++) {
            char a = arg.charAt(i);
            if (isArray) {

                if (String.valueOf(a).compareTo(
                        String.valueOf(CONSTANTS.COMMA))
                        == 0) {
                    arg.setCharAt(i, specialChar);
                }
            }

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.CURLY_OPEN_BRACKETS))
                    == 0)
                isArray = true;

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.CURLY_CLOSE_BRACKETS))
                    == 0)
                isArray = false;
        }

        return arg;
    }

    // Method  3
    // Getting json object by index from array list
    public String getObject(int index)
    {
        if (objects != null) {
            return objects.get(index).replace(specialChar,
                    commaChar);
        }

        return null;
    }

    // Method 4
    // Getting json object from array list
    public JsonConverter getJSONObject(int index)
    {

        if (objects != null) {
            return new JsonConverter(
                    objects.get(index).replace('|', ','));
        }

        return null;
    }
}
