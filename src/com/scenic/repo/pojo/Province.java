package com.scenic.repo.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "province", catalog = "smartscenic1")
public class Province implements java.io.Serializable {

	// Fields

	private Integer provinceId;
	private String provinceName;
	private Set<City> cities = new HashSet<City>(0);

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** minimal constructor */
	public Province(String provinceName) {
		this.provinceName = provinceName;
	}

	/** full constructor */
	public Province(String provinceName, Set<City> cities) {
		this.provinceName = provinceName;
		this.cities = cities;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "province_id", unique = true, nullable = false)
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "province_name", nullable = false, length = 20)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}