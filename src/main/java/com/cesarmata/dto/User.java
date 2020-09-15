/**
 * 
 */
package com.cesarmata.dto;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Cesar Mata.
 *
 */

@ApiModel(description = "All details about this user. ")
public class User {
	
	private Integer id;
	
	@Size(min = 2, message = "Name should have atleast  2 characters")
	@ApiModelProperty(notes = "Name should have atleast  2 characters. ")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "Brithday should be in the past. ")
	private Date birthday;
	
	public User() {
		
	}
	
	public User(Integer id, String name, Date birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}
}