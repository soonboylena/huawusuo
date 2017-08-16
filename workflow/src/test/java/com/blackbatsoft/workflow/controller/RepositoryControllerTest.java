package com.blackbatsoft.workflow.controller;

import com.blackbatsoft.workflow.viewModel.SimpleProcessDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RepositoryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryControllerTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Autowired
    RepositoryService repositoryService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
    }

    String expectedJson;

    @Test
    public void listProcesses() throws Exception {

        //List<HotelDto> hotelDtoList=testServices.findByCountry("US");
        //expectedJson = Obj2Json(hotelDtoList);

        String uri = "/listProcesses";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        List<SimpleProcessDefinition> rlt = new ObjectMapper().readValue(content, List.class);
        Assert.assertTrue("正确的返回值为200", status == 200);
    }

}