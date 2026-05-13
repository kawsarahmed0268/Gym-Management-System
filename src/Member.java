public class Member {
    public int memberId;
    public String name;
    public int age;
    public String gender, phone;
    public MemberShip membership;

    public Member(int memberId, String name, int age, String gender, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

    public void showMember() {
        System.out.println("\n--- Member Profile ---");
        System.out.println("ID     : " + memberId);
        System.out.println("Name   : " + name);
        System.out.println("Age    : " + age);
        System.out.println("Gender : " + gender);
        System.out.println("Phone  : " + phone);
        if(membership != null) System.out.println("Plan   : " + membership.planName);
        System.out.println("----------------------");
    }

    public void assignTrainer(Trainer t) {
        System.out.println("Trainer " + (t != null ? t.getName() : "Unknown") + " assigned successfully.");
    }

    public void assignMembership(MemberShip ms) {
        this.membership = ms;
        System.out.println("Plan '" + ms.planName + "' assigned successfully.");
    }
}
