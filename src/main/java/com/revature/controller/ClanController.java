package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param inputClan
     *            The clan object passed in through the request body. The ID is the
     *            ID of the clan being renamed. The name is the new name of the
     *            clan.
     * @param request
     * @return The clan if successfully renamed. Null if unsuccessful.
     */
    @PutMapping("edit-name")
    @ResponseBody
    public Clan editClanName(@RequestBody Clan inputClan, HttpServletRequest request) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Clan clanToRename = clnSvc.getClan(inputClan.getId());
	if (clanToRename != null) {
	    return clnSvc.changeClanName(currentUser, clanToRename, inputClan.getName());
	} else {
	    return null;
	}
    }

    /**
     * @param inputClan
     *            The clan object passed in through the request body. The ID is the
     *            ID of the clan being renamed. The logo is the new logo of the
     *            clan.
     * @param request
     * @return The clan if successfully renamed. Null if unsuccessful.
     */
    @PutMapping("edit-logo")
    @ResponseBody
    public Clan editClanLogo(@RequestBody Clan inputClan, HttpServletRequest request) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Clan clanToEdit = clnSvc.getClan(inputClan.getId());
	if (clanToEdit != null) {
	    return clnSvc.changeClanLogo(currentUser, clanToEdit, inputClan.getLogo());
	} else {
	    return new Clan(0, "clan id not found", "", "");
	}
    }

    @DeleteMapping("delete/{clanId}")
    @ResponseBody
    public ResponseEntity<Object> deleteClan(@PathVariable int clanId, HttpServletRequest request) {
	User currentUser = usrDao.getUserById(2); // admin
	// User currentUser = request.getParameter("user");
	Clan clanToDelete = clnSvc.getClan(clanId);
	if (clanToDelete != null) {
	    clnSvc.deleteClan(currentUser, clanToDelete);
	    return new ResponseEntity<>(HttpStatus.OK);
	} else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
    }

    @GetMapping("{clanId}")
    @ResponseBody
    public Clan getClan(@PathVariable int clanId) {
	Clan clanToFind = clnSvc.getClan(clanId);
	if (clanToFind != null) {
	    return clanToFind;
	} else {
	    return null;
	}
    }
}
