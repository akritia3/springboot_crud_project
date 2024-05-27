package com.example.crud.service;

import com.example.crud.ExceptionHandler.NotFoundException;
import com.example.crud.entity.KYC;
import com.example.crud.entity.User;
import com.example.crud.repository.KYCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KYCService {

    @Autowired
    private KYCRepository kycRepository;

    public KYC addKYC(KYC kyc){
        return kycRepository.save(kyc);
    }

    public List<KYC> fetchAllKYCs(){
        return kycRepository.findAll();
    }

    public KYC getKYCbyNumber(Long kycNumber){
        Optional<KYC> kyc = kycRepository.findById(kycNumber);
        if (kyc.isEmpty()) {
            throw new NotFoundException("KYC with number " + kycNumber + " not found");
        }
        return kyc.orElse(null);
    }

    public KYC updateKYC(Long kycNumber, KYC kyc) {
        Optional<KYC> originalKYCEntry = kycRepository.findById(kycNumber);

        if (originalKYCEntry.isEmpty()) {
            throw new NotFoundException("KYC with number " + kycNumber + " not found");
        }
        KYC originalKYC = originalKYCEntry.get();

        if (Objects.nonNull(kyc.getName()) && !"".equalsIgnoreCase(kyc.getName())) {
            originalKYC.setName(kyc.getName());
        }
//        if (Objects.nonNull(kyc.getUserID())) {
//            originalKYC.setUserID(kyc.getUserID());
//        }
        return kycRepository.save(originalKYC);
    }

    public String deleteKYC(Long kycNumber) {
        if (kycRepository.findById(kycNumber).isEmpty()) {
            throw new NotFoundException("Entry not found");
        }
        kycRepository.deleteById(kycNumber);
        return "Deleted successfully";
    }

    public List<KYC> getKYCwithFirstLetter(String letter) {
        return kycRepository.getKYCwithFirstLetter(letter + '%');
    }

    public Map<Long, KYC> getAllUsersAndKYCs() {
        List<Object[]> KYCsAsObject = kycRepository.getAllUsersAndKYCs();
        Map<Long, KYC> userKYCMap = new HashMap<>();

        for (Object[] object : KYCsAsObject) {
            User user = (User) object[0];
            KYC kyc = (KYC) object[1];
            userKYCMap.put(user.getUserID(), kyc);
        }

        return userKYCMap;
    }
}
