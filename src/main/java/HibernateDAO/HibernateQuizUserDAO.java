package HibernateDAO;

import model.Quiz;
import model.QuizHistory;
import model.QuizUser;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernatUtil;

import java.util.ArrayList;
import java.util.List;


public class HibernateQuizUserDAO {
    private HibernatUtil hibernatUtil;

    public HibernateQuizUserDAO(){
         hibernatUtil = new HibernatUtil();

    }
    public List<QuizUser> getQuizUsers(int param1){
        Session session = hibernatUtil.getSessionFactory().openSession();
        String q = "from QuizUser order by id";
        Query query = session.createQuery(q);
        query.setFirstResult((param1-1)*10);
        query.setMaxResults(10);
        List<QuizUser> list =query.list();
        session.close();
        return list;
    }

    public void mergeQuizUser(QuizUser user){
        Session session = hibernatUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        session.close();
    }
    public QuizUser getUserById(long id){
        Session session = hibernatUtil.getSessionFactory().openSession();
        QuizUser user = (QuizUser) session.get(QuizUser.class,id);
        session.close();
        return user;
    }



    public static void main(String[] args) {
        HibernateQuizUserDAO hibernateQuizUserDAO = new HibernateQuizUserDAO();
        List<QuizUser> list = hibernateQuizUserDAO.getQuizUsers(2);
        QuizUser user = list.get(0);
        System.out.println(user);
//        for (QuizHistory x : list.get(0).getQuizHistory()) {
//            System.out.println(x.getStartTime());
//        }
    }

}
