
package com.act.db.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;

/**
 * ×¨Ìâ
 * 
 * @author anchuantong
 */

@Entity
@Table(name = "special")
public class Special implements Serializable {

	/**
         * 
         */
	private static final long serialVersionUID = -578790651520778L;

	@Id
	@GeneratedValue
	private Integer id;

	private String title;

	private String description;

	private String creator;

	private Date created;

	private String modifer;

	private Date modifed;

	private Integer hits;

	private boolean published;

	private String logo;

	@OneToMany
	@JoinColumn(name = "special", unique = false)
	@OrderBy(clause = "pos asc")
	private List<SpecialModule> modules;

	@OneToMany
	@JoinColumn(name = "special", unique = false)
	private List<SpecialStyle> styles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getModifer() {
		return modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	public Date getModifed() {
		return modifed;
	}

	public void setModifed(Date modifed) {
		this.modifed = modifed;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<SpecialModule> getModules() {
		return modules;
	}

	public void setModules(List<SpecialModule> modules) {
		this.modules = modules;
	}

	public List<SpecialStyle> getStyles() {
		return styles;
	}

	public void setStyles(List<SpecialStyle> styles) {
		this.styles = styles;
	}

}
