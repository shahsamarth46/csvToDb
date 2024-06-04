package com.example.demo_updated.demo_updated.service.impl;

import com.example.demo_updated.demo_updated.customDTO.CreateUserDTO;
import com.example.demo_updated.demo_updated.customDTO.LoginDTO;
import com.example.demo_updated.demo_updated.model.Employee;
import com.example.demo_updated.demo_updated.repository.EmployeeRepository;
import com.example.demo_updated.demo_updated.service.EmployeeService;
import com.example.demo_updated.demo_updated.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public String isValidUserAndReturnRole(LoginDTO loginDTO) {

        Employee e= employeeRepository.findByUsernameAndPassword(loginDTO.getUserName(),loginDTO.getPassword());
        if(e != null && !StringUtils.isEmpty(e.getRole())){
            return e.getRole();
        }
        return null;
    }

    @Override
    public Employee createUser(CreateUserDTO emp, String role) {
        Employee employee=convertDTOToEntity(emp);
        if(CommonUtils.hmapForRoleMapping.containsKey(role))
        {
            employee.setRole(CommonUtils.hmapForRoleMapping.get(role));
           return  employeeRepository.save(employee);
        }

        return null;
    }

    @Override
    public Map<String, Integer> uploadCsv(MultipartFile file) throws IOException {
         byte[] bytes=file.getBytes();
         String str =new String(bytes);
         String [] strs=str.split("\n");
         Map<String,Integer> hmap = new HashMap<>();
         for(int i=0;i< strs.length;i++){
             String[] commaSperated=strs[i].split(",");
             String key = commaSperated[0]+"$#"+commaSperated[1]+"$#"+commaSperated[2]+"$#"+
                     commaSperated[3];
             hmap.put(key,hmap.getOrDefault(key,0)+1);
         }
        return hmap;
    }

    private Employee convertDTOToEntity(CreateUserDTO emp) {
        Employee e = new Employee();
        e.setFirst_name(emp.getFirst_name());
        e.setLast_name(emp.getLast_name());
        e.setDob(emp.getDob());
        e.setGender(emp.getGender());
        e.setUsername(emp.getUsername());
        e.setPassword(emp.getPassword());
        e.setMiddle_name(emp.getMiddle_name());
        return e;
    }


}
