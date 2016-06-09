/**
 * 
 */
package jpaexample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author root
 *
 */
public class Main {
	public static void main(String[] args){
		// open a database connection
		// create a new database if it not exist yet
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
		EntityManager em = emf.createEntityManager();
		
		//store 1000 point objects in the database
		em.getTransaction().begin();
		for (int i = 0; i < 1000; i++) {
			Point p = new Point(i, i);
			em.persist(p);
		}
		em.getTransaction().commit();
		
		// find the number of point objects in the database:
		Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
	    System.out.println("Total Points: " + q1.getSingleResult());
	    
	    // find the number of points in the database:
	    Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
	    System.out.println("Average X: "+ q2.getSingleResult());
	    
	    // retrieve all the point objects from the database:
	    TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
	    
	    List<Point> results = query.getResultList();
	    for(Point p: results){
	    	System.out.println(p);
	    }
	    
	    // close the database connection:
	    em.close();
	    emf.close();
		
	}

}
