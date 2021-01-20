package com.emmanuel.plumas.p10JavaLibrarEasyWEB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.services.ReservationService;

@Controller
public class ReservationController extends CommonController{
	
	@Autowired
	@Qualifier("ReservationService")
	private ReservationService reservationService;
	
	@GetMapping(value="/reservationByUserLastName")
	public String getReservationByUserLastName(Model model) {
		String userLastName=getPrincipal();
		List<ReservationEntity> reservationByUserLastNameEntities=reservationService.getReservationByUserLastName(userLastName);
		model.addAttribute("principal", userLastName);
		model.addAttribute("reservationByUserLastNameEntities",reservationByUserLastNameEntities);
		return "reservationByUserLastName";
	}
}
