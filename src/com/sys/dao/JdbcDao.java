package com.sys.dao;

import com.sys.entity.Emp;
import com.sys.entity.Page;
import com.sys.util.JdbcTemplateUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcTemplateUtil.getDataSource());

    public List<Emp> show() {
        String sql = "select * from emp";
        return template.query(sql, new BeanPropertyRowMapper<>(Emp.class));
    }

    public List<Emp> like(String name, Integer age) {
        String sql = "select * from emp where name like ? and age > ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Emp.class), "%" + name + "%", age);
    }

    public List<Emp> pageShow(Page page) {
        String sql = "select * from emp limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(Emp.class), (page.getPage() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Emp showById(Integer id) {
        String sql = "select * from emp where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), id);
    }

    public void add(Emp emp) {
        String sql = "insert into emp(id,name,age,sex) values(?,?,?,?)";
        template.update(sql, emp.getId(), emp.getName(), emp.getAge(), emp.getSex());
    }

    public void delById(Integer id) {
        String sql = "delete from emp where id = ?";
        template.update(sql, id);
    }

    public static void main(String[] args) {
        JdbcDao dao = new JdbcDao();
//        System.out.println(dao.template);
//        dao.show().stream().forEach(n -> System.out.println(n));
//        dao.like("娘", 25).stream().forEach(n -> System.out.println(n));
//        Page page = new Page();
//        page.setPage(1);
//        dao.pageShow(page).stream().forEach(n -> System.out.println(n));
//        System.out.println(new JdbcDao().showById(1003));
//        Emp emp = new Emp(1008,"西门庆",39,"男");
//        dao.add(emp);
        dao.delById(1008);
        dao.show().stream().forEach(n -> System.out.println(n));
    }
}
