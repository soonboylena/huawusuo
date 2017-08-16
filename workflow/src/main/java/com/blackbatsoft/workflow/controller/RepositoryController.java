package com.blackbatsoft.workflow.controller;

import com.blackbatsoft.workflow.viewModel.SimpleProcessDefinition;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 取得当前系统已经部署的流程
     *
     * @return
     */
    @RequestMapping("listProcesses")
    public List<SimpleProcessDefinition> listProcesses() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        if (list == null || list.isEmpty()) return null;
        return list.stream().map(processDefinition -> {
            SimpleProcessDefinition spd = new SimpleProcessDefinition();
            spd.setId(processDefinition.getId());
            spd.setDeployId(processDefinition.getDeploymentId());
            spd.setName(processDefinition.getName());
            spd.setPictureName(processDefinition.getDiagramResourceName());
            spd.setProcessKey(processDefinition.getKey());
            spd.setVersion(processDefinition.getVersion());
            spd.setResourceName(processDefinition.getResourceName());
            return spd;
        }).collect(Collectors.toList());
    }

}
