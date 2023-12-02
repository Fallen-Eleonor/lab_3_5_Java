package ntukhpi.kn221a.kro.webappsyrlab3.repository;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findByManagerId(Long id);
    Phone findByNumber(String number);
}
