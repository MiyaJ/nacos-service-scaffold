package com.miya.demo.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miya.demo.entity.Customer;
import com.miya.demo.mapper.CustomerMapper;
import com.miya.demo.service.CustomerService;
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

}
