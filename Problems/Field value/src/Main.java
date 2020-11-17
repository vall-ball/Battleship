
/**
 * Get value for a given public field or null if the field does not exist or is not accessible.
 */
class FieldGetter {

    public Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {
        Object answer = null;
        try {
            answer = object.getClass().getField(fieldName).get(object);
        } catch (NoSuchFieldException e) {
            return null;
        }

        return answer;


    }

}