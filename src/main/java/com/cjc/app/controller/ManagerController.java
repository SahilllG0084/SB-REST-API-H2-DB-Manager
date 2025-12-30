package com.cjc.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cjc.app.model.Manager;
import com.cjc.app.model.Managers;
import com.cjc.app.service.ManagerService;

@RestController
@RequestMapping(value = "/api")
public class ManagerController {
     
	
	private ManagerService managerserv;
	
	@Autowired
	public void setManagerserv(ManagerService managerserv) {
		this.managerserv = managerserv;
	}

	@PostMapping(value = "/addmanager" , consumes = {"application/json","application/xml"} , produces = {"application/json" , "application/xml"})
	public Manager addManager(@RequestBody Manager manager)
	{
		 Manager savedmanager = managerserv.addManager(manager);		 
		 return savedmanager;
	}
	
	@GetMapping(value = "/getmanager/{id}" , consumes = {"application/json" , "application/xml"} , produces = {"application/json" , "application/xml"})
	public Manager getManager(@PathVariable("id") int id)
	{
		Manager dbmanager = managerserv.getManagerById(id);
		
		return dbmanager;
	}
	
	@GetMapping(value = "/getmanagers") //defualt data json
	public List<Manager> getManagers()
	{
		List<Manager> managers = managerserv.getManagers();
		
		return managers;
	}
	
	@GetMapping(value = "/getmanagers-xml" , produces ={"application/xml"} , consumes ={"application/xml"}) //only xml
	public Managers getManagersXml()
	{
		List<Manager> managersxml = managerserv.getManagersxml();
		
		Managers managers = new Managers();
		
		managers.setManagerlist(managersxml);
				
		return managers;
	}
	@DeleteMapping(value = "/deletemanager/{id}")
	public String deleteManager(@PathVariable("id") int id)
	{
		String msg = managerserv.deleteManager(id);
		
		return msg;
	}
	
	@PutMapping(value = "/updatemanager/{id}")
	public Manager updateManager(@PathVariable("id") int id , @RequestBody Manager manager)
	{
		 Manager updatedManager = managerserv.updateManager(id , manager);
		 
		 return updatedManager;
	}
	
	@PatchMapping(value = "/editmanager/{id}")
	public Manager editManager(@PathVariable("id") int id , @RequestBody Manager manager)
	{
		Manager editedManager = managerserv.editManager(id , manager);
		
		return editedManager;
	}
	
	//Pagination :
	@GetMapping(value = "/getmanagers/page") // another way -> /getmanagers/pnumber/{pageNumber}/psize{pageSize}
	public List<Manager> getManagersByPagination(@RequestParam (defaultValue = "0") int pageNumber,
			                                     @RequestParam (defaultValue = "10") int pageSize)
	{
		List<Manager> productsByPaging = managerserv.getProductsByPaging(pageNumber , pageSize);
		
		return productsByPaging;
	}
	
	//Sorting : By Salary -> Ascending & Descending Order.
	@GetMapping(value = "/getmanagers/sortBySalary")
	public List<Manager> getManagerBySorting(@RequestParam(defaultValue = "asc") String direction)
	{
		return managerserv.getManagersSortedBySalary(direction);
	}
	
	//Searching : By Name
	@GetMapping(value = "/getmanager/searchByName/{name}")
	public List<Manager> getManagerByName(@PathVariable("name") String firstname)
	{
		return managerserv.getManagerByName(firstname);
	}
	
	//Filtering : Data By Type And Salary Range(minSalary, maxSalary)
	@GetMapping(value = "/getmanagers/filter")
	public List<Manager> getManagersByFiltering(@RequestParam(required = false) String type,
			                                    @RequestParam(required = false) Double minSalary,
			                                    @RequestParam(required = false) Double maxSalary)
	{
		return managerserv.getManagersByFilter(type, minSalary, maxSalary);
	}
}
