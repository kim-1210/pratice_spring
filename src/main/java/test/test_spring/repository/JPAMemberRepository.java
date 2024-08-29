package test.test_spring.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import test.test_spring.domain.Member;

public class JPAMemberRepository implements MemberRepository{

    private final EntityManager em;

    //스트리부트가 entitymanager를 있는 정보를 토대로 만들어 줌
    public JPAMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //insert query를 만들어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
    
}
