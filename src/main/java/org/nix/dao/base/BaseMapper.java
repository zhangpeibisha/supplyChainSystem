package org.nix.dao.base;
import org.apache.ibatis.annotations.Param;
import org.nix.model.base.BaseModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11723 on 2017/5/4.
 */
@Repository
public interface BaseMapper<M extends BaseModel<M>>{
    void insert(M object);
    void delete(@Param("id") Integer id);
    void update(M model);
    M select(@Param("id") Integer id);
    List list(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("order") String order, @Param("sort") String sort, @Param("conditions") String conditions);
}
