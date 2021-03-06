package com.kodlamaio.hrmsDemo5.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="curriculum_vitaes")
public class CurriculumVitae {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="photo_link")
	private String photoLink;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="linkedın_link")
	private String linkedınLink;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDate createDate = LocalDate.now();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae")
	private List<School> schools;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae")
	private List<WorkExperience> workExperiences;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae")
	private List<Language> languages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitae")
	private List<TechnologyKnowledge> technologyKnowledges;
	
	
	public void setCreateDate(LocalDate createDate) {
		this.createDate = LocalDate.now();
	}
	
}
