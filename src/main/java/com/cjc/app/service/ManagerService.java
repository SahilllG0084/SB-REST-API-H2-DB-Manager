package com.cjc.app.service;

import java.util.List;
import com.cjc.app.model.Manager;

public interface ManagerService {

	Manager addManager(Manager manager);
	
	Manager getManagerById(int id);

	List<Manager> getManagers();

	List<Manager> getManagersxml();

	String deleteManager(int id);

	Manager updateManager(int id, Manager manager);

	Manager editManager(int id, Manager manager);

	List<Manager> getProductsByPaging(int pageNumber, int pageSize);

	List<Manager> getManagersSortedBySalary(String direction);

	List<Manager> getManagerByName(String firstname);

	List<Manager> getManagersByFilter(String type, Double minSalary, Double maxSalary);
}
