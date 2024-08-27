package test.test_spring.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import test.test_spring.domain.Member;
import test.test_spring.repository.JDBCMemberRepository;
import test.test_spring.repository.MemberRepository;

public class JDBC_test {

    @Test
    void testing(){
        MemberRepository memory = new JDBCMemberRepository(this.dataSource);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberRepository memberRepository;
}
