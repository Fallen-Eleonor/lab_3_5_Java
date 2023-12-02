package ntukhpi.kn221a.kro.webappsyrlab3.service;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Phone;
import ntukhpi.kn221a.kro.webappsyrlab3.repository.IPhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService implements IPhoneService {
    private final IPhoneRepository phoneRepository;

    public PhoneService(IPhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Phone getPhoneByNumber(String number) {
        return phoneRepository.findByNumber(number);
    }

    @Override
    public List<Phone> getAllPhonesByManagerId(Long id) {
        return phoneRepository.findByManagerId(id);
    }

    @Override
    public List<Phone> getAllEntities() {
        return phoneRepository.findAll();
    }

    @Override
    public Phone saveEntity(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone getEntityById(Long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    @Override
    public Phone updateEntity(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public void deleteEntityById(Long id) {
        phoneRepository.deleteById(id);
    }
}
