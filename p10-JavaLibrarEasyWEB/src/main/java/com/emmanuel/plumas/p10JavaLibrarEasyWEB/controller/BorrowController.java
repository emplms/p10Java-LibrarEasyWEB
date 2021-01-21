package com.emmanuel.plumas.p10JavaLibrarEasyWEB.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.services.BorrowService;

@Controller
public class BorrowController extends CommonController{

	@Autowired
	@Qualifier("BorrowService")
	private BorrowService borrowService;
	
	@GetMapping(value="/borrowByUserLastName")
	public String getBorrowByUserLastName(Model model)  {
		String userLastName=getPrincipal();
		List<BorrowEntity> oldBorrowEntities=borrowService.getOldBorrowByUserLastName(userLastName);
		List<BorrowEntity> currentBorrowEntities=borrowService.getCurrentBorrowByUserLastName(userLastName);
		Date today=new Date();
		model.addAttribute("oldBorrowEntities", oldBorrowEntities);
		model.addAttribute("currentBorrowEntities", currentBorrowEntities);
		model.addAttribute("principal",userLastName);
		model.addAttribute("today",today);
		return "borrowByUserLastName";
	}
	
	@GetMapping(value="/borrow/extendBorrow/{borrowId}")
	public String setExtendBorrow (@PathVariable Long borrowId) {
		borrowService.extendBorrow(borrowId);
		/* Redirection pour recharger les données" */
		return"redirect:/borrowByUserLastName";
	}
}
