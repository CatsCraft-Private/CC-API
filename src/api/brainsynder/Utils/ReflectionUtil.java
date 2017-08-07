/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package api.brainsynder.Utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import simple.brainsynder.utils.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static int getVersionInt() {
        String vString = Reflection.getVersion().replace("v", "");
        if (!vString.isEmpty()) {
            String[] array = vString.split("_");
            return Integer.parseInt(array[0] + array[1]);
        }
        return 18;
    }

    public static <T> T initiateClass(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    public static <T> T initiateClass(Constructor<?> constructor, Object... args) {
        try {
            return (T) constructor.newInstance(args);
        } catch (Exception e) {
        }
        return null;
    }

    public static Constructor<?> fillConstructor(Class<?> clazz, Class<?>... values) {
        try {
            return clazz.getDeclaredConstructor(values);
        } catch (Exception e) {
        }
        return null;
    }

    public static Class<?> getNmsClass(String name) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("net.minecraft.server." + getVersion() + '.' + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Class<?> getPetNMSClass(String name) {
        try {
            return Class.forName("net.magnus498.entity." + getVersion() + ".list." + name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getPetNMS(String name) {
        try {
            return Class.forName("net.magnus498.entity." + getVersion() + '.' + name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static boolean isVersion(String version) {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23).equals(version);
    }

    public static Object getEntityHandle(Entity entity) {
        return invokeMethod(getMethod(getCBCClass("entity.CraftEntity"), "getHandle"), entity);
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static Object getPrivateStatic(Class<?> clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    public static Object getFieldValue(Field field, Object instance) {
        try {
            return field.get(instance);
        } catch (IllegalAccessException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static Object invoke(Method method, Object instance, Object... parameters) {
        if (method == null) {
            return null;
        } else {
            try {
                return method.invoke(instance, parameters);
            } catch (InvocationTargetException | IllegalAccessException var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static <T> T getWorldHandle(World world) {
        return invokeMethod(getMethod(getCBCClass("CraftWorld"), "getHandle"), world);
    }

    public static Class getClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Class getCBCClass(String className) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + getVersion() + '.' + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class getVersionedClass(String className) {
        try {
            return Class.forName("net.magnus498.entity." + getVersion() + '.' + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + getVersion() + '.' + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... params) {
        try {
            return clazz.getDeclaredMethod(methodName, params);
        } catch (NoSuchMethodException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static <T> T invokeMethod(Method method, Object instance, Object... args) {
        try {
            return (T) method.invoke(instance, args);
        } catch (IllegalAccessException var4) {
            return null;
        } catch (InvocationTargetException var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
