/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import datamodel.MyPokemonBohn;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<MyPokemonBohn> listPokemon() {
      List<MyPokemonBohn> resultList = new ArrayList<MyPokemonBohn>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM MyPokemonBohn").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
        	 MyPokemonBohn pokemon = (MyPokemonBohn) iterator.next();
            resultList.add(pokemon);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<MyPokemonBohn> listPokemon(String keyword) {
      List<MyPokemonBohn> resultList = new ArrayList<MyPokemonBohn>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((MyPokemonBohn)session.get(MyPokemonBohn.class, 1)); // use "get" to fetch data
         List<?> pokemon = session.createQuery("FROM MyPokemonBohn").list();
         for (Iterator<?> iterator = pokemon.iterator(); iterator.hasNext();) {
        	 MyPokemonBohn pokemons = (MyPokemonBohn) iterator.next();
            if (pokemons.getname().startsWith(keyword)) {
               resultList.add(pokemons);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createPokemon(String name, String type, String weight) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new MyPokemonBohn(name, type, weight));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
}
