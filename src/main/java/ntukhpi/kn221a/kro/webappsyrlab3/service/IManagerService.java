package ntukhpi.kn221a.kro.webappsyrlab3.service;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Manager;

public interface IManagerService extends IEntityService<Manager>{
    Manager getManagerByCode(String code);
}
