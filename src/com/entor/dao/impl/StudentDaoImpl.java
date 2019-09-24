package com.entor.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entor.dao.StudentDao;
import com.entor.entity.Student;

import util.DBUtil;


public class StudentDaoImpl implements StudentDao{
	/**
	 * 登录
	 */
	@Override
	public Student login(String username, String password) {
		Student s =null;
		Connection con=DBUtil.getConnection();
		String sql="select * from student where username=? and password=?";
		PreparedStatement pst=null;
		ResultSet rs =null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return s;
	}
	/**
	 * 注册
	 */
	@Override
	public void add(Student s) {
		Connection con = DBUtil.getConnection();
		String sql ="insert into student(id,name,username,password,sex,age,brithday,createTime) values(stu_seq.nextval,?,?,?,?,?,?,sysdate)";
		PreparedStatement pst=null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2, s.getUsername());
			pst.setString(3, s.getPassword());
			pst.setInt(4, s.getSex());
			pst.setInt(5, s.getAge());
			pst.setDate(6, new Date(s.getBrithday().getTime()));
			System.out.println("成功插入"+pst.executeUpdate()+"条数据");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}
	/**
	 * 分页查询
	 */
	@Override
	public List<Student> queryByPage(int currentPage, int pageSize) {
		Student s=null;
		List<Student> list=new ArrayList<Student>();
		Connection con=DBUtil.getConnection();
		PreparedStatement pst=null;
		String sql="select ss.* from(select s.*,rownum r from(select * from student order by id)s where rownum<=?)ss where ss.r>?";
		ResultSet rs =null;
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, (currentPage*pageSize));
			pst.setInt(2, (currentPage-1)*pageSize);
			rs = pst.executeQuery();
			while(rs.next()) {
				s =new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
				list.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}
	/**
	 * 总数
	 */
	@Override
	public int getTotal() {
		int count =0;
		Connection con=DBUtil.getConnection();
		PreparedStatement pst=null;
		String sql="select count(*) from student";
		ResultSet rs =null;
		try {
			pst=con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return 0;
	}
	/**
	 * 销毁根据Id删除
	 */
	@Override
	public void deleteById(int id) {
		Connection con = DBUtil.getConnection();
		String sql="delete from student where id=?";
		PreparedStatement pst = null; 
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("删除了"+pst.executeUpdate()+"条数据");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}
	/**
	 * 根据id查 然后修改
	 */
	@Override
	public Student queryById(int id) {
		Student s =null;
		Connection con=DBUtil.getConnection();
		String sql="select * from student where id=?";
		PreparedStatement pst=null;
		ResultSet rs =null;
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return s;
	}
	/**
	 * 修改
	 */
	@Override
	public void update(Student s) {
		Connection con = DBUtil.getConnection();
//		String sql ="insert into student(id,name,username,password,sex,age,brithday,createTime) values(stu_seq.nextval,?,?,?,?,?,?,sysdate)";
		String sql="update  student set name=?,username=?,password=?,sex=?,age=?, brithday=?, createTime=sysdate where id=?";
		PreparedStatement pst=null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, s.getName());
			pst.setString(2, s.getUsername());
			pst.setString(3, s.getPassword());
			pst.setInt(4, s.getSex());
			pst.setInt(5, s.getAge());
			pst.setDate(6, new Date(s.getBrithday().getTime()));
			pst.setInt(7, s.getId());
			System.out.println("成功修改"+pst.executeUpdate()+"条数据");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}
	/**
	 * 批量删除
	 */
	@Override
	public void deleteMore(String ids) {
		Connection con = DBUtil.getConnection();
		String sql="delete from student where id in ("+ids+")";
		PreparedStatement pst = null; 
		try {
			pst = con.prepareStatement(sql);
			System.out.println("删除了"+pst.executeUpdate()+"条数据");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
		
	}
	/**
	 * 批量添加
	 */
	@Override
	public void addMore(List<Student> list) {
		Connection con = DBUtil.getConnection();
		String sql = "insert into student(id,name,username,password,sex,age,brithday,createTime) values(stu_seq.nextval,?,?,?,?,?,?,sysdate)";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				Student s = list.get(i);
				pst.setString(1, s.getName());
				pst.setString(2, s.getUsername());
				pst.setString(3, s.getPassword());
				pst.setInt(4, s.getSex());
				pst.setInt(5, s.getAge());
				pst.setDate(6, new Date(s.getBrithday().getTime()));
				pst.addBatch();
				if(i%100==0) {
					pst.executeBatch();
					pst.clearBatch();
				}
			}
			pst.executeBatch();
			pst.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, null);
		}
	}
	/**
	 * 查询所有数据
	 */
	@Override
	public List<Student> queryAll() {
		List<Student> list = new ArrayList<Student>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from student order by id";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setUsername(rs.getString("username"));
				s.setPassword(rs.getString("password"));
				s.setSex(rs.getInt("sex"));
				s.setAge(rs.getInt("age"));
				s.setBrithday(rs.getDate("brithday"));
				s.setCreateTime(rs.getTimestamp("createTime"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(con, pst, rs);
		}
		return list;
	}

}
