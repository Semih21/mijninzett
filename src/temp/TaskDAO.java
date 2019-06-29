package com.miijninzet.mijninzetprojectteamdrie.model.repository;


import com.miijninzet.mijninzetprojectteamdrie.model.entity.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TaskDAO {
    private SessionFactory sessionFactory;
    Configuration configuration = new Configuration();

    public TaskDAO(SessionFactory factory) {
        super();
        sessionFactory = factory;
    }

    public List<Task> findAllTasks() {
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "FROM Task";
        Query query = session.createQuery(hql);
        List<Task> results = query.list();
        session.close();
        //System.out.println("dit is wat er opgehaald is uit de database" + results);
        return results;
    }


    public Task getTaakByID(int taskId) {
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Task t WHERE t.taskId=:TASKID";
        Query query = session.createQuery(hql);
        query.setParameter("TASKID", taskId);
        List<Task> all = query.list();
        // if size>0 haal dan de eerste uit de lijst (index 0) else zet Taaktaak = null
        Task taak = (all.size() > 0 ? all.get(0) : null);
        tx.commit();
        session.close();
        return taak;
    }

    public void storeTaak(Task task) {
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(task);
        tx.commit();
        session.close();
    }


    public void storeTaakList(List<Task> tasks){
        sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(Task t: tasks){
            session.save(t);
        }
        tx.commit();
        session.close();
    }



}
