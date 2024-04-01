package vn.codegym.controller;

import vn.codegym.model.Student;
import vn.codegym.service.StudentServiceImlt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = {"","/student"})
public class StudentServlet  extends HttpServlet {
    StudentServiceImlt studentServiceImlt = new StudentServiceImlt();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreatePage(req,resp);
                break;
            case "edit":
                showEditPage(req,resp);
                break;
            case "delete":
                showDeletePage(req,resp);
                break;
            default:
                showList(req, resp);
        }

    }

    private void showDeletePage(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentServiceImlt.findById(id);
        studentServiceImlt.remove(id,student);
        try {
            resp.sendRedirect("/student");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        Student student = studentServiceImlt.findById(id);
        req.setAttribute("student",student);
        req.getRequestDispatcher("/student/edit.jsp").forward(req,resp);
    }

    private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/student/create.jsp");
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("studentList", studentServiceImlt.findAll());
        req.getRequestDispatcher("/student/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                createStudent(req,resp);
                break;
            case "edit":
                editStudent(req,resp);
                break;
            case "search":
                searchStudent(req,resp);
                break;
            default:
                showList(req, resp);
        }

    }

    private void searchStudent(HttpServletRequest req, HttpServletResponse resp) {
        String nameSearch = req.getParameter("nameSearch");
        List<Student> list = studentServiceImlt.findAll();
        List<Student> studentList = new ArrayList<>();
        for (Student student:list){
            if (student.getName().contains(nameSearch)){
                studentList.add(student);
            }
        }
        req.setAttribute("nameSearch",nameSearch);
        req.setAttribute("studentList", studentList);
        if (studentList.isEmpty()) {
            req.setAttribute("message", "There's no name containing in the list");
        }
        RequestDispatcher dispatcher =req.getRequestDispatcher("/student/home.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt( req.getParameter("id"));
        String name =  req.getParameter("name");
        String email =  req.getParameter("email");
        String birth =  req.getParameter("birth");
        String address =  req.getParameter("address");
        String phone =  req.getParameter("phone");
        String classroom =  req.getParameter("class");
        studentServiceImlt.update(id, new Student(name,email,birth,address,phone,classroom));
        resp.sendRedirect("/student");
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name =  req.getParameter("name");
        String email =  req.getParameter("email");
        String birth =  req.getParameter("birth");
        String address =  req.getParameter("address");
        String phone =  req.getParameter("phone");
        String classroom =  req.getParameter("class");
        studentServiceImlt.save(new Student(name,email,birth,address,phone,classroom));
        resp.sendRedirect("/student");
    }
}
