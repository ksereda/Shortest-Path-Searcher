package com.example.showDistanceClient.controller;

import java.util.List;
import java.util.Optional;
import com.example.showDistanceClient.model.EmployeeEntity;
import com.example.showDistanceClient.service.EmployeeService;
import com.example.showDistanceClient.service.ServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class EmployeeMvcController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeService service;
    private ServiceFeignClient serviceFeignClient;

    @RequestMapping
    public String showEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
            model.addAttribute("employee", new EmployeeEntity());
        return "choose-employee";
    }

    // Using Feign Client
    @RequestMapping(path = "/getAllDataFromAddService")
    public String getData2(Model model) {
        List<EmployeeEntity> list = ServiceFeignClient.FeignHolder.create().getAllEmployeesList();
        model.addAttribute("employees", list);
        return "resultlist-employees";
    }

    // Using RestTemplate (MVC)
    @GetMapping("/dataWithRestTemplateMvc")
    public String getAllDataWithRestTemplate(Model model) {
        //accessing hello-service
        List<EmployeeEntity> objects = restTemplate.getForObject("http://add-service/show/", List.class);
        model.addAttribute("employees", objects);
        return "resultlist-employees";
    }

    @RequestMapping(path = "/findShortestWay")
    public String findAllEmployees(Model model, RedirectAttributes attributes, @RequestParam("city_from") String city_from, @RequestParam("city_to") String city_to) {
        List result;

        List<EmployeeEntity> list = service.getAllEmployees();
        EmployeeEntity[] GRAPH = service.convertListToArray(list);

        try {
            result = service.deikstraAlgorithmStart(GRAPH, city_from, city_to);
        } catch (Exception e) {
            attributes.addFlashAttribute("inputVertexError", true);
            return "redirect:/";
        }

        model.addAttribute("resultList", result);
        return "list-employees";
    }

}
