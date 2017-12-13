package com.revature.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.entities.Report;
import com.revature.service.ReportService;

@Controller
@RequestMapping("report")
// need allowCredentials, but won't need origins after bundling
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class ReportController {

	@Autowired
	private ReportService rs;
	private Logger log = Logger.getRootLogger();

	@GetMapping("loadbygame")
	@ResponseBody
	public Report loadReport(@RequestParam("id") int id) {
		log.info("Loading Game Report " + id);
		return rs.loadgameReport(id);
	}

	@GetMapping("tickets")
    @ResponseBody
    public List<Report> loadTickets() {
    	log.info("Loading all tickets");
    	return rs.getFlaggedReports();
    }

	@PutMapping("modify")
	@ResponseBody
	public Report modifyGame(@RequestBody Report rep) {
		log.info("Modifying a report");
		return rs.modify(rep);
	}
}
