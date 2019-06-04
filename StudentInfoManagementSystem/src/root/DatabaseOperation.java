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

    public ArrayList<BasicStuentInfoBlob> GetStudentBasicInfo_byClassAndName(String Class_,String Name) {
        ArrayList<BasicStuentInfoBlob> ret = new ArrayList<>();
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM StudentBasicMsg where Class = " + Class_ + " and StudentName = " + '"'+Name + '"';
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
        }catch (IOException e) {
            System.out.println("open filed");
        }finally {

        }
    }
}

