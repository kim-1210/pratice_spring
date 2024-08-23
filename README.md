#pratice_spring

### domain -> Member class가 들어있으며, long 타입의 id 와 String타입의 name 존재
### repository -> Member Management API
#####   - MemberRepository : 회원에 관한 api
#####   - MemoryMemberRepository : api의 기능 작성
### service 파일 -> MemberService : 가상DB 회원가입 및 조회 기능 제작
------------------------------------------------------------------------------
### Repository 파일 -> MemoryMemberRepository : 가상DB 생성후 기능 작동 테스트
### service 파일 -> MemberServiceTest : 외부 프로그램에서 가상DB 접근 후 기능 테스트