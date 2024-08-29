package test.test_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import test.test_spring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    @Override
    Optional<Member> findByName(String name);
    //jpa : select m from Member as m where m.name = ?
    //기존 이외에 다른 것도 하고 싶다? => findByNameAndId(name, id) / findByNameOrId(name, id) 등으로 쓰면됨
    //동적 쿼리 : Querydsl을 사용
}
