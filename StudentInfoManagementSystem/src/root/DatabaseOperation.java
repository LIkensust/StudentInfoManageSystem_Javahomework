package root;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperation {
    private String USER;
    private String PASS;
    private String DB_URL;
    protected final String JDBC_DRIVER;
    private Connection conn;
    DatabaseOperation() {
        SettingReader set = new SettingReader("/home/liyuan/IdeaProjects/StudentInfoManagementSystem/src/settings/MysqlConnectionSetting.json");
        USER = set.GetString("user");
        PASS = set.GetString("password");
        DB_URL = set.GetString("url");
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> singleselect(String sql,String cowname) {
        ArrayList<String> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                ret.add(rs.getString(cowname));
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public ArrayList<String> GetCollegeList() {
        String sql;
        sql = "SELECT distinct college FROM StudentBasicMsg";
        return singleselect(sql,"college");
    }

    public ArrayList<String> GetCourseList() {
        String sql;
        sql = "SELECT distinct CourseName FROM CourseBasicMsg order by CourseName desc";
        return singleselect(sql,"CourseName");
    }

    public ArrayList<String> GetClassList() {
        String sql;
        sql = "SELECT distinct class FROM StudentBasicMsg order by class";
        return singleselect(sql,"class");
    }

    public ArrayList<BasicStuentInfoBlob> GetStudentBasicInfo_all() {
        ArrayList<BasicStuentInfoBlob> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM StudentBasicMsg order by class,gender,id";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                BasicStuentInfoBlob tmp = new BasicStuentInfoBlob();
                tmp.setID_(rs.getString("ID"));
                tmp.setName_(rs.getString("StudentName"));
                tmp.setClass_(rs.getString("class"));
                tmp.setGender_(rs.getString("Gender"));
                tmp.setCollage_(rs.getString("College"));
                ret.add(tmp);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public ArrayList<BasicStuentInfoBlob> GetStudentBasicInfo_byID(String stu_id) {
        ArrayList<BasicStuentInfoBlob> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM StudentBasicMsg where ID =" + stu_id;
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                BasicStuentInfoBlob tmp = new BasicStuentInfoBlob();
                tmp.setID_(rs.getString("ID"));
                tmp.setName_(rs.getString("StudentName"));
                tmp.setClass_(rs.getString("class"));
                tmp.setGender_(rs.getString("Gender"));
                tmp.setCollage_(rs.getString("College"));
                ret.add(tmp);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public ArrayList<BasicStuentInfoBlob> GetStudentBasicInfo_byCollegeClassAndName(String college,String Class_,String Name) {
        ArrayList<BasicStuentInfoBlob> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            if(Name == null || Name.length() == 0) {
                Name = new String(".*");
            }
            sql = "SELECT * FROM StudentBasicMsg where College = "+ '"'+ college +'"' +" and Class = " + '"' + Class_ + '"' + " and StudentName REGEXP " + '"'+Name + '"' + "order by gender,studentname";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                BasicStuentInfoBlob tmp = new BasicStuentInfoBlob();
                tmp.setID_(rs.getString("ID"));
                tmp.setName_(rs.getString("StudentName"));
                tmp.setClass_(rs.getString("class"));
                tmp.setGender_(rs.getString("Gender"));
                tmp.setCollage_(rs.getString("College"));
                ret.add(tmp);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public ArrayList<BasicStuentInfoBlob> GetStudentBasicInfo_byCollege(String College) {
        ArrayList<BasicStuentInfoBlob> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM StudentBasicMsg where College =" + '"' + College + '"';
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                BasicStuentInfoBlob tmp = new BasicStuentInfoBlob();
                tmp.setID_(rs.getString("ID"));
                tmp.setName_(rs.getString("StudentName"));
                tmp.setClass_(rs.getString("class"));
                tmp.setGender_(rs.getString("Gender"));
                tmp.setCollage_(rs.getString("College"));
                ret.add(tmp);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public ArrayList<GradeInfoBlob> GetGradeInfo(String ID,String classname,String course,boolean regexp) {
        ArrayList<GradeInfoBlob> ret = new ArrayList<>();
        String sql;
        if(regexp) {
            sql = "select StudentBasicMsg.ID,StudentName,class,CourseName,grade,college,gender " +
                    "from (StudentBasicMsg inner join SelectCourseMsg " +
                    " on StudentBasicMsg.ID = SelectCourseMsg.ID ) " +
                    "inner join CourseBasicMsg" +
                    " on SelectCourseMsg.Course = CourseBasicMsg.Course " +
                    "where CourseBasicMsg.CourseName REGEXP " + '"' + course + '"' +
                    " and StudentBasicMsg.id REGEXP "+ '"' + ID + '"' +
                    " and StudentBasicMsg.class REGEXP "+ '"' + classname + '"'+
                    " order by class,gender,StudentBasicMsg.id";
        }
        else {
            sql = "select StudentBasicMsg.ID,StudentName,class,CourseName,grade,college,gender " +
                    "from (StudentBasicMsg inner join SelectCourseMsg " +
                    " on StudentBasicMsg.ID = SelectCourseMsg.ID ) " +
                    "inner join CourseBasicMsg" +
                    " on SelectCourseMsg.Course = CourseBasicMsg.Course " +
                    "where CourseBasicMsg.CourseName = " + '"' + course + '"' +
                    " and StudentBasicMsg.id REGEXP "+ '"' + ID + '"' +
                    " and StudentBasicMsg.class REGEXP "+ '"' + classname + '"'+
                    " order by class,gender,StudentBasicMsg.id";
        }

        //System.out.println(sql);
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                GradeInfoBlob tmp = new GradeInfoBlob();
                tmp.setID(rs.getString("ID"));
                tmp.setStudentName(rs.getString("StudentName"));
                tmp.setClass_(rs.getString("class"));
                tmp.setCollege(rs.getString("College"));
                tmp.setCourse(rs.getString("CourseName"));
                tmp.setGrade(rs.getString("grade"));
                tmp.setGender(rs.getString("gender"));
                ret.add(tmp);
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public void delete_course(ArrayList<ArrayList<String>> text) {
        Statement stmt = null;
        try{
            String sql = "delete from SelectCourseMsg where ID = ? and Course in (select Course from CourseBasicMsg where CourseName = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for(int i = 0;i<text.size();i++) {
                ps.setString(1, text.get(i).get(0));
                ps.setString(2, text.get(i).get(5));
                ps.executeUpdate();
            }
            ps.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
        }
    }

    public int InserIntoTable(String tablename, ArrayList<String> values) {
        Statement stmt = null;
        int ret = 0;
        if(tablename == null || tablename.equals("")) {
            return -1;
        }
        if(values == null || values.size() == 0) {
            return -1;
        }
        try{
            String sql;

            sql = "insert into " + tablename + " value (";
            for(int i = 0;i < values.size();i++) {
                if(values.get(i) == null || values.get(i).equals("")) {
                    return -1;
                }
                if(i == values.size() - 1) break;;
                sql = sql + '"' + values.get(i) + '"' + ",";
            }
            sql = sql + '"' + values.get(values.size() - 1) + '"' + ")";
            stmt = conn.createStatement();
            ret = stmt.executeUpdate(sql);
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        return ret;
    }

    public void test() {
        Statement stmt = null;
        try{
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM StudentBasicMsg";
            ResultSet rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String id  = rs.getString("ID");
                String name = rs.getString("StudentName");
                int class_ = rs.getInt("class");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", name: " + name);
                System.out.print(", class: " + class_);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
        }
        System.out.println("Goodbye!");
    }

    public void createfakedata() {
        String filename = "/home/liyuan/IdeaProjects/StudentInfoManagementSystem/src/2016studentinfo.csv";
        String gradefilename = "/home/liyuan/IdeaProjects/StudentInfoManagementSystem/src/grade.csv";
        String line;
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            line = in.readLine();
            while(line != null) {
                String[] oneline = line.split(",");
                //201611010532,杨晨涛,信息162,男,文理学院
                ArrayList<String> values = new ArrayList<>();
                values.add(oneline[0]);
                values.add(oneline[1]);
                values.add(oneline[3]);
                values.add(oneline[2]);
                values.add(oneline[4]);
                int num = InserIntoTable("StudentBasicMsg",values);
                System.out.println("insert num :" + num);
                line = in.readLine();
            }
            in.close();
            in = new BufferedReader(new FileReader(gradefilename));
            line = in.readLine();
            while(line != null) {
                String[] oneline = line.split(",");
                //201611010532,杨晨涛,信息162,男,文理学院
                ArrayList<String> values = new ArrayList<>();
                values.add(oneline[0]);
                values.add(oneline[1]);
                values.add(oneline[2]);
                int num = InserIntoTable("SelectCourseMsg",values);
                System.out.println("insert num :" + num);
                line = in.readLine();
            }
        }catch (IOException e) {
            System.out.println("open filed");
        }finally {

        }
    }
}