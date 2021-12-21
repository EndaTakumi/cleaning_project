package jp.co.sss.project.form;

import java.util.ArrayList;
import java.util.List;

public class ProductNumberList {
	private List<Integer> number = new ArrayList<>();
	
	public void setNumber(Integer number) {
		this.number.add(number);
	}
	public List<Integer> getNumber(){
		return number;
	}
	
}
