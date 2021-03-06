package com.isolutions4u.hibernate.mapping.eager.vs.lazy;

import com.isolutions4u.hibernate.entity.Course;
import com.isolutions4u.hibernate.entity.Instructor;
import com.isolutions4u.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchJoin {

    public static void main(String[] args) {
        // todo create session factory

        SessionFactory factory = new
                Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();


        //todo create session

        Session session = factory.getCurrentSession();


        try {
            //todo start transaction
            session.beginTransaction();

            // TODO : get the instructor for db

            int id = 5;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("ISolutions4u : Instructor : " + instructor);

            //TODO : get course for the instructor
            System.out.println("ISolutions4u : Courses : " + instructor.getCourses());


            //todo commit transaction
            session.getTransaction().commit();


            System.out.println("ISolutions4u : Session Is Close !!");

            System.out.println("ISolutions4u : Courses : " + instructor.getCourses());

            System.out.println("ISolutions4u : Done!");
        } finally {
            factory.close();
            if (session.isOpen()) {
                session.close();
            }
        }
    }
}
