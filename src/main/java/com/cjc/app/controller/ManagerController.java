package com.cjc.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/v2")
public class ManagerController {
     
	
	private ManagerService managerserv;
	
	@Autowired
	public void setManagerserv(ManagerService managerserv) {
		this.managerserv = managerserv;
	}

	@PostMapping(value = "/managers",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                       produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Manager> addManager(@RequestBody Manager manager)
	{
		 Manager savedmanager = managerserv.addManager(manager);		 
		 
		 if(savedmanager != null)
		 {
			 return new ResponseEntity<Manager>(savedmanager, HttpStatus.CREATED);
		 }
		     return new ResponseEntity<Manager>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/managers/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Manager> getManagerById(@PathVariable("id") int id)
	{
		Manager dbmanager = managerserv.getManagerById(id);
		
		if(dbmanager != null)
		{
			return new ResponseEntity<Manager>(dbmanager, HttpStatus.FOUND);
		}
		    return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/managers")
	public ResponseEntity<List<Manager>> getManagers()
	{
		List<Manager> managers = managerserv.getManagers();
		
		if(!managers.isEmpty())
		{
			return new ResponseEntity<List<Manager>>(managers, HttpStatus.OK);
		}
		    return new ResponseEntity<List<Manager>>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/managers-xml",produces ={MediaType.APPLICATION_XML_VALUE})
	public Managers getManagersXml()
	{
		List<Manager> managersxml = managerserv.getManagersxml();
		
		Managers managers = new Managers();
		
		managers.setManagerlist(managersxml);
				
		return managers;
	}
	
	@DeleteMapping(value = "/managers/{id}")
	public String deleteManager(@PathVariable("id") int id)
	{
		String msg = managerserv.deleteManager(id);
		
		return msg;
	}
	
	@PutMapping(value = "/managers/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Manager updateManager(@PathVariable("id") int id , @RequestBody Manager manager)
	{
		 Manager updatedManager = managerserv.updateManager(id , manager);
		 
		 return updatedManager;
	}
	
	@PatchMapping(value = "/managers/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                                               produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Manager editManager(@PathVariable("id") int id , @RequestBody Manager manager)
	{
		Manager editedManager = managerserv.editManager(id , manager);
		
		return editedManager;
	}
	
	//Pagination :
	@GetMapping(value = "/managers/page", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<Manager> getManagersByPagination(@RequestParam (defaultValue = "0") int pageNumber,
			                                     @RequestParam (defaultValue = "10") int pageSize)
	{
		List<Manager> productsByPaging = managerserv.getProductsByPaging(pageNumber , pageSize);
		
		return productsByPaging;
	}
	
	//Sorting : By Salary -> Ascending & Descending Order.
	@GetMapping(value = "/managers/sortBySalary", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<Manager> getManagerBySorting(@RequestParam(defaultValue = "asc") String direction)
	{
		return managerserv.getManagersSortedBySalary(direction);
	}
	
	//Searching : By Name
	@GetMapping(value = "/managers/searchByName/{name}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<Manager> getManagerByName(@PathVariable("name") String firstname)
	{
		return managerserv.getManagerByName(firstname);
	}
	
	//Filtering : Data By Type And Salary Range(minSalary, maxSalary)
	@GetMapping(value = "/managers/filter", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public List<Manager> getManagersByFiltering(@RequestParam(required = false) String type,
			                                    @RequestParam(required = false) Double minSalary,
			                                    @RequestParam(required = false) Double maxSalary)
	{
		return managerserv.getManagersByFilter(type, minSalary, maxSalary);
	}
}
