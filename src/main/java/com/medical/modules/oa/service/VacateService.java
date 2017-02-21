package com.medical.modules.oa.service;

import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medical.common.service.BaseService;
import com.medical.common.utils.StringUtils;
import com.medical.modules.act.utils.ActUtils;
import com.medical.modules.oa.dao.VacateDao;
import com.medical.modules.oa.entity.Leave;

@Service
@Transactional(readOnly=true)
public class VacateService extends BaseService {
	
	@Autowired
	private VacateDao vacateDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	
	public void save(Leave leave, Map<String, Object> variables) {
		
		if (StringUtils.isBlank(leave.getId())) {
			leave.preInsert();
			vacateDao.insert(leave);
		} else {
			leave.preUpdate();
			vacateDao.update(leave);
		}
		logger.debug("save entity: {}", leave);
		
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(leave.getCurrentUser().getLoginName());
		
		String businessKey = leave.getId().toString();
		variables.put("type", "leave");
		variables.put("busId", businessKey);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActUtils.PD_LEAVE[0], businessKey, variables);
		leave.setProcessInstance(processInstance);
		
		//更新流程实例
		leave.setProcessInstanceId(processInstance.getId());
		vacateDao.updateProcessInstanceId(leave);
		
		logger.debug("start process of {key= {}, bkey={}, pid={}, variables={}}", new Object[] {
			ActUtils.PD_LEAVE[0], businessKey, processInstance.getId(), variables
		});
		
	}

}
