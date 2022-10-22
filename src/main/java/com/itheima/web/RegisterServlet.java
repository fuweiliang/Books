package com.itheima.web;

import com.itheima.Utils.SqlSessionFactoryUtils;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = new String(request.getParameter("userName").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        String passworld = new String(request.getParameter("userPassworld").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        //2.封装对象
        User u = new User();
        u.setUsername(username);
        u.setPassword(passworld);

        //获取用户输入的验证码
        String checkcode = request.getParameter("checkcode");

        //从程序生成的验证码，从session获取
        HttpSession session = request.getSession();
        String codeGen = (String) session.getAttribute("chenckCodeGen");
        //校验验证码 equalsIgnoreCase表示忽略大小写校验
        if (!codeGen.equalsIgnoreCase(checkcode)){
            request.setAttribute("","验证码错误");
            //校验失败
            return;
        }

        //获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.selectByUserName(username);


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        if (user == null) {
            //用户不存添加用户
            mapper.addUser(u);

            //提交事务
            sqlSession.commit();

            sqlSession.close();

            writer.write("注册成功 即将跳转登录界面");
            response.sendRedirect("http://localhost/index.html");
        } else {
            //用户存在
            writer.write("用户已存在");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
