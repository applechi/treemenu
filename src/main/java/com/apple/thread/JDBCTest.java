package com.apple.thread;

import java.sql.*;
import java.util.Date;
/**
 * @program: treemenu
 * @description: jdbc测试
 * @author: Apple
 * @create: 2019-07-19 18:50
 **/
public class JDBCTest {
    // JDBC 驱动名 及数据库 URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL      = "jdbc:mysql://localhost:3306/hrm";

    // 数据库的用户名与密码，需要根据自己的设置
    public static final String USER     = "root";
    public static final String PASSWORD = "chi123";

    public static void main(String[] args) {
        Connection        conn = null;
        PreparedStatement psmt = null;
        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // 执行查询
            String sql = "SELECT * FROM user_inf";
            System.out.println(" 实例化Statement对...");
            psmt = conn.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery(sql);

            while (rs.next()) {
                // 通过字段检索
                int    id         = rs.getInt("ID");
                String loginname  = rs.getString("loginname");
                String createTime = rs.getString("createdate");
                String userName   = rs.getString("username");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", loginname: " + loginname);
                System.out.print(",创建时间: " + createTime);
                System.out.print(", 备注名：" + userName);
                System.out.print("\n");
            }
            //更新update
            //String update_sql = "update  user_inf set loginname='ucar'  where ID=1";
            //psmt.execute(update_sql);

            //增加insert
            String inset_sql="insert into user_inf (loginname, createTime, userName) values(?,now(),?)";
            Date date=new Date();
            psmt.setString(1, "dsadsa");
            //psmt.setDate(2, (java.sql.Date) date);
            psmt.setString(2, "female");
            int i = psmt.executeUpdate(inset_sql);

            //删除 delete
            //String delete_sql = "delete from user_inf where user_id>1";
            //psmt.execute(delete_sql);

            // 完成后关闭
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("jdbc_test_end!");
    }

}
