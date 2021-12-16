import java.util.ArrayList;
import java.util.*;
import java.sql.*;
public class stu
{
    Connection c=null;
    PreparedStatement ps = null;
    private  int rollno ;
    private String name ;
    private int Marks ;
    stu() throws SQLException
    {
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/etest","root","");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public int getMarks() {
        return Marks;
    }

    public void setMarks(int marks) {
        Marks = marks;
    }
    public void insert ()throws SQLException
    {
        String query =  "insert into student values (?,?,?)";
        ps = c.prepareStatement(query);
        ps.setInt(1,rollno);
        ps.setString(2,name);
        ps.setInt(3,Marks);
        ps.executeUpdate();
        System.out.println("Insertion succesfull");
    }
    public void display() throws SQLException
    {
        String query = "select * from student" ;
        ps = c.prepareStatement(query);
        ResultSet res = ps.executeQuery();
        System.out.println("This is your database");
        while (res.next())
        {
            System.out.println(res.getInt(1)+" "+res.getString(2)+" "+res.getInt(3));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException,NotPass,IndexOutOfBoundsException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner sc = new Scanner(System.in);
        ArrayList<stu> obj = new ArrayList<>(5);
        for(int i=0;i<1;i++)
        {
            System.out.println("<Enter the RollNumber>");
            int rno = sc.nextInt();
            System.out.println("<Enter the Name of the Student>");
            String name = sc.next();
            System.out.println("<Enter the marks of the student>");
            int marks = sc.nextInt();

            if(marks>35)
            {
                stu obj1 =new stu();
                obj1.setRollno(rno);
                obj1.setName(name);
                obj1.setMarks(marks);
                obj.add(obj1);
                obj.get(i).insert();
            }
            else {
                try {
                    throw new NotPass("Not Passed");
                }
                catch(NotPass e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
class NotPass extends Exception
{
    NotPass(String str)
    {
        super(str);
    }
}
