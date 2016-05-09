package com.scenic.repo.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Review entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "review", catalog = "smartscenic1")
public class Review implements java.io.Serializable {

	// Fields

	private Integer reviewNo;
	private ScenicSpot scenicSpot;
	private String reviewName;
	private String reviewContent;
	private Date reviewTime;

	// Constructors

	/** default constructor */
	public Review() {
	}

	/** full constructor */
	public Review(ScenicSpot scenicSpot, String reviewName,
			String reviewContent, Date reviewTime) {
		this.scenicSpot = scenicSpot;
		this.reviewName = reviewName;
		this.reviewContent = reviewContent;
		this.reviewTime = reviewTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "review_no", unique = true, nullable = false)
	public Integer getReviewNo() {
		return this.reviewNo;
	}

	public void setReviewNo(Integer reviewNo) {
		this.reviewNo = reviewNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scenic_spot_no")
	public ScenicSpot getScenicSpot() {
		return this.scenicSpot;
	}

	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
	}

	@Column(name = "review_name", length = 30)
	public String getReviewName() {
		return this.reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	@Column(name = "review_content", length = 512)
	public String getReviewContent() {
		return this.reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "review_time", length = 10)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

}