package com.josemeurer.DeliveryKing.services;

import com.josemeurer.DeliveryKing.dtos.UserDTO;
import com.josemeurer.DeliveryKing.dtos.UserInsertDTO;
import com.josemeurer.DeliveryKing.dtos.UserMinDTO;
import com.josemeurer.DeliveryKing.entities.Address;
import com.josemeurer.DeliveryKing.entities.User;
import com.josemeurer.DeliveryKing.repositories.RoleRepository;
import com.josemeurer.DeliveryKing.repositories.UserRepository;
import com.josemeurer.DeliveryKing.services.exceptions.DatabaseException;
import com.josemeurer.DeliveryKing.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final String INITIAL_USER_ROLE = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserMinDTO> findAllPaged(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(UserMinDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User entity = userRepository.findFullUser(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return new UserDTO(entity, entity.getAddresses(), entity.getRoles());
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    //implementar no futuro adm poder atualizar conta usuario


    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        dtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.getRoles().add(roleRepository.findByAuthority(INITIAL_USER_ROLE));
        entity = userRepository.save(entity);
        return new UserDTO(entity, entity.getAddresses(), entity.getRoles());
    }

    private void dtoToEntity(UserInsertDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        dto.getAddresses().forEach(x -> entity.getAddresses()
                .add(new Address(null, x.getName(), x.getAddress(), x.getNumber())));
    }
}
