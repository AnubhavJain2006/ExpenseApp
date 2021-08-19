package com.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.ReportBean;
import com.bean.UserBean;
import com.dao.ReportDao;

@Controller
public class ReportController {
	String activeLink = "report";

	@Autowired
	ReportDao reportDao;

	public boolean isValidUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
//		System.out.println(session.getAttribute("user"));
		UserBean user = (UserBean) session.getAttribute("user");
		if ((UserBean) session.getAttribute("user") != null) {
			return true;
		} else {
			return false;
		}
	}

	public int getUserId(HttpSession session) {
		return ((UserBean) session.getAttribute("user")).getUser_id();
	}

	@RequestMapping(value = "/user/report")
	public String userReport(HttpServletRequest req, Model model) {
		if (isValidUser(req)) {
			model.addAttribute("activeLink", activeLink);
			return "/user/report";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/user/report/getTransactionByWeek/{startDate}/{endDate}")
	public String getTransactionByWeek(HttpServletRequest req, HttpSession session, @PathVariable long startDate,
			@PathVariable long endDate, Model model) {
		if (isValidUser(req)) {
			int userId = getUserId(session);
			System.out.println(userId);
//		System.out.println("startDate is " + startDate);
//		System.out.println("endDate is " + endDate);
			Timestamp startTimestamp = new Timestamp(startDate);
			Timestamp endTimestamp = new Timestamp(endDate);
//		System.out.println("startTimestamp is " + startTimestamp);
//		System.out.println("endTimestamp is " + endTimestamp);

			List<ReportBean> transactionListByWeek = reportDao.getUserTransactionByWeek(userId,
					startTimestamp.toString(), endTimestamp.toString());
//		for (ReportBean reportBean : allTransaction) {
//			System.out.println(reportBean.getTransactionBean());
//		}
			model.addAttribute("transactionListByWeek", transactionListByWeek);
			model.addAttribute("display", "week");
			return "/user/displayReport";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/user/report/getTransactionByPeriod/{startDate}/{endDate}")
	public String getTransactionByPeriod(HttpServletRequest req, HttpSession session, @PathVariable long startDate,
			@PathVariable long endDate, Model model) {
		if (isValidUser(req)) {
			int userId = getUserId(session);
			System.out.println(userId);
// 			System.out.println("startDate is " + startDate);
//			System.out.println("endDate is " + endDate);
			Timestamp startTimestamp = new Timestamp(startDate);
			Timestamp endTimestamp = new Timestamp(endDate);
//			System.out.println("startTimestamp is " + startTimestamp);
//			System.out.println("endTimestamp is " + endTimestamp);

			List<ReportBean> transactionListByPeriod = reportDao.getUserTransactionByPeriod(userId,
					startTimestamp.toString(), endTimestamp.toString());
//			for (ReportBean reportBean : allTransaction) {
//				System.out.println(reportBean.getTransactionBean());
//			}
			model.addAttribute("transactionListByPeriod", transactionListByPeriod);
			model.addAttribute("display", "period");
			model.addAttribute("startDate", new java.sql.Date(startTimestamp.getTime()).toString());
			model.addAttribute("endDate", new java.sql.Date(endTimestamp.getTime()).toString());
			return "/user/displayReport";
		} else {
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "/user/report/getTransactionByMonth/{month}/{year}")
	public String getTransactionByMonth(HttpServletRequest req, HttpSession session, @PathVariable int month,
			@PathVariable int year, Model model) {
		if (isValidUser(req)) {
			int userId = getUserId(session);

			String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };
			System.out.println("userMonth is " + month);
			System.out.println("userYear is " + year);

			List<ReportBean> transactionListByMonth = reportDao.getUserTransactionByMonth(userId, month, year);
//			for (ReportBean reportBean : allTransaction) {
//				System.out.println(reportBean.getTransactionBean());
//			}
			model.addAttribute("transactionListByMonth", transactionListByMonth);
			model.addAttribute("display", "month");
			model.addAttribute("month", months[month]);
			model.addAttribute("year", year);
			return "/user/displayReport";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/user/report/getTransactionByYear/{year}")
	public String getTransactionByYear(HttpServletRequest req, HttpSession session, @PathVariable int year,
			Model model) {
		if (isValidUser(req)) {
			int userId = getUserId(session);

			String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August",
					"September", "October", "November", "December" };
			System.out.println("userYear is " + year);

			List<ReportBean> transactionListByYear = reportDao.getUserTransactionByYear(userId, year);
//			for (ReportBean reportBean : allTransaction) {
//				System.out.println(reportBean.getTransactionBean());
//			}
			model.addAttribute("transactionListByYear", transactionListByYear);
			model.addAttribute("display", "annual");
//			model.addAttribute("month", months[month]);
			model.addAttribute("year", year);
			return "/user/displayReport";
		} else {
			return "redirect:/login";
		}

	}

}