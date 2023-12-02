package ntukhpi.kn221a.kro.webappsyrlab3.service;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Phone;

import java.util.List;

public interface IPhoneService extends IEntityService<Phone>{
    Phone getPhoneByNumber(String number);
    List<Phone> getAllPhonesByManagerId(Long id);
}