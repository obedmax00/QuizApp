package HibernateDAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Quiz;
import model.QuizHistory;
import model.QuizUser;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import util.HibernatUtil;

import javax.persistence.GeneratedValue;
import java.util.List;

public class HibernateQuizHistoryDAO {
    private HibernatUtil hibernatUtil;

    public HibernateQuizHistoryDAO(){
        hibernatUtil = new HibernatUtil();

    }

    public List<QuizHistory> getHistoryOrderedByDate(int param1){
        Session session = hibernatUtil.getSessionFactory().openSession();
        String q = "from QuizHistory history left join fetch history.quizUser User left join fetch history.quiz quiz order by history.id desc";
        Query query = session.createQuery(q);
        query.setFirstResult((param1-1)*10);
        query.setMaxResults(10);
        List<QuizHistory> list =query.list();
        session.close();
        return list;
    }

    public List<QuizHistory> getHistoryOrderedByName(int param1){
        Session session = hibernatUtil.getSessionFactory().openSession();
        String q = "from QuizHistory history left join fetch history.quizUser user left join fetch history.quiz order by user.firstName asc ,user.lastName asc";
        Query query = session.createQuery(q);
        query.setFirstResult((param1-1)*10);
        query.setMaxResults(10);
        List<QuizHistory> list =query.list();
        session.close();
        return list;
    }

    public List<QuizHistory> getHistoryOrderedByQuiz(int param1){
        Session session = hibernatUtil.getSessionFactory().openSession();
        String q = "from QuizHistory history left join fetch history.quizUser user left join fetch history.quiz quiz order by quiz.name asc";
        Query query = session.createQuery(q);
        query.setFirstResult((param1-1)*10);
        query.setMaxResults(10);
        List<QuizHistory> list =query.list();
        session.close();
        return list;
    }

    public QuizHistory getHistoryandRelationById(long id){
        Session session = hibernatUtil.getSessionFactory().openSession();
        String q = "from QuizHistory history left join fetch history.historyAnswers answer left join fetch answer.questionPool question left join fetch question.choices left join fetch history.quiz where history.id = :id";
        Query query = session.createQuery(q);
        query.setParameter("id",id);
        QuizHistory history = (QuizHistory) query.getSingleResult();
        session.close();
        return history;
    }



    public List<QuizHistory> getHistoryByNameRegex(String regex) {
        Session session = hibernatUtil.getSessionFactory().openSession();
        String query = "from QuizHistory h left join fetch h.quizUser u where concat(u.firstName,' ',u.lastName) like :cr";
        Query q = session.createQuery(query);
        q.setParameter("cr","%"+regex+"%");
        List list = q.list();
        return list;
    }

    public List<QuizHistory> getHistoryByCatRegex(String regex) {
        Session session = hibernatUtil.getSessionFactory().openSession();
        String query = "from QuizHistory h left join fetch h.quiz q where q.name like :cr";
        Query q = session.createQuery(query);
        q.setParameter("cr","%"+regex+"%");
        List list = q.list();
        return list;
    }

    public List<QuizHistory> getHistoryByQuizCat(String value, int count) {
        Session session = hibernatUtil.getSessionFactory().openSession();
        String query = "from QuizHistory h left join fetch h.quiz q left join fetch h.quizUser where q.name = :value";
        Query q = session.createQuery(query);
        q.setFirstResult((count-1)*10);
        q.setMaxResults(10);
        q.setParameter("value",value);
        List list = q.list();
        return list;
    }

    public List<QuizHistory> getHistoryByUserName(String value,int count) {
        Session session = hibernatUtil.getSessionFactory().openSession();
        String query = "from QuizHistory h left join fetch h.quizUser u left join fetch h.quiz where concat(u.firstName,' ',u.lastName) = :value";
        Query q = session.createQuery(query);
        q.setFirstResult((count-1)*10);
        q.setMaxResults(10);
        q.setParameter("value",value);
        List list = q.list();
        return list;
    }

    public static void main(String[] args) {
        HibernateQuizHistoryDAO hibernateQuizHistoryDAO = new HibernateQuizHistoryDAO();
        List<QuizHistory> quizHistory = hibernateQuizHistoryDAO.getHistoryByUserName("Caroline Rhodes",1);
        System.out.println(quizHistory.get(0));
        for(QuizHistory x: quizHistory){
            x.setHistoryAnswers(null);
        }
        Gson gson =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        gson.toJson(quizHistory);
    }


}
