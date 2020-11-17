import java.lang.reflect.Method;

class MethodFinder {

    public static String findMethod(String methodName, String[] classNames) throws ClassNotFoundException {
        Class[] classes = new Class[classNames.length];
        Class clazz = String.class.getSuperclass();
        for (int i = 0; i < classes.length; i++) {
            classes[i] = Class.forName(classNames[i]);
        }

        for (Class c : classes) {
            for (Method m : c.getMethods()) {
                if (m.getName().equals(methodName)) {
                    return c.getName();
                }
            }
        }
        return null;
    }
}