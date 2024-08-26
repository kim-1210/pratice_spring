package test.test_spring.Controller;

public class MemberForm { //html에서 데이터를 받아서 객체로 자동으로 바꿔줌 <post에서 MemberForm을 받아오는 거>
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
