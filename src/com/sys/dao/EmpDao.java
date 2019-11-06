package com.sys.dao;

import com.sys.entity.Emp;
import com.sys.entity.Page;
import com.sys.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
    public static void addEmp(Emp emp) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        String sql = "insert into emp(id,name,age,sex) values(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, emp.getId());
            ps.setObject(2, emp.getName());
            ps.setObject(3, emp.getAge());
            ps.setObject(4, emp.getSex());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    public static List<Emp> show() {
        List<Emp> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from emp";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Emp emp = null;
            while (rs.next()) {
                emp = new Emp();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                emp.setId(id);
                emp.setName(name);
                emp.setAge(age);
                emp.setSex(sex);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    public static void delEmp(int id) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        String sql = "delete from emp where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    public static void updateEmp(int id, int age) {
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        String sql = "update emp set age = ? where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, age);
            ps.setObject(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    public static List<Emp> like(String cname) {
        List<Emp> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Emp emp = null;
        String sql = "select * from emp where name like ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%" + cname);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp = new Emp();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                emp.setId(id);
                emp.setName(name);
                emp.setAge(age);
                emp.setSex(sex);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    public static List<Emp> pageShow(Page page) {
        List<Emp> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Emp emp = null;
        String sql = "select * from emp limit ?,?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (page.getPage() - 1) * page.getPageSize());
            ps.setObject(2, page.getPageSize());
            rs = ps.executeQuery();
            while (rs.next()) {
                emp = new Emp();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                emp.setId(id);
                emp.setName(name);
                emp.setAge(age);
                emp.setSex(sex);
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }


    public static void main(String[] args) {
//        Emp emp = new Emp(1007, "潘金莲", 22, "女");
//        addEmp(emp);
//        System.out.println(show());
//        delEmp(1007);
//        System.out.println(show());
//        updateEmp(1001, 40);
//        System.out.println(show());
        like("娘").stream().forEach(System.out::println);
        Page page = new Page();
        page.setPage(3);
        pageShow(page).stream().forEach(System.out::println);
    }
}
