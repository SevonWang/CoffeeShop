package cn.edu.neusoft.coffeeshop.vo;

import java.io.Serializable;

import android.R.string;

public class Coffee implements Serializable {

	private Integer coffee_id;
	private String coffee_name;
	private float coffee_price;
	private String coffee_info;
	private String coffee_comment;
	private String image_name;

	public Integer getCoffee_id() {
		return coffee_id;
	}

	public void setCoffee_id(Integer coffee_id) {
		this.coffee_id = coffee_id;
	}

	public String getCoffee_name() {
		return coffee_name;
	}

	public void setCoffee_name(String coffee_name) {
		this.coffee_name = coffee_name;
	}

	public float getCoffee_price() {
		return coffee_price;
	}

	public void setCoffee_price(float coffee_price) {
		this.coffee_price = coffee_price;
	}

	public String getCoffee_info() {
		return coffee_info;
	}

	public void setCoffee_info(String coffee_info) {
		this.coffee_info = coffee_info;
	}

	public String getCoffee_comment() {
		return coffee_comment;
	}

	public void setCoffee_comment(String coffee_comment) {
		this.coffee_comment = coffee_comment;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

}
