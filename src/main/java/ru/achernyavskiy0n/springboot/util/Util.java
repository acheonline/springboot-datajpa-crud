package ru.achernyavskiy0n.springboot.util;

import org.hibernate.Hibernate;

/**
 * 02.08.2020
 *
 * @author a.chernyavskiy0n
 */
public class Util {

    public static <T> T unproxy(T t) {
        return (T) Hibernate.unproxy(t);
    }
}
