package com.cjc.app.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Managers {
  
	private List<Manager> managerlist;

	public List<Manager> getManagerlist() {
		return managerlist;
	}

	public void setManagerlist(List<Manager> managerlist) {
		this.managerlist = managerlist;
	}	
}
