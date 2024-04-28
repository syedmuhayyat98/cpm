package com.example.cpm.service;

import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.FamilyMembers;
import com.example.cpm.repository.FamilyMembersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Log
public class FamilyMembersService {
    
    private final FamilyMembersRepository familyMembersRepository;

    public List<FamilyMembers> getAllFamilyMembers() {
        log.info("Getting all familyMembers data from database");
        return familyMembersRepository.findAll();
    }

    public FamilyMembers saveFamilyMembers(FamilyMembers familyMembers){
        log.info("Saving familyMembers data to database");
        return familyMembersRepository.save(familyMembers);
    }

    public Optional<FamilyMembers> findFamilyMembersById(Long id){
        log.info(String.format("Getting familyMembers data with id: %o", id));
        Optional<FamilyMembers> familyMembers = familyMembersRepository.findById(id);
        if (familyMembers.isEmpty()) throw new ResourceNotFoundException(String.format("FamilyMembers with id %o is not found", id));
        return familyMembers;
    }

    public void deleteFamilyMembers(Long id) {
        log.info(String.format("Deleting familyMembers data with id: %o", id));
        Optional<FamilyMembers> familyMembers = familyMembersRepository.findById(id);
        if (familyMembers.isEmpty()) throw new ResourceNotFoundException(String.format("FamilyMembers with id %o is not found", id));
        familyMembersRepository.deleteById(id);
    }

    public FamilyMembers updateFamilyMembers(FamilyMembers familyMembers){
        log.info(String.format("Updating familyMembers data with id: %o", familyMembers.getId()));
        return familyMembersRepository.save(familyMembers);
    }
}
