/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.persistence.Page;
import com.medical.common.service.CrudService;
import com.medical.common.utils.StringUtils;
import com.medical.modules.work.entity.Project;
import com.medical.modules.work.dao.ProjectDao;
import com.medical.modules.work.entity.ProjectDevelop;
import com.medical.modules.work.dao.ProjectDevelopDao;

/**
 * 项目管理Service
 * @author dyf
 * @version 2017-03-24
 */
@Service
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {

	@Autowired
	private ProjectDevelopDao projectDevelopDao;
	
	public Project get(String id) {
		Project project = super.get(id);
		project.setProjectDevelopList(projectDevelopDao.findList(new ProjectDevelop(project)));
		return project;
	}
	
	public List<Project> findList(Project project) {
		return super.findList(project);
	}
	
	public Page<Project> findPage(Page<Project> page, Project project) {
		return super.findPage(page, project);
	}
	
	@Transactional(readOnly = false)
	public void save(Project project) {
		super.save(project);
		for (ProjectDevelop projectDevelop : project.getProjectDevelopList()){
			if (projectDevelop.getId() == null){
				continue;
			}
			if (ProjectDevelop.DEL_FLAG_NORMAL.equals(projectDevelop.getDelFlag())){
				if (StringUtils.isBlank(projectDevelop.getId())){
					projectDevelop.setProject(project);
					projectDevelop.preInsert();
					projectDevelopDao.insert(projectDevelop);
				}else{
					projectDevelop.preUpdate();
					projectDevelopDao.update(projectDevelop);
				}
			}else{
				projectDevelopDao.delete(projectDevelop);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Project project) {
		super.delete(project);
		projectDevelopDao.delete(new ProjectDevelop(project));
	}
	
}