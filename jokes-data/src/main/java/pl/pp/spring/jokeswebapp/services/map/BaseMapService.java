package pl.pp.spring.jokeswebapp.services.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.pp.spring.jokeswebapp.model.BaseEntity;
import pl.pp.spring.jokeswebapp.services.BaseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseMapService<E extends BaseEntity> implements BaseService<E> {

    private Logger log = LoggerFactory.getLogger(BaseMapService.class);
    protected Map<Long, E> map = new HashMap<>();
    private String type;

    public BaseMapService(String type) {
        this.type = type;
    }

    @Override
    public List<E> findAll() {
        log.info("find all {}", type);
        return new ArrayList<>(map.values());
    }

    @Override
    public E findById(Long id) {
        E e = map.get(id);
        log.info("find by Id: {} , {}", id, e.getClass());
        return e;
    }

    @Override
    public E save(E entity) {
        log.info("saving class: {}", entity.getClass());
        if ( entity.getId() == null ) {
            Long maxId = map.keySet().stream().max(Long::compare).orElse(0L);
            entity.setId(maxId + 1);
        }
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(Long id){
        log.info("delete category by id: {}", id);
        map.remove(id);
    }
}
