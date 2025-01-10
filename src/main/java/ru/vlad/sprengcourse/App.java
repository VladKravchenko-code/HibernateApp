package ru.vlad.sprengcourse;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.vlad.sprengcourse.model.Director;
import ru.vlad.sprengcourse.model.Movie;

import java.util.List;

public class App {
    private static Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
            .addAnnotatedClass(Movie.class);
    private static SessionFactory sessionFactory = configuration.buildSessionFactory();
    private static Session session = sessionFactory.getCurrentSession();

    public static void main(String[] args) {
        getDirectorAndGetHisMovies();
    }

    public static void getDirectorAndGetHisMovies() {
        try {
            session.beginTransaction();

            Director director = session.get(Director.class, 1);

            System.out.println(director);

            List<Movie> movies = director.getMovies();

            System.out.println(movies);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

