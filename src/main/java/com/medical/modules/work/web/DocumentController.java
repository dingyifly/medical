/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.medical.modules.work.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.common.config.Global;
import com.medical.common.persistence.Page;
import com.medical.common.web.BaseController;
import com.medical.common.utils.StringUtils;
import com.medical.modules.work.entity.Document;
import com.medical.modules.work.service.DocumentService;

/**
 * 文档文献管理Controller
 * @author dyf
 * @version 2017-02-22
 */
@Controller
@RequestMapping(value = "${adminPath}/work/document")
public class DocumentController extends BaseController {

	@Autowired
	private DocumentService documentService;
	
	@ModelAttribute
	public Document get(@RequestParam(required=false) String id) {
		Document entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = documentService.get(id);
		}
		if (entity == null){
			entity = new Document();
		}
		return entity;
	}
	
	@RequiresPermissions("work:document:view")
	@RequestMapping(value = {"list", ""})
	public String list(Document document, HttpServletRequest request, HttpServletResponse response, Model model) {
		if ("1".equals(document.getType()) && !document.getCurrentUser().isAdmin()) {
			document.setOffice(document.getCurrentUser().getOffice());
		}
		Page<Document> page = documentService.findPage(new Page<Document>(request, response), document); 
		model.addAttribute("page", page);
		return "modules/work/documentList";
	}

	@RequiresPermissions("work:document:view")
	@RequestMapping(value = "form")
	public String form(Document document, Model model) {
		model.addAttribute("document", document);
		return "modules/work/documentForm";
	}

	@RequiresPermissions("work:document:edit")
	@RequestMapping(value = "save")
	public String save(Document document, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, document)){
			return form(document, model);
		}
		documentService.save(document);
		addMessage(redirectAttributes, "保存" + ("0".equals(document.getType()) ? "文档" : "文献") + "成功");
		return "redirect:"+Global.getAdminPath()+"/work/document/?repage&type="+document.getType();
	}
	
	@RequiresPermissions("work:document:edit")
	@RequestMapping(value = "delete")
	public String delete(Document document, RedirectAttributes redirectAttributes) {
		documentService.delete(document);
		addMessage(redirectAttributes, "删除" + ("0".equals(document.getType()) ? "文档" : "文献") + "成功");
		return "redirect:"+Global.getAdminPath()+"/work/document/?repage&type="+document.getType();
	}

}