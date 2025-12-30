package com.cjc.app.serivceimpl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cjc.app.model.Manager;
import com.cjc.app.repository.ManagerRepository;
import com.cjc.app.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
    
	
	private ManagerRepository managerrepo;
	
	@Autowired
	public void setManagerrepo(ManagerRepository managerrepo) {
		this.managerrepo = managerrepo;
	}

	@Override
	public Manager addManager(Manager manager) {
		
	  System.out.println("In Service Layer :"+manager);
		
	  Manager savedmanager = managerrepo.save(manager);
		
	  return savedmanager;
	}

	@Override
	public Manager getManagerById(int id) {
		
		if(managerrepo.existsById(id))
		{
			 Manager manager = managerrepo.findById(id).get();
			 
			 return manager;
		}
		
		return null;
	}
	
	@Override
	public List<Manager> getManagers() {
		
		List<Manager> managerlist = managerrepo.findAll();
		
		return managerlist;
	}
	
	@Override
	public List<Manager> getManagersxml() {
		
		List<Manager> list = managerrepo.findAll();
		return list;
	}
	
	@Override
	public String deleteManager(int id) {
		
		String msg = "";
		
		if(managerrepo.existsById(id))
		{
			managerrepo.deleteById(id);
			
              msg = "Manager Deleted Successfully For Given Id :"+id;
              return msg;
		}
		 msg = "Given Id For Delete Manager Is Not Found :"+id;
		return msg;
	}
	
	@Override
	public Manager updateManager(int id, Manager manager) {
		
		if(managerrepo.existsById(id))
		{
			Manager dbmanager = managerrepo.findById(id).get();
			
			dbmanager.setId(manager.getId());
			dbmanager.setFirstname(manager.getFirstname());
			dbmanager.setLastname(manager.getLastname());
			dbmanager.setType(manager.getType());
			dbmanager.setSalary(manager.getSalary());
			
			Manager savemanager = managerrepo.save(dbmanager);
			
			return savemanager;
		}
		return null;
	}
	
	@Override
	public Manager editManager(int id, Manager manager) {
		
		if(managerrepo.existsById(id))
		{
			Manager dbmg = managerrepo.findById(id).get();
			
			if(manager.getId() != null)
			{
				dbmg.setId(manager.getId());
			}
			
			if(manager.getFirstname() != null)
			{
				dbmg.setFirstname(manager.getFirstname());
			}
			
			if(manager.getLastname() != null)
			{
				dbmg.setLastname(manager.getLastname());
			}
			
			if(manager.getType() != null)
			{
				dbmg.setType(manager.getType());
			}
			
			if(manager.getSalary() != null)
			{
				dbmg.setSalary(manager.getSalary());
			}
			
			  return managerrepo.save(dbmg);
		}
		
		return null;
	}
	
	@Override
	public List<Manager> getProductsByPaging(int pageNumber, int pageSize) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
		Page dbmg = managerrepo.findAll(page);
		
		if(dbmg.hasContent())
		{
			List managerslist = dbmg.getContent();
			
			return managerslist;
		}
		
		return null;
	}
	
	@Override
	public List<Manager> getManagersSortedBySalary(String direction) {
		
		Sort sort;
		
		if(direction != null && direction.equalsIgnoreCase("desc"))
		{
			sort = Sort.by(Sort.Direction.DESC, "salary");
		}
		else {
			sort = Sort.by(Sort.Direction.ASC, "salary");
		} 
		
		return managerrepo.findAll(sort);
	}
	
	@Override
	public List<Manager> getManagerByName(String firstname) {
		
		 List<Manager> managerslist = managerrepo.findByfirstname(firstname);
		 
		 if(!managerslist.isEmpty())
		    return managerslist;
		 else		  
		return Collections.emptyList();
	}

	@Override
	public List<Manager> getManagersByFilter(String type, Double minSalary, Double maxSalary) {
				
		return managerrepo.filterManagers(type, minSalary, maxSalary);
	}
}
