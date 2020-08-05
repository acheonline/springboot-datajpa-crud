package ru.achernyavskiy0n.springboot.util;

import org.hibernate.Hibernate;

/**
 * 02.08.2020
 *
 * @author a.chernyavskiy0n
 */
public class Util {
    @SuppressWarnings("unchecked")
    public static <T> T unproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }
        return (T) Hibernate.unproxy(entity);
    }
}
