package com.fastcampus.jblog.biz.category;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOMyBatis {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCategory(CategoryVO vo) {
		mybatis.insert("CategoryDAO.insertCategory", vo);
	}

	public CategoryVO getCategory(CategoryVO vo) {
		return (CategoryVO) mybatis.selectOne("CategoryDAO.getCategory", vo);
	}

	public List<CategoryVO> getCategoryList(CategoryVO vo) {
		return mybatis.selectList("CategoryDAO.getCategoryList", vo);
	}
	
	public void updateCategory(CategoryVO vo) {
		mybatis.update("CategoryDAO.updateCategory", vo);
	}

	public void deleteCategory(int categoryId) {
		mybatis.delete("CategoryDAO.deleteCategory", categoryId);
	}	

}
