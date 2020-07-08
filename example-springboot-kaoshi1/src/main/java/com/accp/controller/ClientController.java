package com.accp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.domain.Address;

import com.accp.domain.Client;
import com.accp.service.ClientService;


@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("/client")
	public String tofind() {
		return "find";
	}
		
	@RequestMapping("/find")
	@ResponseBody
	public List<Address> find(){
		return clientService.find();
}
	@RequestMapping("/findById")
	@ResponseBody
	public Client find(String id) {
		System.out.println(id);
		return clientService.findByid(id);
	}		
	@RequestMapping("/create")
	@ResponseBody
	public String create(@RequestBody Client client) {
		return clientService.create(client);
	}
	

}
