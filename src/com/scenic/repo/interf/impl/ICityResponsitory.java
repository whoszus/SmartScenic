package com.scenic.repo.interf.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scenic.repo.pojo.City;

public interface ICityResponsitory extends JpaRepository<City, Integer> {

	@Query("select c from City c where c.province.provinceId= ?1")
	public List<City> finfByProvinceId(Integer provinceId);

	@Query("select c from City c where c.province.provinceName= ?1")
	public List<City> finfByProvinceName(String provinceName);

}
