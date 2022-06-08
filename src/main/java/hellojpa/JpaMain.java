package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaMain {

    private static void insert(EntityManager em, EntityTransaction tx, Member member, Long id, String name, Integer age, RoleType roleType){

        tx.begin();

        member.setId(id);
        member.setName(name);
        member.setAge(age);
        member.setRoleType(roleType);
        member.setCreatedDate(new Date());
        member.setLastModifiedDate(new Date());
        em.persist(member);
        //이 persist 메서드는 DB에 바로 저장하는 게 아니라
        //엔티티를 영속성 컨텍스트라는 곳에 저장한다는 소리이다. (Member 엔티티가 영속 상태가 됨)

        tx.commit();
        //실제 쿼리는 이때 날라간다.
        //(트렌잭션을 지원하는 쓰기 지연)

    }

    private static Member select(EntityManager em , EntityTransaction tx,Member member,Long id){

        Member member1= em.find(member.getClass(),id);
        return member1;
    }

    private static void update(EntityManager em, EntityTransaction tx,  Long id, Integer age){
        tx.begin();
        Member findMember = em.find(Member.class,id);
       // findMember.setName(name);
        findMember.setAge(age);

        tx.commit();

    }

    //엔티티를 준영속 상태로 바꿔보자.
    private static void updateDetach(EntityManager em, EntityTransaction tx,  Long id, String name){
        tx.begin();
        Member findMember = em.find(Member.class,id);
        findMember.setName(name);
        em.detach(findMember); //영속성 컨텍스트에서 관리하지마.

        tx.commit();

    }


    public static void main(String[] args) {
        System.out.println("hello jpa");

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em =null;//emf.createEntityManager();
        EntityTransaction tx = null;//em.getTransaction();
       //tx.begin();

        try{
            em = emf.createEntityManager();
            tx = em.getTransaction();
           // Member member = new Member();
           // Member m = new Member();

            //insert(em, tx, member,1L, "HelloAAA",20,RoleType.USER);
            //insert(em, tx, m,2L, "HelloBBB",21,RoleType.ADMIN);
            //update(em,tx, 1l,30);

            /*
            Member2 member2_1 =new Member2();
            Member2 member2_2 =new Member2();
            Member2 member2_3 =new Member2();
            tx.begin();
            member2_1.setUsername("mem2_1");
            member2_2.setUsername("mem2_2");
            member2_3.setUsername("mem2_3");

            em.persist(member2_1);
           em.persist(member2_2);
           em.persist(member2_3);

            tx.commit();
             */
            //Member member1 =  select(em,tx,member,1l);
            //Member member2 =  select(em,tx,member,1l);//1차 캐시로 인해 2번째 쿼리부터는 db에서 가져오지 않고 영속성 컨텍스트에서 가져온다.
                                                                                                  //(콘솔에 select 쿼리가 실행되지 않는다.)
            //System.out.println(member1==member2); //동일성 보장



            tx.begin();
            Team team =new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member3= new Member3();
            member3.setUsername("member3");
            member3.setTeam(team);
            em.persist(member3);

            Member3 findMember = em.find(Member3.class,member3.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName()= "+findTeam.getName());


            tx.commit();

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
