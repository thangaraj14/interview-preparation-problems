package reflections.customMockito;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class OurMockito implements InvocationHandler {
    private static Map<String, Object> stubMap = new HashMap<>();
    private static Map<String, Exception> excepMap = new
            HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        String methodName = method.getName();
        if (Modifier.isFinal(method.getModifiers()) ||
                Modifier.isPrivate(method.getModifiers()) ||
                Modifier.isStatic(method.getModifiers())) {
            throw new RuntimeException("You naughty developer mocking a private,static or final method " + methodName);
        }
        if (excepMap.containsKey(methodName)) {
            Exception excep = excepMap.get(methodName);
            throw excep;
        }
        if (stubMap.containsKey(methodName)) {
            return stubMap.get(methodName);
        }
        return null;
    }

    public static Object mock(Class aClass) {
        return Proxy.newProxyInstance
                (OurMockito.class.getClassLoader(), new Class[]{aClass
                }, new OurMockito());
    }

    public static void stub(Object stubOn, String methodName, Object stubbedValue) {
        stubMap.put(methodName, stubbedValue);
    }

    public static void stub(Object stubOn, String methodName,
                            Exception excep) {
        if (excep != null) {
            excepMap.put(methodName, excep);
        }
    }
}