package com.scenic.repo.interf.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import com.scenic.repo.pojo.Review;

public interface IReviewRespository extends JpaRepository<Review, Integer> {

}
