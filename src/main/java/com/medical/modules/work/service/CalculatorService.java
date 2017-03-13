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
import com.medical.modules.work.entity.Calculator;
import com.medical.modules.work.dao.CalculatorDao;
import com.medical.modules.work.entity.CalculatorItem;
import com.medical.modules.work.dao.CalculatorItemDao;

/**
 * 物料项目Service
 * @author dyf
 * @version 2017-03-13
 */
@Service
@Transactional(readOnly = true)
public class CalculatorService extends CrudService<CalculatorDao, Calculator> {

	@Autowired
	private CalculatorItemDao calculatorItemDao;
	
	public Calculator get(String id) {
		Calculator calculator = super.get(id);
		calculator.setCalculatorItemList(calculatorItemDao.findList(new CalculatorItem(calculator)));
		return calculator;
	}
	
	public List<Calculator> findList(Calculator calculator) {
		return super.findList(calculator);
	}
	
	public Page<Calculator> findPage(Page<Calculator> page, Calculator calculator) {
		return super.findPage(page, calculator);
	}
	
	@Transactional(readOnly = false)
	public void save(Calculator calculator) {
		super.save(calculator);
		for (CalculatorItem calculatorItem : calculator.getCalculatorItemList()){
			if (calculatorItem.getId() == null){
				continue;
			}
			if (CalculatorItem.DEL_FLAG_NORMAL.equals(calculatorItem.getDelFlag())){
				if (StringUtils.isBlank(calculatorItem.getId())){
					calculatorItem.setProject(calculator);
					calculatorItem.preInsert();
					calculatorItemDao.insert(calculatorItem);
				}else{
					calculatorItem.preUpdate();
					calculatorItemDao.update(calculatorItem);
				}
			}else{
				calculatorItemDao.delete(calculatorItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Calculator calculator) {
		super.delete(calculator);
		calculatorItemDao.delete(new CalculatorItem(calculator));
	}
	
}