package ntukhpi.kn221a.kro.webappsyrlab3.service;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Manager;
import ntukhpi.kn221a.kro.webappsyrlab3.repository.IManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService implements IManagerService {
    private final IManagerRepository managerRepository;

    public ManagerService(IManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<Manager> getAllEntities() {
        return managerRepository.findAll();
    }

    @Override
    public Manager getEntityById(Long id) {
        return managerRepository.findById(id).orElse(null);
    }

    @Override
    public Manager saveEntity(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateEntity(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public void deleteEntityById(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Manager getManagerByCode(String code) {
        return managerRepository.findManagerByCode(code);
    }
}
