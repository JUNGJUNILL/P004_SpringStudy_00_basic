package hellojpa;

import P001_Item_JOINED_STRATEGY.Movie;
import P002_Item_SINGLE_TABLE_STRATEGY.Movie_S;
import P004_Item_MappedSuperClass.Member_M;
import P004_Item_MappedSuperClass.Team_M;
import P005_CASECADE.Child;
import P005_CASECADE.Parent;
import P006_ORPANREMOVAL.Child_O;
import P006_ORPANREMOVAL.Parent_O;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    //상속관계 매핑
    //JOINED_STRATEGY
    /*
    • 장점
        • 테이블 정규화
        • 외래 키 참조 무결성 제약조건 활용가능
        • 저장공간 효율화
    • 단점
        • 조회시 조인을 많이 사용, 성능 저하
        • 조회 쿼리가 복잡함
        • 데이터 저장시 INSERT SQL 2번 호출
    */
    private static void func001(EntityManager em, EntityTransaction tx){

        tx.begin();
        Movie movie=new Movie();
        movie.setName("parasite");
        movie.setDirector("bong");
        movie.setActor("song");
        movie.setPrice(1000);
        em.persist(movie);

        em.flush();
        em.clear();

        Movie findMovie = em.find(Movie.class,1l);
        System.out.println("getDirector()="+findMovie.getDirector());

        tx.commit();

    }

    //상속관계 매핑
    //SINGLE_TABLE_STRATEGY
    /*
    • 장점
        • 조인이 필요 없으므로 일반적으로 조회 성능이 빠름
        • 조회 쿼리가 단순함
    • 단점
        • 자식 엔티티가 매핑한 컬럼은 모두 null 허용
        • 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있다. 상황에 따라서 조회 성능이 오히려 느려질 수 있다.
    */
    private static void func002(EntityManager em, EntityTransaction tx){
        tx.begin();
        Movie_S movie=new Movie_S();
        movie.setName("CASHTRUCK");
        movie.setDirector("JJI");
        movie.setActor("JSON");
        movie.setPrice(1000);
        em.persist(movie);

        em.flush();
        em.clear();

        Movie_S findMovie = em.find(Movie_S.class,1l);
        System.out.println("getDirector()="+findMovie.getDirector());

        tx.commit();

    }

    //@MappedSuperClass
    /*
    • 상속관계 매핑X
    • 엔티티X, 테이블과 매핑X
    • 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
    • 조회, 검색 불가(em.find(BaseEntity) 불가)
    • 직접 생성해서 사용할 일이 없으므로 추상 클래스 권장
    • 테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑정보를 모으는 역할
    • 주로 등록일, 수정일, 등록자, 수정자 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용
    • 참고: @Entity 클래스는 엔티티나 @MappedSuperclass로 지정한 클래스만 상속 가능
    */
    private static void func004(EntityManager em, EntityTransaction tx){
        tx.begin();
        Member_M member_m =new Member_M();
        member_m.setUsername("HELLO");
        member_m.setCreatedBy("JJI");
        member_m.setCreatedDate(LocalDateTime.now());
        em.persist(member_m);

        em.flush();
        em.clear();

        tx.commit();

    }

    //프록시
    //• 프록시 객체는 처음 사용할 때 한 번만 초기화
    //• 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아님, 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능
    private static void func005(EntityManager em, EntityTransaction tx){
        tx.begin();
        Member_M member_m=new Member_M();
        member_m.setUsername("JJI1");
        em.persist(member_m);

        Member_M member_m2=new Member_M();
        member_m2.setUsername("JJI2");
        em.persist(member_m2);
        em.flush();
        em.clear();

 //---프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함 (== 비교 실패, 대신 instance of 사용)--/
        Member_M m1=em.find(Member_M.class,member_m.getId());
        Member_M m3=em.getReference(Member_M.class,member_m2.getId());

        System.out.println("m1 == m3 ="+ (m1.getClass()==m3.getClass()));
        System.out.println((m1 instanceof Member_M));
        System.out.println((m3 instanceof Member_M));
//-------------------------------------------------------------------------------/


//---• 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환--/
        Member_M reference = em.getReference(Member_M.class,member_m.getId());
        System.out.println("m1.getClass()=" + m1.getClass());
        System.out.println("reference.getClass()=" + reference.getClass());
        System.out.println("a == a: " + (m1 == reference));
//-------------------------------------------------------------------------------/



        Member_M findMember =em.getReference(Member_M.class,member_m.getId());
        //em.getReference() : 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
        System.out.println(findMember.getClass());
        System.out.println("Member_m.id="+member_m.getId());
        System.out.println("Member_m.userName="+member_m.getUsername());



        tx.commit();
    }

    //프록시
    /*
    실무에서 진짜 많이 만나게 된다.

    ★★
    즉 프록시를 사용하면 엔티티가 사용될 때 까지 조회하지 않고 있다가 필요할 때 조회하는 방식이라는 것을 알 수 있습니다.

    • 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면
    문제 발생
    (하이버네이트는 org.hibernate.LazyInitializationException 예외를 터트림)
    */
    private static void func006(EntityManager em, EntityTransaction tx){

        try{


        tx.begin();
        Member_M member_m=new Member_M();
        member_m.setUsername("JJI1");
        em.persist(member_m);

        em.flush();
        em.clear();

        Member_M refMember=em.getReference(Member_M.class,member_m.getId());
        System.out.println("refMember = " + refMember.getClass()); //Proxy
//        em.detach(refMember);
//        em.close();

            System.out.println();

        System.out.println(refMember.getUsername());




        tx.commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    //프록시
    private static void func007(EntityManagerFactory emf, EntityManager em, EntityTransaction tx){

        try{
            tx.begin();
            Member_M member_m=new Member_M();
            member_m.setUsername("JJI1");
            em.persist(member_m);

            em.flush();
            em.clear();

            //• 프록시 인스턴스의 초기화 여부 확인
            //PersistenceUnitUtil.isLoaded(Object entity)
            Member_M refMember=em.getReference(Member_M.class,member_m.getId());
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //false
            //System.out.println(refMember.getUsername()); //이때 프록시가 초기화 된다.

            //• 프록시 클래스 확인 방법
            System.out.println("refMember = " + refMember.getClass());
            
            
            //• 프록시 강제 초기화 방법
            Hibernate.initialize(refMember); //강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //true

            tx.commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    //프록시 - 지연 로딩(LAZY) (실무에서는 "즉시로딩(EAGER) 사용 X")
    private static void func008(EntityManager em, EntityTransaction tx){
        try{
            tx.begin();

            Team_M team = new Team_M();
            team.setName("TeamA");
            em.persist(team);

            Member_M member_m=new Member_M();
            member_m.setUsername("JJI1");
            member_m.setTttt(team);
            em.persist(member_m);

            em.flush();
            em.clear();

            Member_M m =em.find(Member_M.class,member_m.getId());
            System.out.println("M=" +m.getTttt().getClass());

            //지연 로딩 LAZY을 사용해서 프록시로 조회
            System.out.println("================");
            //프록시 초기화(실제 team을 사용하는 시점에 초기화(DB조회))
            m.getTttt().getName();
            System.out.println("================");

            tx.commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    //즉시로딩(EAGER)은 JPQL N+1 문제를 일으킨다.
    //(쓸데없는 쿼리가 막 실행됨)
    private static void func009(EntityManager em, EntityTransaction tx){
        try{
            tx.begin();

            Team_M teamA = new Team_M();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team_M teamB = new Team_M();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member_M member_m=new Member_M();
            member_m.setUsername("JJI1");
            member_m.setTttt(teamA);
            em.persist(member_m);

            Member_M member_m2=new Member_M();
            member_m2.setUsername("JJI2");
            member_m2.setTttt(teamB);
            em.persist(member_m2);

            em.flush();
            em.clear();

            List<Member_M> list=em.createQuery("select m from Member_M m",Member_M.class).getResultList();


            tx.commit();
        }catch (Exception e){
            System.out.println(e);
        }



    }

    //CASECADE
    //부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
    //하나의 부모가 자식들을 관리 할 때 사용(단일 테이블에 완전히 종속적일때, 라이프 사이클이 유사 할 때)
    private static void func010(EntityManager em, EntityTransaction tx){

        try{tx.begin();
        Child child1 =new Child();
        child1.setName("child1");
        Child child2 =new Child();
        child2.setName("child2");

        Parent parent=new Parent();

        parent.setName("mom1");
        parent.addChild(child1);
        parent.addChild(child2);

        em.persist(parent); //em.persist를 한방에 

        tx.commit();
    }catch (Exception e){
        System.out.println(e);
    }


    }


    //고아객체
    //• 참조하는 곳이 하나일 때 사용해야함!
    //• 특정 엔티티가 개인 소유할 때 사용
    private static void func011(EntityManager em, EntityTransaction tx){

        try{tx.begin();
            Child_O child1 =new Child_O();
            child1.setName("child1");
            Child_O child2 =new Child_O();
            child2.setName("child2");

            Parent_O parent=new Parent_O();

            parent.setName("mom1");
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent); //em.persist를 한방에 , em.remove(parent) 일 때도 하위 자식 테이블도 다 삭제 됨

            em.flush();
            em.clear();

            Parent_O findParent =em.find(Parent_O.class,parent.getId());
            findParent.getChildList().remove(0);


            tx.commit();
        }catch (Exception e){
            System.out.println(e);
        }


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


            /*
            tx.begin();
            Team team =new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member3= new Member3();
            member3.setUsername("member3");
            member3.setTeam(team);
            em.persist(member3);

            em.flush();
            em.clear();

            Member3 findMember = em.find(Member3.class,member3.getId());

            Team findTeam = findMember.getTeam();


            for(Member3 list : findMember.getTeam().getMember3()){
                System.out.println("getUsername=>"+list.getUsername());
            }

            tx.commit();
            */


            //연관관계의 주인 예제
           /* tx.begin();

            Team team =new Team();
            team.setName("TeamAAA");
            em.persist(team);


            Member3 member3= new Member3();
            member3.setUsername("member3");

            Member3 member3_01=new Member3();
            member3_01.setUsername("member3_01");
   //         member3.setTeam(team);  //순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자

//            연관관계 편의메서드(01번 버젼)
//            member3.changeTeam(team);

//            연관관계 편의메서드(02번 버젼)
            team.addMember(member3);
            team.addMember(member3_01);
            em.persist(member3);
            em.persist(member3_01);
//            team.getMember3().add(member3); //순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
                                                                            //이거 안쓸거면 연관관계 편의 메서드를 만들어서 사용하자.

//            em.flush();
//            em.clear();

            Team fineTeam=em.find(Team.class,team.getId());
            List<Member3> members = fineTeam.getMember3();


            for(int i=0; i<members.size(); i++){

                System.out.println("username= "+members.get(i).getUsername());
            }

            tx.commit();
            */


            //상속관계 매핑
//            func001(em,tx);
//            func002(em,tx);
//            func004(em,tx);
//            func005(em,tx);
//            func006(em,tx);
//            func007(emf,em,tx);
//            func008(em,tx);
//            func009(em,tx);
//            func010(em,tx);
            func011(em,tx);

        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
