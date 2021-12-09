package org.example.youtubeh1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {

    private static final SessionFactory factory = configFactory();

    private static SessionFactory configFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static Session createSession(){
        return factory.openSession();
    }
}
