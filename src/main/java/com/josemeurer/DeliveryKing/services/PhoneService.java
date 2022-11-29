package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.PhoneDTO;
import com.josemeurer.DeliveryKing.entities.Phone;
import com.josemeurer.DeliveryKing.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional
    public PhoneDTO insert(PhoneDTO dto) {
        //Adicionar regex
        //Refatorar

        Phone entity = new Phone();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity = phoneRepository.save(entity);
        return new PhoneDTO(entity);
    }
}
