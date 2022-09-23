package entities;

public class Product {

	private String name;
	private Double price;
	private Integer qtt;
	
	public Product(String name, Double price, Integer qtt) {
		this.name = name;
		this.price = price;
		this.qtt = qtt;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Integer getQtt() {
		return qtt;
	}
	
	public void setQtt(Integer qtt) {
		this.qtt = qtt;
	}
	
	public void totalValue() {
		 price *= qtt;
	}
	
}
