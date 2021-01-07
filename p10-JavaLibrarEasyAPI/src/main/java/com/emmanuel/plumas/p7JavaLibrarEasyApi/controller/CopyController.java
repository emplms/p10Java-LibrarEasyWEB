package com.emmanuel.plumas.p7JavaLibrarEasyApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.CopyEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.service.CopyService;

@RestController
public class CopyController {

		@Autowired
		@Qualifier("CopyService")
		private CopyService copyService;
		
		@GetMapping(value="copies")
		public List<CopyEntity> getAllCopies(){
			List<CopyEntity> copyEntities=copyService.getAllCopyEntities();
			return copyEntities;
		}
		
		@PostMapping(value="copy/create")
		public void createCopy(@RequestBody CopyEntity copyEntity) {
			copyService.createCopy(copyEntity);
		}
		
		@PostMapping(value="copy/update")
		public void upDateCopy(@RequestBody CopyEntity copyEntity) {
			copyEntity=copyService.upDateCopy(copyEntity);
		}
		
		@GetMapping(value="copy/delete/{copyId}")
		public void deleteCopyById(@PathVariable Long copyId) {
			copyService.deleteCopyById(copyId);
		}
}
