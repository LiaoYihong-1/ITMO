package com.example.demo.Controller;

import com.example.demo.Dao.Cart.Cart;
import com.example.demo.Dao.Cart.CartRepository;
import com.example.demo.Dao.Device.Device;
import com.example.demo.Dao.Device.DeviceRepository;
import com.example.demo.Dao.Transaction.Transaction;
import com.example.demo.Dao.Transaction.TranscationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class TransactionController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    TranscationRepository transcationRepository;
    final String Success = "Success";
    final String Fail = "Fail";
    public boolean checkString(String s){
        if ((s == null) | (Objects.equals(s, ""))){
            return false;
        }
        return true;
    }
    @ResponseBody
    @PostMapping("/adddevice")
    public String addDevice(HttpServletRequest request){
        Integer id;
        Integer value;
        try{
            id = Integer.valueOf(request.getParameter("company"));
            value = Integer.valueOf(request.getParameter("value"));
        }catch (Exception e){
            return Fail;
        }
        String type = request.getParameter("type");
        if(!checkString(type)){
            return Fail;
        }
        Device device = new Device();
        device.setCompany(id);
        device.setValue(value);
        device.setType(type);
        deviceRepository.save(device);
        return Success;
    }

    @ResponseBody
    @PostMapping("/addcart")
    public String addCart(HttpServletRequest request){
        Integer customer;
        Integer device;
        try{
            customer = Integer.valueOf(request.getParameter("customer"));
            device = Integer.valueOf(request.getParameter("device"));
        }catch (Exception e){
            return Fail;
        }
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setDevice(device);
        cartRepository.save(cart);
        return Success;
    }
    @ResponseBody
    @PostMapping("/addtransaction")
    public String addTransaction(HttpServletRequest request){
        Integer customer;
        Integer device;
        Integer amount;
        Integer company;
        try{
            customer = Integer.valueOf(request.getParameter("customer"));
            device = Integer.valueOf(request.getParameter("device"));
            company = Integer.valueOf(request.getParameter("company"));
            amount = Integer.valueOf(request.getParameter("amount"));
        }catch (Exception e){
            return Fail;
        }
        Transaction transaction = new Transaction();
        transaction.setCostomer(customer);
        transaction.setDevice(device);
        transaction.setMount(amount);
        transaction.setSeller(company);
        transaction.setStatus("On proccess");
        transcationRepository.save(transaction);
        return Success;
    }

    @ResponseBody
    @PostMapping("/getalldevices")
    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }
    @ResponseBody
    @PostMapping("/getTransaction")
    public List<Transaction> getTransaction(HttpServletRequest request){
        Integer customer;
        try{
            customer = Integer.valueOf(request.getParameter("customer"));
        }catch (Exception e){
            return null;
        }
        return transcationRepository.findByCostomer(customer);
    }
}
