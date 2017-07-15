package com.lay.shop.modules.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.modules.sys.command.OrganizationCommand;
import com.lay.shop.modules.sys.dao.OrganizationDao;
import com.lay.shop.modules.sys.model.Organization;
import com.lay.shop.modules.sys.service.OrganizationService;
@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationDao organizationDao;
    
    @Override
    public void save(Organization organization) {
        this.organizationDao.insert(organization);
    }

    @Override
    public void update(Organization organization) {
        organization.setLastModifyTime(new Date());
        this.organizationDao.update(organization);
    }

    @Override
    public Pagination<OrganizationCommand> findPageListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.organizationDao.findPageListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public void delete(Long id) {
        this.organizationDao.delete(id);
    }

    @Override
    public List<Organization> findAllList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OrganizationCommand findOrganizationById(Long id) {
        return this.organizationDao.findOrganizationById(id);
    }

    
}
