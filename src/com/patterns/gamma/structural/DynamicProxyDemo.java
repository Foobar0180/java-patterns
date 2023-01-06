package com.patterns.gamma.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface PlayableCharacter {
    void walk();
    void talk();
}

class HumanPlayer implements PlayableCharacter {
    @Override
    public void walk() {
        System.out.println("The player is walking");
    }

    @Override
    public void talk() {
        System.out.println("The player is talking");
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    private Map<String, Integer> numberOfCalls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        if (name.contains("toString")) {
            return numberOfCalls.toString();
        }

        numberOfCalls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

class DynamicProxyDemo {
    @SuppressWarnings("Ignored Unchecked Cast Warning: withLogging")
    public static <T> T withLogging(T target, Class<T> intf) {
        return (T) Proxy.newProxyInstance(
                intf.getClassLoader(),
                new Class<?>[] { intf },
                new LoggingHandler(target)
        );
    }


    public static void main(String[] args) {
        HumanPlayer player = new HumanPlayer();
        PlayableCharacter logged = withLogging(player, PlayableCharacter.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}
