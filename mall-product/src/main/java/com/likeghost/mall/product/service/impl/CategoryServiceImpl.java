package com.likeghost.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.likeghost.common.pojo.vo.PageVo;
import com.likeghost.common.utils.Query;
import com.likeghost.mall.product.dao.CategoryDao;
import com.likeghost.mall.product.entity.CategoryEntity;
import com.likeghost.mall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author likeghost
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageVo queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageVo(page);
    }

    /**
     * 获取所有的标签组织而成的树形结构具体实现
     * @return List<CategoryEntity>
     */
    @Override
    public List<CategoryEntity> listTree() {

        List<CategoryEntity> list = baseMapper.selectList(null);

        List<CategoryEntity> listTree= list.stream()
                .filter((o)->o.getParentCid()==0)
                .peek(item-> item.setChildren(getChildren(item,list)))
                .sorted((o1, o2) -> o1.getSort() == null ? 0 : o1.getSort().compareTo(o2.getSort() == null ? 0 : o2.getSort()))
                .collect(Collectors.toList());



        return listTree;


    }

    @Override
    public int removeCategoryByIds(List<Long> catIds) {
        int deleteCnt =-1;
        for (Long catId : catIds) {

            if(baseMapper.selectList(new LambdaQueryWrapper<CategoryEntity>()
                    .eq(CategoryEntity::getParentCid,catId)).size()!=0){
                deleteCnt=0;
                break;
            }
        }
        if(deleteCnt==0){
            return deleteCnt;
        }
        else{
            return baseMapper.deleteBatchIds(catIds) ;

        }
    }

    private List<CategoryEntity> getChildren(CategoryEntity item, List<CategoryEntity> list) {

        List<CategoryEntity> children = list.stream().filter(child -> child.getParentCid().equals(item.getCatId()))
                .peek(child -> child.setChildren(getChildren(child, list)))
                .sorted((o1, o2) -> o1.getSort() == null ? 0 : o1.getSort().compareTo(o2.getSort() == null ? 0 : o2.getSort()))
                .collect(Collectors.toList());
        return children;
    }




}