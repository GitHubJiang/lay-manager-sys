package com.lay.shop.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lay.shop.common.persistence.db.dao.Page;
import com.lay.shop.common.persistence.db.dao.Pagination;
import com.lay.shop.common.persistence.db.dao.Sort;
import com.lay.shop.common.utils.Validator;
import com.lay.shop.modules.sys.dao.UrlDao;
import com.lay.shop.modules.sys.model.Url;
import com.lay.shop.modules.sys.service.UrlService;

/**
 * 
 * @author Lay
 * @date 2017年6月9日 下午2:33:25
 * @since
 */
@Service
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlDao urlDao;
    @Override
    public void saveOrUpdate(Url url) {
        if(Validator.isNullOrEmpty(url.getId())){
            this.urlDao.insert(url);
        }else{
            this.urlDao.update(url);
        }
        
    }

    @Override
    public Pagination<Url> findListByQueryMapWithPage(Page page, Sort[] sorts, Map<String, Object> params) {
        return this.urlDao.findListByQueryMapWithPage(page, sorts, params);
    }

    @Override
    public void delete(Long id) {
        this.urlDao.delete(id);
    }

    @Override
    public List<Url> findAllList() {
        return this.urlDao.findAllList();
    }
	   
}
