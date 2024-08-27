package test.test_spring.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;

import test.test_spring.domain.Member;

public class JDBCMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JDBCMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private Connection getConnection(){ //database transaction을 나오지않게 유지를 시켜줌
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn) throws SQLException{
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs != null){rs.close();}
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(pstmt != null){pstmt.close();}
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn != null){close(conn);}
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            //pstmt에 sql을 넣은 후 sql의 values에 넣는 값들을 setting하는 것
            pstmt.executeUpdate();
            //실제 쿼리에 넣음
            rs = pstmt.getGeneratedKeys();
            //Statement.RETURN_GENERATED_KEYS를 해줘야 가능함
            if(rs.next()){
                member.setId(rs.getLong(1));
            }
            else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        }
        catch(Exception e){
            throw new IllegalStateException(e);
        }
        finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery(); //pstmt에서 sql후 값을 받아옴

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }

    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery(); //pstmt에서 sql후 값을 받아옴

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }
    }
    
}
