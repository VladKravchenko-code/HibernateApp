package ru.vlad.sprengcourse;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.vlad.sprengcourse.model.Item;
import ru.vlad.sprengcourse.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);

            Item item = session.get(Item.class, 2);
            item.getOwner().getItems().remove(item);

            item.setOwner(person);
            person.getItems().add(item);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

