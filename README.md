#pratice_spring

## Main 파일
### Contoroller 파일
#####   - HomeController = 루트 html 위치 설정
#####   - MemberContoroller = html에서 가상DB의 기능을 이용하는 연결다리
#####   - MemberForm = html에서 post했을 시, 얻어오는 데이터의 객체
#####   - TestContoroller = 나머지 html 위치
### domain -> Member class가 들어있으며, long 타입의 id 와 String타입의 name 존재
### repository -> Member Management API
#####   - MemberRepository : 회원에 관한 api
#####   - MemoryMemberRepository : api의 기능 작성
#####   - JDBCMemberRepository : h2 DB를 이용하여 member테이블을 생성 후 spring과 h2를 연결 및 기능 구현
#####   - JDBCTemplateMemberRepository : JDBC를 대신하는 JDBCTemplate를 이용한 MemberRepository 인터페이스 구현
#####      (sql query를 그대로 이용)
### service 파일 -> MemberService : 가상DB 회원가입 및 조회 기능 제작
### Config.java -> Spring Container에 넣어주기 위한 Spring Bean작업 <java self>

------------------------------------------------------------------------------
## Test 파일
### Repository 파일 -> MemoryMemberRepository : 가상DB 생성후 기능 작동 테스트
####   - MemberServiceIntegrationTest : Springboot를 연동하고, Transaction을 이용한 DB테스트
### service 파일 -> MemberServiceTest : 외부 프로그램에서 가상DB 접근 후 기능 테스트