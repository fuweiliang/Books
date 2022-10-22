package com.itheima.web;

import com.itheima.Utils.SqlSessionFactoryUtils;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/LOginServlet", name = "LOginServlet")
public class LOginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = new String(req.getParameter("username").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        String passworld = new String(req.getParameter("password").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //获取对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //调用方法
        User user = mapper.selectAll(username, passworld);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        if (user != null) {
            resp.sendRedirect("http://localhost/pages/books.html");
//            req.getRequestDispatcher("url:http://localhost:8080/springmvc_08_ssm_demo/pages/books.html").forward(req, resp);
        } else {
            writer.write("登录失败");

        }
        //释放资源
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
