package com.example.demo_back.Controllers;

import com.example.demo_back.Dao.Enums.Problem_Type;
import com.example.demo_back.Dao.Problem.Problem;
import com.example.demo_back.Dao.Problem.ProblemRepository;
import com.example.demo_back.Dao.Problem.SupportMan;
import com.example.demo_back.Service.SupportmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
public class ReportController {
    @Autowired
    SupportmanService supportmanService;
    @Autowired
    ProblemRepository problemRepository;
    @PostMapping("/report")
    @ResponseBody
    public Boolean main(HttpServletRequest request){
        String description = request.getParameter("description");
        String id = request.getParameter("user_id");
        String t = request.getParameter("problem_type");
        SupportMan supportMan = supportmanService.findFreeSupport();
        System.out.println(supportMan.getPassword());
        System.out.println(description);
        System.out.println(id);
        if("".equals(id)|| "".equals(description)||"".equals(t)||t==null||description==null||id==null||supportMan==null){
            return false;
        }
        Problem p = new Problem();
        p.set_finished(false);
        p.setDescription(description);
        p.setSupport_man_id(supportMan.getId());
        p.setUser_id(Integer.valueOf(id));
        java.util.Date ud = new java.util.Date();
        p.setDate(new java.sql.Date(ud.getTime()));
        p.setProblem_type(Problem_Type.valueOf(request.getParameter("problem_type")));
        problemRepository.save(p);
        return true;
    }
}
