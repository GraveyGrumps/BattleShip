package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.daos.ClanDao;
import com.revature.daos.UserDao;
import com.revature.entities.Clan;
import com.revature.entities.User;
import com.revature.service.ClanService;

@Controller
@RequestMapping("clan")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class ClanController {
    Logger log = Logger.getRootLogger();
    @Autowired
    ClanDao cd;
    @Autowired
    ClanService clnSvc;
    @Autowired
    UserDao usrDao;

    @PostMapping("new")
    @ResponseBody
    public Clan createClan(@RequestBody Clan clan, HttpServletRequest request) {
	if ("".equals(clan.getLogo())) {
	    return clnSvc.createClan(clan.getName());
	} else {
	    return clnSvc.createClan(clan.getName(), clan.getLogo());
	}
    }

    @GetMapping("all")
    @ResponseBody
    public List<Clan> getAllClans() {
	return clnSvc.getAllClans();
    }

    /**
     * @param clan
     *            The clan object passed in through the request body. The ID is the
     *            ID of the clan being renamed. The name is the new name of the
     *            clan.
     * @param request
     * @return The clan if successfully renamed. Null if unsuccessful.
     */
    @PutMapping("edit-name")
    @ResponseBody
    public Clan editClanName(@RequestBody Clan clan, HttpServletRequest request) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Clan clanToRename = clnSvc.getClan(clan.getId());

	if (clanToRename != null) {
	    return clnSvc.changeClanName(currentUser, clanToRename, clan.getName());
	    // return new Clan(99, "success", "", "");
	} else {
	    // return null;
	    return new Clan(99, "failed", "", "");
	}
    }

    @GetMapping("test")
    @ResponseBody
    public String getTest() {
	log.trace("in /clan/test method");
	return "test successful";
    }

}
