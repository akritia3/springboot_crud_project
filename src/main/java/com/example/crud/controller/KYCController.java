package com.example.crud.controller;

import com.example.crud.entity.KYC;
import com.example.crud.entity.User;
import com.example.crud.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class KYCController {

    @Autowired
    private KYCService kycService;

    @PostMapping("/kyc")
    public KYC addKYCEntry(@RequestBody KYC kyc){
        return kycService.addKYC(kyc);
    }

    @GetMapping("/kyc")
    public List<KYC> getAllKYCEntries(){
        return kycService.fetchAllKYCs();
    }

    @GetMapping("/kyc/{kycNumber}")
    public KYC getKYCEntry(@PathVariable Long kycNumber){
        return kycService.getKYCbyNumber(kycNumber);
    }

    @PutMapping ("/kyc/{kycNumber}")
    public KYC updateKYCEntry(@PathVariable Long kycNumber, @RequestBody KYC kyc){
        return kycService.updateKYC(kycNumber, kyc);
    }

    @DeleteMapping("/kyc/{kycNumber}")
    public String deleteKYCEntry(@PathVariable Long kycNumber){
        return kycService.deleteKYC(kycNumber);
    }

    @GetMapping("/user-kyc-details/{letter}")
    public List<KYC> getKYCwithFirstLetter(@PathVariable String letter) {
        return kycService.getKYCwithFirstLetter(letter);
    }

    @GetMapping("/user-kyc-details")
    public Map<Long, KYC> getAllUsersAndKYCs() {
        return kycService.getAllUsersAndKYCs();
    }
}
