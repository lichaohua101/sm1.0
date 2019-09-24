package com.entor.dao;

import java.util.List;

import com.entor.entity.Student;

public interface StudentDao {
	/**
	 * 登录
	 * @param username		帐号
	 * @param password		密码
	 * @return				登录成功的学生对象
	 */
	public Student login(String username ,String password);
	/**
	 * 新增加的用户
	 * @param s
	 */
	public void add(Student s);
	/**
	 * 分页查询数据
	 * @param currentPage		当前页码
	 * @param pageSize			每页的记录数
	 * @return					每页显示的记录数
	 */
	public List<Student> queryByPage(int currentPage,int pageSize);
	/**
	 * 计数器  总数
	 * @return
	 */
	public int getTotal();
	/**
	 * 根据主键删除
	 */
	public void deleteById(int id);
	/**
	 * 根据主键查找内容
	 * @return
	 */
	public Student queryById(int id);
	/**
	 * 更新数据
	 */
	public void update(Student s);
	/**
	 * 批量删除数据
	 * @param ids		有主机编号拼成的字符串 格式1，2，3
	 */
	public void deleteMore(String ids);
	/**
	 * 批量增加新用户
	 * @param list
	 */
	public void addMore(List<Student> list);
	/**
	 * 查询所有的数据
	 * @return
	 */
	public List<Student> queryAll();
}
