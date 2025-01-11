package ru.vlad.sprengcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.vlad.sprengcourse.model.Director;
import ru.vlad.sprengcourse.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    private static Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
            .addAnnotatedClass(Movie.class);
    private static SessionFactory sessionFactory = configuration.buildSessionFactory();
    private static Session session = sessionFactory.getCurrentSession();

    public static void main(String[] args) {
        deleteFilm();
    }

    public static void deleteFilm() {
        try{
            session.beginTransaction();

            Movie movie = session.get(Movie.class, 1);
            Director director = movie.getDirector();

            director.getMovies().remove(movie);
            session.delete(movie);

            movie.setDirector(null);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static void movieSerDirector() {
        try {
            session.beginTransaction();

            Director director = session.get(Director.class, 8);
            Movie movie = session.get(Movie.class, 1);

            Director director1 = movie.getDirector();
            movie.setDirector(director);
            director1.getMovies().remove(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static void creationNewDirectorAndHisNewFilm() {
        try {
            session.beginTransaction();

            Director director = new Director("James Cameron", 70);
            Movie movie = new Movie("Terminator", 1984);

            movie.setDirector(director);
            director.setMovies(Collections.singletonList(movie));

            session.save(director);
            session.save(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static void makingNewMovie() {
        try {
            session.beginTransaction();

            Director director = session.get(Director.class, 1);
            Movie movie = new Movie("Inglourious Basterds", 2009);

            movie.setDirector(director);
            director.getMovies().add(movie);

            session.save(movie);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static void getMoviesAndGetHisDirector() {
        try {
            session.beginTransaction();

            Movie movie = session.get(Movie.class, 1);

            Director director = movie.getDirector();

            System.out.println(director);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    public static void getDirectorAndGetHisMovies() {
        try {
            session.beginTransaction();

            Director director = session.get(Director.class, 8);

            System.out.println(director);

            List<Movie> movies = director.getMovies();

            System.out.println(movies);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

