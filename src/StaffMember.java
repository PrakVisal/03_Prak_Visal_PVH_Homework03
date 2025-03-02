abstract class StaffMember {
    protected int id;
    protected String name;
    protected String address;
    public StaffMember(int id , String name , String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    public abstract double pay();
}
